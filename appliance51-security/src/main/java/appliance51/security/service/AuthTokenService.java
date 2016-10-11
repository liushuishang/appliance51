package appliance51.security.service;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuananyun on 2016/9/21.
 */
@Service
public class AuthTokenService {
    private static final String KEY_PREFIX = "auth_token:";

    /**
     * 超时 2个小时
     */
    private static int expires = 1000 * 60 * 60 * 2;


    @Resource(name = "restApiRedis")
    private StringRedisTemplate authRedisTemplate;

    /**
     * 验证一个token是否合法
     *
     * @param token
     * @return token对应的用户的Id
     */
    public String validate(String accountType,String token) {
        return authRedisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                String key = getTokenKey(accountType,token);
                BoundValueOperations<String, String> valueOperations = authRedisTemplate.boundValueOps(key);
                String uid = valueOperations.get();
                if (!StringUtils.isBlank(uid))
                    valueOperations.set(key, expires, TimeUnit.SECONDS);
                return uid;
            }
        });
    }

    /**
     * 放入一个Token
     *
     * @param token
     * @param uid
     */
    public void put(String accountType,String token, String uid) {
        String key = getTokenKey(accountType,token);
        BoundValueOperations<String, String> valueOperations = authRedisTemplate.boundValueOps(key);
        valueOperations.set(uid, expires, TimeUnit.SECONDS);
    }

    /**
     * 获取Token在redis中的key
     * @param token
     * @return
     */
    private String getTokenKey(String accountType,String token) {
        return KEY_PREFIX + DigestUtils.md5Hex(accountType+token);
    }
}
