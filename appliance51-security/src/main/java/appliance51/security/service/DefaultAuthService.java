package appliance51.security.service;

import appliance51.security.annotation.AuthInfo;
import appliance51.security.model.AuthRequest;
import appliance51.security.model.AuthResponse;
import appliance51.security.provider.UserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-8-30 00:22.
 */
@Service
public class DefaultAuthService implements AuthService, ApplicationContextAware, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAuthService.class);
    private ApplicationContext context;

    @Resource
    private UserProvider userProvider;

    @Override
    public AuthResponse auth(AuthRequest request, Optional<AuthInfo> authInfo) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
