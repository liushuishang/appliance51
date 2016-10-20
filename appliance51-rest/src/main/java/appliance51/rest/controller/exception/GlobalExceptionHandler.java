package appliance51.rest.controller.exception;

import appliance51.common.exception.EngineException;
import appliance51.rest.model.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Created by ucs_yuananyun on 2016/3/17.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex) {
        logger.error(ex.getLocalizedMessage());
        if (ex instanceof EngineException) {
            EngineException engineException = (EngineException) ex;
            String errorMsgCn = engineException.getErrorMsgCn();
            String errorCode = String.valueOf(engineException.getFactor().getErrorCode());
            HttpStatus status = engineException.getFactor().getStatus();
            return new ResponseEntity<>(RestResult.failure(errorMsgCn, errorCode), status);
        } else {
            return new ResponseEntity<>(RestResult.failure("操作出现异常", "500"), HttpStatus.SERVICE_UNAVAILABLE);
        }

    }


}
