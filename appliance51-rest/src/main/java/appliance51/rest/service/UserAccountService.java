package appliance51.rest.service;

import appliance51.common.exception.EngineExceptionHelper;
import appliance51.common.utils.CertificateNoUtil;
import appliance51.common.utils.ExceptionAssert;
import appliance51.common.utils.PasswordUtl;
import appliance51.dao.domain.User;
import appliance51.dao.domain.Workman;
import appliance51.rest.dto.UserLoginResult;
import appliance51.rest.dto.WorkmanRegistration;
import appliance51.rest.exception.UserExcepFactor;
import appliance51.rest.model.AccountType;
import appliance51.security.service.AuthTokenService;
import appliance51.security.service.MobileCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /**
     * 师傅注册
     *
     * @param registration
     * @return
     */
    public UserLoginResult workmanRegister(WorkmanRegistration registration) {
        String mobile = registration.getMobile();
        String certificateNo = registration.getCertificateNo();
        ExceptionAssert.notBlank(mobile, UserExcepFactor.MOBILE_BLANK);
        ExceptionAssert.notBlank(certificateNo, UserExcepFactor.CERTIFICATE_NO_BLANK);
        ExceptionAssert.notBlank(registration.getPassword(), UserExcepFactor.USERPASS_BLANK);
        ExceptionAssert.notEmpty(registration.getServiceItemIdList(), UserExcepFactor.SERVICE_ITEM_EMPTY);
        ExceptionAssert.notEmpty(registration.getServiceRegionIdList(), UserExcepFactor.SERVICE_REGION_EMPTY);

        boolean isExists = isAccountExists(AccountType.Workman, mobile);
        if (isExists)
            throw EngineExceptionHelper.localException(UserExcepFactor.ACCOUNT_EXISTS, "手机号已经被注册");

        if (!CertificateNoUtil.validate(certificateNo))
            throw EngineExceptionHelper.localException(UserExcepFactor.CERTIFICATE_NO_ERROR);

        Workman man = workmanDBService.save(registration);
        if (man == null)
            throw EngineExceptionHelper.localException(UserExcepFactor.SAVE_FAILURE);

        String userToken = generateUserToken(man);
        return new UserLoginResult(man.getId(), man.getUserName(), man.getMobile(), userToken);
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
     * 根据帐号类型和手机号码判断帐号是否存在
     *
     * @param accountType 帐号的类型
     * @param mobile      手机号
     * @return
     */
    public boolean isAccountExists(AccountType accountType, String mobile) {
        User user = null;
        if (AccountType.Workman.equals(accountType))
            user = proprietorDBService.findOneByMobile(mobile);
        else if (AccountType.Proprietor.equals(accountType))
            user = workmanDBService.findOneByMobile(mobile);
        return user != null;
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
                EngineExceptionHelper.localException(UserExcepFactor.MOBILE_CODE_ERROR);
        } else {
            String salt = user.getSalt();
            if (!user.getPassword().equals(PasswordUtl.encryptPassword(salt, password)))
                EngineExceptionHelper.localException(UserExcepFactor.USERPASS_ERROR);
        }
        String userToken = generateUserToken(user);
        return new UserLoginResult(user.getId(), user.getUserName(), user.getMobile(), userToken);
    }

    /**
     * 生成用户登录专用的Token
     *
     * @param user
     * @return
     */
    private String generateUserToken(User user) {
        String token = UUID.randomUUID().toString();
        authTokenService.put(token, user.getId());
        return token;
    }


    public Workman findWorkman(String id) {
        return workmanDBService.findOne(id);
    }
}
