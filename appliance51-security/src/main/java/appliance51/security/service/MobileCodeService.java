package appliance51.security.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuananyun on 2016/9/22.
 */
@Service
public class MobileCodeService {
    private static final String KEY_PREFIX = "auth_mobile:";
    /**
     * 超时
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
}
