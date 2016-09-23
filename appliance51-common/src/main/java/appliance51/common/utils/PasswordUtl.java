package appliance51.common.utils;

import appliance51.common.encrypt.Digests;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * Created by yuananyun on 2016/9/22.
 */
public class PasswordUtl {
    public static final int HASH_INTERATIONS = 1024;

    /**
     * 加密密码
     * @param saltStr 盐值
     * @param password 要加密的密码
     * @return 加密后的密码
     */
    public static  String encryptPassword(String saltStr, String password) {
        if(password==null||"".equals(password.trim())) return password;
        byte[] salt = new byte[0];
        try {
            salt = Hex.decodeHex(saltStr.toCharArray());
        } catch (DecoderException ignored) {
        }
        byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
        return Hex.encodeHexString(hashPassword);
    }
    /**
     * 获取加密的Salt
     */
    public static  String getSalt() {
        byte[] salt = Digests.generateSalt(32);
        return Hex.encodeHexString(salt);
}
}
