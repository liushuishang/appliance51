package appliance51.common.encrypt;

/**
 * 加密解密异常
 *
 */
public class EncrypterException extends RuntimeException {

    public EncrypterException() {
        super();
    }

    public EncrypterException(String message) {
        super(message);
    }

    public EncrypterException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncrypterException(Throwable cause) {
        super(cause);
    }

    protected EncrypterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
