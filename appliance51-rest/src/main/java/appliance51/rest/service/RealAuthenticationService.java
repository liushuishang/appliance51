package appliance51.rest.service;

import appliance51.common.exception.EngineExceptionHelper;
import appliance51.common.exception.ExcepFactor;
import appliance51.common.utils.BankcardVerifyUtil;
import appliance51.common.utils.ExceptionAssert;
import appliance51.common.utils.PasswordUtl;
import appliance51.dao.domain.RealAuthenticatonLog;
import appliance51.dao.domain.UserBankCard;
import appliance51.dao.domain.Workman;
import appliance51.dao.repositories.RealAuthLogRespository;
import appliance51.dao.repositories.UserBankCardRepository;
import appliance51.rest.dto.RealAuthInfo;
import appliance51.rest.exception.UserExcepFactor;
import appliance51.security.context.RequestContext;
import appliance51.security.context.ThreadLocalContext;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 实名认证服务类
 * Created by yuananyun on 2016/10/18.
 */
@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class})
public class RealAuthenticationService {
    private static Logger logger = LoggerFactory.getLogger(RealAuthenticationService.class);

    @Value("${juhe.verifybankcard4.key}")
    private String verifybankcard4Key;


    @Autowired
    private RealAuthLogRespository realAuthLogRespository;

    @Autowired
    private UserBankCardRepository bankCardRepository;

    @Autowired
    private WorkmanDBService workmanDBService;

    /**
     * 对师傅信息进行实名认证
     *
     * @param realAuthInfo
     * @return
     */
    public boolean realAuthentication(RealAuthInfo realAuthInfo) {
        ExceptionAssert.notBlank(realAuthInfo.getBankcardNo(), ExcepFactor.E_PARAM_ERROR);
        ExceptionAssert.notBlank(realAuthInfo.getCertificateNo(), ExcepFactor.E_PARAM_ERROR);
        ExceptionAssert.notBlank(realAuthInfo.getMobile(), ExcepFactor.E_PARAM_ERROR);
        ExceptionAssert.notBlank(realAuthInfo.getMobileCode(), ExcepFactor.E_PARAM_ERROR);
        ExceptionAssert.notBlank(realAuthInfo.getPassword(), ExcepFactor.E_PARAM_ERROR);
        ExceptionAssert.notBlank(realAuthInfo.getRealName(), ExcepFactor.E_PARAM_ERROR);

        String bankNo = realAuthInfo.getBankcardNo();
        Object o=bankCardRepository.findByBankcardNo(bankNo);
        if(o!=null)
            throw EngineExceptionHelper.localException(UserExcepFactor.ACCOUNT_ALREADY_AUTHENTICATION);

        boolean result = BankcardVerifyUtil.verifybankcard4(verifybankcard4Key,
                realAuthInfo.getRealName(), realAuthInfo.getCertificateNo(),
                realAuthInfo.getBankcardNo(), realAuthInfo.getMobile());
        if (!result)
            throw EngineExceptionHelper.localException(UserExcepFactor.ACCOUNT_REAL_AUTHENTICATION_FAILURE);
        /**
         * 如果请求正常则写入数据库
         */
        bindToAccount(realAuthInfo, result);
        return result;
    }

    /**
     * 绑定
     *
     * @param realAuthInfo
     */

    private void bindToAccount(RealAuthInfo realAuthInfo, boolean result) {
        RequestContext context = ThreadLocalContext.getRequestContext();
        String userId = context.getCurrentUid();
        ExceptionAssert.notBlank(userId, UserExcepFactor.E_BAN_USER);

        RealAuthenticatonLog log = new RealAuthenticatonLog();
        log.setUserId(userId);
        log.setCreatedDate(new Date());
        log.setData(JSON.toJSONString(realAuthInfo));
        log.setResult(result ? 1 : 0);
        realAuthLogRespository.save(log);

        if (result) {
            /**
             * 把银行卡号和操作密码绑定到用户的帐号
             */
            Workman workman = workmanDBService.findOne(userId);
            if (workman==null||!realAuthInfo.getMobile().equals(workman.getMobile()))
                throw EngineExceptionHelper.localException(UserExcepFactor.E_BAN_USER);

            logger.info("【bindToAccount】开始绑定用户%s的银行卡信息", userId);
            workman.setStatus(Workman.STATUS_ALREADY_VERIFIED);
            String salt = PasswordUtl.getSalt();
            String password = PasswordUtl.encryptPassword(salt, realAuthInfo.getPassword());
            workman.setSalt(salt);
            workman.setPassword(password);
            workman.setRealName(realAuthInfo.getRealName());
            workman.setCertificateNo(realAuthInfo.getCertificateNo());
            workman.setLastUpdatedDate(new Date());
            workmanDBService.save(workman);

            UserBankCard blankCard = new UserBankCard();
            blankCard.setUserId(userId);
            blankCard.setRealName(realAuthInfo.getRealName());
            blankCard.setCertificateNo(realAuthInfo.getCertificateNo());
            blankCard.setBankcardNo(realAuthInfo.getBankcardNo());
            blankCard.setMobile(realAuthInfo.getMobile());
            blankCard.setStatus(UserBankCard.STATUS_ENABLE);
            blankCard.setBindTime(new Date());
            bankCardRepository.save(blankCard);
            logger.info("【bindToAccount】绑定用户%s的银行卡信息成功！", userId);
        }

    }


}
