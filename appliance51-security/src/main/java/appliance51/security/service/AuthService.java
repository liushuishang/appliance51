package appliance51.security.service;

import appliance51.security.annotation.AuthInfo;
import appliance51.security.model.AuthRequest;
import appliance51.security.model.AuthResponse;

import java.util.Optional;

/**
 * Created by yuananyun on 2016/9/20.
 */
public interface AuthService {
    String ENGINE_REMOTEIP_HEADER = "X-Engine-RemoteIP";
    String ENGINE_UID_HEADER = "X-Engine-UID";
    String ENGINE_APPID_HEADER = "X-Engine-APPID";
    AuthResponse auth(AuthRequest request, Optional<AuthInfo> authInfo);
}
