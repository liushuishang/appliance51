package appliance51.rest.model;

import com.google.common.base.Strings;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuananyun on 2016/9/11.
 */
public class RestResult {
    private int s;
    private String m;
    private Object r;

    public RestResult(int s, String m, Object r) {
        this.s = s;
        this.m = m;
        this.r = r;
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
}
