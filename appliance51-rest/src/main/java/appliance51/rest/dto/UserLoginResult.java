package appliance51.rest.dto;

/**
 * Created by yuananyun on 2016/9/22.
 */
public class UserLoginResult {
    private String uid;
    private String userName;
    private String mobile;
    private String Token;

    public UserLoginResult(String uid, String userName, String mobile, String token) {
        this.uid = uid;
        this.userName = userName;
        this.mobile = mobile;
        Token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
