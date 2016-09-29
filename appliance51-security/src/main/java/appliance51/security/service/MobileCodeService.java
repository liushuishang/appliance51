package appliance51.security.service;

import appliance51.common.exception.ExcepFactor;
import appliance51.common.utils.ExceptionAssert;
import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuananyun on 2016/9/22.
 */
@Service
public class MobileCodeService {
    private static Logger logger = LoggerFactory.getLogger(MobileCodeService.class);
    private static final String KEY_PREFIX = "auth_mobile:";

    @Value("${mobile.appkey}")
    private String appkey;
    @Value("${mobile.appSecret}")
    private String appSecret;
    @Value("${mobile.smsUrl}")
    private String smsUrl;

    @Value("${mobile.smsFreeSignName}")
    private String smsFreeSignName;

    @Value("${mobile.smsTemplateCode}")
    private String smsTemplateCode;

    /**
     * 超时,5分钟
     */
    private static int expires = 1000 * 60 * 5;

    @Resource(name = "restApiRedis")
    private StringRedisTemplate redisTemplate;

    public String get(String mobile) {
        return redisTemplate.boundValueOps(getKey(mobile)).get();
    }


    public void put(String mobile, String code) {
        redisTemplate.boundValueOps(getKey(mobile)).set(code, expires, TimeUnit.SECONDS);
    }

    private String getKey(String mobile) {
        return KEY_PREFIX + mobile;
    }


    /**
     * 发送手机验证码
     *
     * @param mobile
     * @return
     */
    public boolean sendMobileCode(String mobile, String uid) {
        if(StringUtils.isBlank(mobile)) return false;

        TaobaoClient client = new DefaultTaobaoClient(smsUrl, appkey, appSecret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend(uid);
        req.setSmsType("normal");
        req.setSmsFreeSignName(smsFreeSignName);
        String mobileCode = getRandNum(6);
        req.setSmsParamString(String.format("{number:'%s'}", mobileCode));
        req.setRecNum(mobile);
        req.setSmsTemplateCode(smsTemplateCode);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            logger.info("【sendMobileCode】发送的请求为：{}", JSON.toJSONString(req));
            rsp = client.execute(req);
            logger.info("【sendMobileCode】收到的响应为：{}", rsp.getBody());
            if (rsp.getResult().getSuccess()) {
                //把手机动态验证码加入redis缓存
                this.put(mobile, mobileCode);
            }
            return true;
        } catch (ApiException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    private String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    private int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }

}
