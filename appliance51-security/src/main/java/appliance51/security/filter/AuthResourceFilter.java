package appliance51.security.filter;

import appliance51.security.annotation.AuthInfo;
import appliance51.security.constants.RequestHeaderConstant;
import appliance51.security.context.RequestContext;
import appliance51.security.context.ThreadLocalContext;
import appliance51.security.exception.AuthException;
import appliance51.security.model.AuthRequest;
import appliance51.security.model.AuthResponse;
import appliance51.security.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

/**拦截资源请求授权
 * Created by yuananyun on 2016/9/20.
 */
//@Component
public class AuthResourceFilter extends RequestMappingHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthResourceFilter.class);

    public static final String ERROR_PATH = "/error";

    @Resource(name = "defaultAuthService")
    private AuthService authService;

//    @Resource
//    private CounterService counterService;

    @Override
    protected ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        if (StringUtils.equals(request.getRequestURI(), ERROR_PATH)) {
            return super.handleInternal(request, response, handlerMethod);
        }

        RequestContext context = ThreadLocalContext.getRequestContext();
        context.setAppVersion(request.getHeader(RequestHeaderConstant.CLIENT_VERSION));
        context.setDeviceId(request.getHeader(RequestHeaderConstant.CLIENT_DEVICE));
        if(StringUtils.isBlank(context.getDeviceId()))
            context.setDeviceId(context.getRequestId());
        context.setPlatform(request.getHeader(RequestHeaderConstant.CLIENT_PLATFORM));
        context.setClientType(request.getHeader(RequestHeaderConstant.CLIENT_TYPE));
        context.setOriginRequest(request);


        Method method = handlerMethod.getMethod();
        AuthInfo authInfo=null;
        if (method.isAnnotationPresent(AuthInfo.class)) {
            authInfo = method.getAnnotation(AuthInfo.class);
        }else{
            //没有表明认证信息则默认授权
            return super.handleInternal(request, response, handlerMethod);
        }
        AuthResponse authResponse;
        try {
            authResponse = authService.auth(new AuthRequest(request), Optional.ofNullable(authInfo));
            context.setCurrentUid(authResponse.getUid());
        } catch (AuthException e) {
            logger.debug("auth failed! path: " + request.getRequestURI() + " clientType: " + request.getHeader(RequestHeaderConstant.CLIENT_TYPE)
                    + " deviceId: " + request.getHeader(RequestHeaderConstant.CLIENT_DEVICE) + " appVersion:" + request.getHeader(RequestHeaderConstant.CLIENT_VERSION));
            throw e;
        }
//        counterService.increment(StringUtils.substring(StringUtils.replace(request.getRequestURI(), "/", "."), 1));
        return super.handleInternal(request, response, handlerMethod);
    }
}
