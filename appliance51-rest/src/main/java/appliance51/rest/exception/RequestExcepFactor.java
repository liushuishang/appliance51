package appliance51.rest.exception;

import appliance51.common.exception.ExcepFactor;
import appliance51.common.utils.GlobalConstants;
import org.springframework.http.HttpStatus;

/**
 * Created by yuananyun on 2016/10/17.
 */
public class RequestExcepFactor extends ExcepFactor {

    public static final ExcepFactor REQUEST_FAILURE = new RequestExcepFactor(HttpStatus.BAD_REQUEST, 1, "operate failure!", "操作失败！");
    /**
     * 实体保存失败
     */
    public static final ExcepFactor REQUEST_ENTITY_SAVED_ERROR = new RequestExcepFactor(HttpStatus.SERVICE_UNAVAILABLE, 2, "save failure！.", "保存失败！");


    protected RequestExcepFactor(HttpStatus httpStatus, int errorCode, String errorMsg, String errorMsgCn) {
        super(GlobalConstants.REQUEST_ID, httpStatus, errorCode, errorMsg, errorMsgCn);
    }
}
