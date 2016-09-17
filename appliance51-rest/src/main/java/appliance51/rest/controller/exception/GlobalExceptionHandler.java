//package appliance51.rest.controller.exception;
//
//import appliance51.rest.model.RestResult;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.IOException;
//import java.net.SocketTimeoutException;
//import java.net.UnknownHostException;
//
///**
// * Created by ucs_yuananyun on 2016/3/17.
// */
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(IOException.class)
//    public ResponseEntity<Object> handleIOException(Exception ex) {
//        return redirectToErrorPage("可能是数据源拒绝了数据请求！");
//    }
//
//    @ExceptionHandler(SocketTimeoutException.class)
//    public ResponseEntity<Object> handleSocketTimeoutException(Exception ex) {
//        return redirectToErrorPage("连接超时，请检查您的网络！");
//    }
//
//    @ExceptionHandler(UnknownHostException.class)
//    public ResponseEntity<Object> handleUnknownHostException(Exception ex) {
//        return redirectToErrorPage("网络不通，请检查您的网络！");
//    }
//
//    private ResponseEntity<Object> redirectToErrorPage(String message) {
//        return new ResponseEntity<>(RestResult.failure(message), HttpStatus.SERVICE_UNAVAILABLE);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleAllException(Exception ex) {
//        String message = ex.getMessage();
//        if (message.contains("SocketTimeoutException"))
//            return handleSocketTimeoutException(ex);
//        if (message.contains("UnknownHostException"))
//            return handleUnknownHostException(ex);
//        if (message.contains("IOException")) {
//            return handleIOException(ex);
//        }
//
//        return redirectToErrorPage( message);
//    }
//
//
//}
