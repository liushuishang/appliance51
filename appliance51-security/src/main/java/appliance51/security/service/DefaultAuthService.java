package appliance51.security.service;

import appliance51.common.exception.EngineExceptionHelper;
import appliance51.security.annotation.AuthInfo;
import appliance51.security.annotation.AuthType;
import appliance51.security.model.AuthExcepFactor;
import appliance51.security.model.AuthRequest;
import appliance51.security.model.AuthResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-8-30 00:22.
 */
@Service
public class DefaultAuthService implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAuthService.class);


    @Autowired
    private AuthTokenService tokenService;

    @Override
    public AuthResponse auth(AuthRequest request, Optional<AuthInfo> authInfo) {
        /**
         * 检查客户端和请求的接口认证范围是否匹配
         */
        String clientType = request.getClientType();
        String authScopeValue = authInfo.get().authScope().getValue();
        if (!("all".equals(authScopeValue)) && !authScopeValue.equals(clientType)) {
            throw EngineExceptionHelper.localException(AuthExcepFactor.E_AUTH_ILLEGAL_REQUEST);
        }
        if (authInfo.get().needAuth() == AuthType.OPTION) return new AuthResponse("anonymous");
        /**
         *检查用户的票据是否有效
         */
        String token = request.getClientToken();
        if (StringUtils.isBlank(token)) throw EngineExceptionHelper.localException(AuthExcepFactor.E_AUTH_NO_TOKEN);
        String uid = tokenService.validate(clientType, token);
        if (StringUtils.isBlank(uid))
            throw EngineExceptionHelper.localException(AuthExcepFactor.E_AUTH_TOKEN_EXPIRES);
        return new AuthResponse(uid);
    }
}
