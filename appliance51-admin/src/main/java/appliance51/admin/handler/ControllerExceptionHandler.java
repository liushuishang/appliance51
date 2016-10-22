package appliance51.admin.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuananyun on 2016/9/11.
 */
@Component
public class ControllerExceptionHandler implements HandlerExceptionResolver {
    private static Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
        ModelAndView model = new ModelAndView("error/500");
        model.addObject("error", ex.getMessage());
        return model;

    }

}
