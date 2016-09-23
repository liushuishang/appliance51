package appliance51.rest.model;

import com.google.common.base.Strings;

import java.io.Serializable;

/**
 * Created by yuananyun on 2016/9/11.
 */
public class RestResult implements Serializable {
    private boolean success;
    private String message;
    private Object data;
    private String errorCode;

    public RestResult(boolean success, String message, Object data, String errorCode) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errorCode = errorCode;
    }

    public static RestResult success(Object data){
        return new RestResult(true,null,data,null);
    }

    public static RestResult failure(String errorMsg,String errorCode){
        return new RestResult(false,errorMsg,null,errorCode);

    }

    public static  RestResult getRestResult(String errorMsg, Object data) {
        return Strings.isNullOrEmpty(errorMsg) ? RestResult.success(data) : RestResult.failure(errorMsg,null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
