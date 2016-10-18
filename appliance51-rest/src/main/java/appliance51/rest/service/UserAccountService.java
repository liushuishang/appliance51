package appliance51.rest.service;

import appliance51.common.exception.EngineExceptionHelper;
import appliance51.common.utils.CertificateNoUtil;
import appliance51.common.utils.ExceptionAssert;
import appliance51.common.utils.PasswordUtl;
import appliance51.dao.domain.ClientLoginStatus;
import appliance51.dao.domain.Proprietor;
import appliance51.dao.domain.User;
import appliance51.dao.domain.Workman;
import appliance51.dao.model.AccountType;
import appliance51.dao.repositories.ClientLoginLogRespository;
import appliance51.rest.dto.UserLoginResult;
import appliance51.rest.dto.WorkmanRegistration;
import appliance51.rest.exception.UserExcepFactor;
import appliance51.security.context.RequestContext;
import appliance51.security.context.ThreadLocalContext;
import appliance51.security.service.AuthTokenService;
import appliance51.security.service.MobileCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 用户帐号服务
 * Created by yuananyun on 2016/9/11.
 */
@Component
public class UserAccountService {

    @Autowired
    private ProprietorDBService proprietorDBService;

    @Autowired
    private WorkmanDBService workmanDBService;

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private MobileCodeService mobileCodeService;

    @Autowired
    private ClientLoginLogRespository clientLoginLogRespository;

    /**
     * 师傅注册
     *
     * @param registration
     * @return
     */
    public UserLoginResult workmanRegister(WorkmanRegistration registration) {
        String mobile = registration.getMobile();
        String certificateNo = registration.getCertificateNo();
        String password = registration.getPassword();

        ExceptionAssert.notBlank(mobile, UserExcepFactor.MOBILE_BLANK);
        ExceptionAssert.notBlank(certificateNo, UserExcepFactor.CERTIFICATE_NO_BLANK);
        ExceptionAssert.notBlank(password, UserExcepFactor.USERPASS_BLANK);
        ExceptionAssert.notEmpty(registration.getServiceItemIdList(), UserExcepFactor.SERVICE_ITEM_EMPTY);
        ExceptionAssert.notEmpty(registration.getServiceAdCodeList(), UserExcepFactor.SERVICE_REGION_EMPTY);

        boolean isExists = isAccountExists(AccountType.Workman, mobile);
        if (isExists)
            throw EngineExceptionHelper.localException(UserExcepFactor.ACCOUNT_EXISTS, "手机号已经被注册");

        if (!CertificateNoUtil.validate(certificateNo))
            throw EngineExceptionHelper.localException(UserExcepFactor.CERTIFICATE_NO_ERROR);

        Workman man = workmanDBService.save(registration);
        if (man == null)
            throw EngineExceptionHelper.localException(UserExcepFactor.SAVE_FAILURE);

        return workmanLogin(mobile, password, null, 1);

    }

    /**
     * 师傅登录
     *
     * @param mobile    登录手机号
     * @param password  登录密码
     * @param code      手机动态验证码
     * @param loginType 登录的方式,0:密码;1:手机验证码
     * @return token
     */
    public UserLoginResult workmanLogin(String mobile, String password, String code, Integer loginType) {
        Workman workman = workmanDBService.findOneByMobile(mobile);
        return userLogin(mobile, password, code, loginType, workman);
    }

    /**
     * 业主登录
     *
     * @param mobile
     * @param code
     * @return
     */
    public UserLoginResult proprietorLogin(String mobile, String code) {
        ExceptionAssert.notBlank(mobile, UserExcepFactor.MOBILE_BLANK);
        Proprietor proprietor = proprietorDBService.findOneByMobile(mobile);
        if (proprietor == null) {
            //自动注册用户帐号
            proprietor = new Proprietor();
            proprietor.setMobile(mobile);
            proprietor.setUserName(mobile);
            proprietor = proprietorDBService.save(proprietor);
        }
        return userLogin(mobile, null, code, 1, proprietor);
    }

    /**
     * 根据帐号类型和手机号码判断帐号是否存在
     *
     * @param accountType 帐号的类型
     * @param mobile      手机号
     * @return
     */
    public boolean isAccountExists(AccountType accountType, String mobile) {
        return getUserInfo(accountType, mobile) != null;
    }

    public User getUserInfo(AccountType accountType, String mobile) {
        User user = null;
        if (AccountType.Workman.equals(accountType))
            user = workmanDBService.findOneByMobile(mobile);
        else if (AccountType.Proprietor.equals(accountType))
            user = proprietorDBService.findOneByMobile(mobile);
        return user;
    }


    /**
     * 用户登录
     *
     * @param mobile
     * @param password
     * @param code
     * @param loginType
     * @param user
     * @return token
     */
    private UserLoginResult userLogin(String mobile, String password, String code, Integer loginType, User user) {
        ExceptionAssert.notNull(user, UserExcepFactor.ACCOUNT_NOT_EXISTS);
        if (loginType == 1) {
            String storedCode = mobileCodeService.get(mobile);
            ExceptionAssert.notBlank(storedCode, UserExcepFactor.MOBILE_CODE_TIMEOUT);
            if (!storedCode.equals(code))
                throw EngineExceptionHelper.localException(UserExcepFactor.MOBILE_CODE_ERROR);
            else
                mobileCodeService.del(mobile);            //验证码匹配成功，删除旧的验证码
        } else {
            String salt = user.getSalt();
            if (!user.getPassword().equals(PasswordUtl.encryptPassword(salt, password)))
                throw EngineExceptionHelper.localException(UserExcepFactor.USERPASS_ERROR);
        }

        //生成用户的登录
        ClientLoginStatus loginLog = clientLoginLogRespository.findByUserId(user.getId());
        if (loginLog == null)
            loginLog = new ClientLoginStatus();
        RequestContext context = ThreadLocalContext.getRequestContext();
        loginLog.setUserId(user.getId());
        loginLog.setMobile(mobile);
        loginLog.setClientPlatform(context.getPlatform());
        loginLog.setDeviceIdentification(context.getDeviceId());
        loginLog.setAppVersion(context.getAppVersion());
        loginLog.setAccountType(user.getAccountType());
        loginLog.setLastLoginTime(new Date());
        loginLog.setStatus(ClientLoginStatus.STATUS_ONLINE);
        clientLoginLogRespository.save(loginLog);

        //生成登录token
        String userToken = refreshUserToken(user.getAccountType(), user.getId());
        return new UserLoginResult(user.getId(), user.getUserName(), user.getMobile(), userToken);
    }

    /**
     * 生成或刷新用户登录专用的Token
     *
     * @param accountType
     * @param userId
     * @return
     */
    private String refreshUserToken(String accountType, String userId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        ClientLoginStatus loginStatus = clientLoginLogRespository.findByUserId(userId);
        if (loginStatus != null) {
            String oldToken = loginStatus.getToken();
            if (oldToken != null) {
                //删除原来的token
                authTokenService.del(accountType, oldToken);
            }
            loginStatus.setToken(token);
            clientLoginLogRespository.save(loginStatus);
        }
        authTokenService.put(accountType, token, userId);
        return token;
    }

    public Workman findWorkman(String id) {
        return workmanDBService.findOne(id);
    }


    /**
     * 通过手机号和设备号获取指定帐号类型的Token
     *
     * @param mobile
     * @param deviceId
     * @param accountTypeValue
     * @return
     */
    public String gainToken(String mobile, String deviceId, String accountTypeValue) {
        ExceptionAssert.notBlank(deviceId, UserExcepFactor.CLIENT_DEVICE_IDENTIFICATION_BLANK);
        AccountType accountType = null;
        if (AccountType.Proprietor.getValue().equals(accountTypeValue)) accountType = AccountType.Proprietor;
        else if (AccountType.Workman.getValue().equals(accountTypeValue)) accountType = AccountType.Workman;

        User user = getUserInfo(accountType, mobile);
        //如果帐号不存在，提示用户登录或注册
        if (user == null) {
            throw EngineExceptionHelper.localException(UserExcepFactor.CLIENT_LOGIN_REGISTER);
        }
        //如果设备号和最后一次登录的设备号不一致，提示用户重新登录
        if (!accordanceWithLastDevice(mobile, deviceId, accountType))
            throw EngineExceptionHelper.localException(UserExcepFactor.CLIENT_RE_LOGIN);

        return refreshUserToken(accountType.getValue(), user.getId());
    }

    /**
     * 判断本次设备id与最后一次登录的设备id是否一致
     *
     * @param mobile
     * @param deviceId
     * @param accountType
     * @return
     */
    private boolean accordanceWithLastDevice(String mobile, String deviceId, AccountType accountType) {
        String lastLoginDeviceId = clientLoginLogRespository.findDeviceIdentificationByMobileAndAccountType(mobile, accountType.getValue());
        return lastLoginDeviceId.equals(deviceId) && !StringUtils.isBlank(lastLoginDeviceId);
    }
}
