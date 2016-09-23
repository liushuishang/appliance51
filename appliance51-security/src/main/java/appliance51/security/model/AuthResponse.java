package appliance51.security.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class AuthResponse implements Serializable {
    private static final long serialVersionUID = 5453139780159342985L;

    private String  uid;
    private Map<String, Object> attributes;
    private String ip;
    private int appId;
    private String platform;


    public AuthResponse(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
