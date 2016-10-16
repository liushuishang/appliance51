package appliance51.security.interceptor;

import appliance51.dao.model.AccountType;
import appliance51.security.constants.RequestHeaderConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对终端的访问请求进行过滤，判断请求是否来自终端
 * Created by yuananyun on 2016/10/10.
 */
public class TerminalAccessInterceptor implements HandlerInterceptor {
//    private static Logger logger = LoggerFactory.getLogger(TerminalAccessInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestMethod = request.getMethod();
        String customHeader = request.getHeader("access-control-request-headers");
        //如果是ajax预请求
        boolean isOptionsRequest = "options".equals(requestMethod.toLowerCase());
        String clientType = request.getHeader(RequestHeaderConstant.CLIENT_TYPE);
        if (isOptionsRequest && customHeader.contains("x-client-type")) {
            setCORSHeaders(true,response);
            return false;
        }
        if (!(AccountType.Proprietor.equal(clientType) || AccountType.Workman.equal(clientType))) {
            response.setStatus(400);
            response.getWriter().write(" illegal request");
            return false;
        }
        setCORSHeaders(false,response);

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public void setCORSHeaders(boolean isPreLight,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(!isPreLight) return;
        response.setHeader("Access-Control-Allow-Methods", "PUT, DELETE, GET, POST,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", String.format("%s,%s,%s,%s,%s", RequestHeaderConstant.CLIENT_DEVICE,
                RequestHeaderConstant.CLIENT_PLATFORM, RequestHeaderConstant.CLIENT_TOKEN, RequestHeaderConstant.CLIENT_VERSION,
                RequestHeaderConstant.CLIENT_TYPE));
        response.setHeader("Access-Control-Allow-Credentials", "false");
        response.setHeader("Access-Control-Max-Age", "1728000");
    }
}
