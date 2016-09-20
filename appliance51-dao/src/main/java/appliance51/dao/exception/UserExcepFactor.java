package appliance51.dao.exception;


import appliance51.common.exception.ExcepFactor;
import appliance51.common.utils.GlobalConstants;
import org.springframework.http.HttpStatus;

public class UserExcepFactor extends ExcepFactor {

    public static final UserExcepFactor ACCOUNT_EXISTS = new UserExcepFactor(HttpStatus.BAD_REQUEST, 1,
            "account exists", "账号已存在");
    public static final UserExcepFactor ACCOUNT_NOT_EXISTS = new UserExcepFactor(HttpStatus.BAD_REQUEST, 2,
            "account not exists", "账号不存在");
    public static final UserExcepFactor USERPASS_ERROR = new UserExcepFactor(HttpStatus.BAD_REQUEST, 3,
            "username password error", "用户名或密码错误");

    protected UserExcepFactor(HttpStatus httpStatus, int errorCode, String errorMsg, String errorMsgCn) {
        super(GlobalConstants.USER_ID_AUTH, httpStatus, errorCode, errorMsg, errorMsgCn);
    }

}
