package appliance51.security.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class AuthResponse implements Serializable {
    private static final long serialVersionUID = 5453139780159342985L;

    private final long uid;
    private final Map<String, Object> attributes;
    private final String ip;
    private final int appId;
    private final String authedBy;
    private final String platform;

    public AuthResponse(String platform, long uid, String ip, int appId, String authedBy) {
        this.platform = platform;
        this.uid = uid;
        this.attributes = new HashMap<>();
        this.ip = ip;
        this.appId = appId;
        this.authedBy = authedBy;
    }

    public long getUid() {
        return uid;
    }

    public void setAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    public Set<Map.Entry<String, Object>> getAttributes() {
        return attributes.entrySet();
    }

    public String getIp() {
        return ip;
    }

    public int getAppId() {
        return appId;
    }

    public String getAuthedBy() {
        return authedBy;
    }

    public String getPlatform() {
        return platform;
    }

}
