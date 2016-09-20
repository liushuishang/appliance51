package appliance51.rest.model;

import com.google.common.base.Strings;

import java.io.Serializable;

/**
 * Created by yuananyun on 2016/9/11.
 */
public class RestResult implements Serializable {
    private int status;
    private String message;
    private Object data;

    public RestResult(int s, String m, Object data) {
        this.status = s;
        this.message = m;
        this.data=data;
    }

    public static RestResult success(Object data) {
        return new RestResult(1, null, data);
    }

    public static RestResult failure(String errorMsg) {
        return new RestResult(0, errorMsg, null);
    }

    public static  RestResult getRestResult(String errorMsg, Object data) {
        return Strings.isNullOrEmpty(errorMsg) ? RestResult.success(data) : RestResult.failure(errorMsg);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
