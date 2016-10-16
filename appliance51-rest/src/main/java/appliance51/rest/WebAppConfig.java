package appliance51.rest;

import appliance51.security.constants.RequestHeaderConstant;
import appliance51.security.interceptor.TerminalAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ucs_yuananyun on 2016/5/19.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TerminalAccessInterceptor()).addPathPatterns("/common/**").addPathPatterns("/proprietor/**").addPathPatterns("/workman/**");
//        registry.addInterceptor(new TerminalAccessInterceptor()).addPathPatterns("/**");
    }

    /**
     * 设置CORS属性
     *
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
//                .allowedHeaders(RequestHeaderConstant.CLIENT_TOKEN)
//                .allowedHeaders(RequestHeaderConstant.CLIENT_PLATFORM)
//                .allowedHeaders(RequestHeaderConstant.CLIENT_TYPE)
//                .allowedHeaders(RequestHeaderConstant.CLIENT_VERSION)
//                .allowCredentials(false).maxAge(1728000);//20天
//    }
}
