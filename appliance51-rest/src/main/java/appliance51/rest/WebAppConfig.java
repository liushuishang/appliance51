package appliance51.rest;

import appliance51.security.interceptor.TerminalAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
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
}
