package appliance51.rest.exception;


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
    public static final ExcepFactor CERTIFICATE_NO_BLANK = new UserExcepFactor(HttpStatus.BAD_REQUEST, 4,
            "certificateNo can not be blank", "身份证号码不能为空");
    public static final ExcepFactor MOBILE_BLANK = new UserExcepFactor(HttpStatus.BAD_REQUEST, 5,
            "mobile can not be blank", "手机号码不能为空");
    public static final ExcepFactor USERPASS_BLANK = new UserExcepFactor(HttpStatus.BAD_REQUEST, 6,
            "password can not be blank", "登录密码不能为空");
    public static final ExcepFactor CERTIFICATE_NO_ERROR = new UserExcepFactor(HttpStatus.BAD_REQUEST, 7,
            "certificateNo invalid", "身份证号码无效");
    public static final ExcepFactor SAVE_FAILURE = new UserExcepFactor(HttpStatus.BAD_REQUEST, 8,
            "user save failure", "用户信息保存失败");
    public static final ExcepFactor SERVICE_ITEM_EMPTY = new UserExcepFactor(HttpStatus.BAD_REQUEST, 9,
            "", "可提供服务的项目不能为空");
    public static final ExcepFactor SERVICE_REGION_EMPTY = new UserExcepFactor(HttpStatus.BAD_REQUEST, 10,
            "", "可提供服务的区域不能为空");
    public static final ExcepFactor MOBILE_CODE_TIMEOUT= new UserExcepFactor(HttpStatus.BAD_REQUEST, 11,
            "", "手机验证码已超时");
    public static final ExcepFactor MOBILE_CODE_ERROR = new UserExcepFactor(HttpStatus.BAD_REQUEST, 12,
            "", "手机验证码错误");
    public static final ExcepFactor  CLIENT_DEVICE_IDENTIFICATION_BLANK= new UserExcepFactor(HttpStatus.BAD_REQUEST, 13,
            "", "设备标识不能为空");
    public static final ExcepFactor CLIENT_LOGIN_REGISTER = new UserExcepFactor(HttpStatus.BAD_REQUEST, 14,
            "", "请登录或注册");
    public static final ExcepFactor CLIENT_RE_LOGIN= new UserExcepFactor(HttpStatus.BAD_REQUEST, 15,
            "", "请重新登录");

    public static final ExcepFactor ACCOUNT_REAL_AUTHENTICATION_FAILURE= new UserExcepFactor(HttpStatus.BAD_REQUEST, 16,
            "RealAuthentication failure!", "实名认证失败");

    public static final ExcepFactor ACCOUNT_ALREADY_AUTHENTICATION =new UserExcepFactor(HttpStatus.BAD_REQUEST, 17,
            "RealAuthentication repeat!", "银行卡已被实名认证");


    protected UserExcepFactor(HttpStatus httpStatus, int errorCode, String errorMsg, String errorMsgCn) {
        super(GlobalConstants.USER_ID_AUTH, httpStatus, errorCode, errorMsg, errorMsgCn);
    }

}
