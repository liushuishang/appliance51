//package appliance51.rest.controller.exception;
//
//import appliance51.rest.model.RestResult;
//import com.alibaba.fastjson.JSON;
//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
///**
// * Created by yuananyun on 2016/9/17.
// */
//@ControllerAdvice
//public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<>(JSON.toJSONString(RestResult.failure(ex.getMessage())), status);
//    }
//
//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode == null) {
////            return HttpStatus.INTERNAL_SERVER_ERROR;
//            return HttpStatus.BAD_REQUEST;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
//
//}