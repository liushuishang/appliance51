package appliance51.dao.service;

import appliance51.common.encrypt.Digests;
import appliance51.common.exception.EngineExceptionHelper;
import appliance51.dao.domain.User;
import appliance51.dao.repositories.ProprietorRespository;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-10-13 17:02
 */
@Service
public class UserService {
    public static final int HASH_INTERATIONS = 1024;

    @Resource
    private ProprietorRespository dao;

    public boolean save(User user) {
//        User existsUser = dao.findByUserName(user.getUserName());
//        if (existsUser != null) {
//            throw EngineExceptionHelper.localException(UserExcepFactor.ACCOUNT_EXISTS);
//        }
//
//        entryptPassword(user);
//        user = dao.save(user);
//        return user.getUid() > 0;
        return false;
    }

    public User get(String id) {
        return dao.findOne(id);
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(127);
        user.setSalt(Hex.encodeHexString(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Hex.encodeHexString(hashPassword));
    }

    private String entryptPassword(String saltStr, String password) {

        byte[] salt = new byte[0];
        try {
            salt = Hex.decodeHex(saltStr.toCharArray());
        } catch (DecoderException ignored) {
        }
        byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
        return Hex.encodeHexString(hashPassword);
    }

    public User login(String loginName, String password) {
//        User user = dao.findByUsername(loginName);
//        if (user != null && StringUtils.equals(entryptPassword(user.getSalt(), password), user.getPassword())) {
//            return user;
//        }
//        throw EngineExceptionHelper.localException(AuthExcepFactor.E_AUTH_PASSWORD_ERROR);
        return null;
    }
}
