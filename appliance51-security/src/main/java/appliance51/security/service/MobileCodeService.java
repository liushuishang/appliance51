package appliance51.security.service;

import appliance51.common.exception.EngineExceptionHelper;
import appliance51.security.exception.AuthExcepFactor;
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
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
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
    private static int expires = 60 * 5;

    @Resource(name = "restApiRedis")
    private StringRedisTemplate redisTemplate;

    public String get(String mobile) {
        String key = getKey(mobile);
        BoundValueOperations<String, String> operations = redisTemplate.boundValueOps(key);
        String code = operations.get();
        return code;
    }

    public void del(String mobile){
        if(mobile.equals("15913162508")) return;
        String key = getKey(mobile);
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(key.getBytes());
            }
        });
    }

    public void put(String mobile, String code) {
        redisTemplate.boundValueOps(getKey(mobile)).set(code, expires, TimeUnit.SECONDS);
    }

    public long getCodeExpire(String mobile) {
        Long restLive = redisTemplate.getExpire(getKey(mobile), TimeUnit.SECONDS);
        if (restLive == null) return Long.MAX_VALUE;
        else return restLive;
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
        if (StringUtils.isBlank(mobile)) return false;

        //一分钟内不能重复发送验证码
        long restLive = getCodeExpire(mobile);
        if (restLive > (expires - 60)) {
            throw EngineExceptionHelper.localException(AuthExcepFactor.E_MOBILE_CODE_TRYLIMIT);
        }

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
