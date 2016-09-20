package appliance51.security.annotation;

/**
 * Created by yuananyun on 2016/9/20.
 */
public enum AuthType {
    //非必须授权
    OPTION(false),
    // 必须验证
    REQUIRED(true);

    // 认证失败是否抛出异常
    private boolean authFailThrowException;

    AuthType(boolean authFailThrowException) {
        this.authFailThrowException = authFailThrowException;
    }

    public boolean authFailThrowException() {
        return this.authFailThrowException;
    }
}
