package appliance51.security.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuananyun on 2016/9/20.
 */
public class RequestContext implements Serializable, JSONAware {
    private static final long serialVersionUID = -402232948972687045L;

    @JSONField(name = "request_id")
    private String requestId;
    @JSONField(name = "current_uid")
    private String currentUid;
    private String ip;
    @JSONField(name = "app_id")
    private int appId;

    @JSONField(name = "platform")
    private String platform;

    private Map<String, Object> attribute;

    private transient HttpServletRequest originRequest;
    private String deviceId;
    private String appVersion;
    private String clientType;

    public RequestContext(String requestId) {
        this.requestId = requestId;
        attribute = new HashMap<>();
    }

    public String getCurrentUid() {
        return currentUid;
    }

    public void setCurrentUid(String currentUid) {
        this.currentUid = currentUid;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }


    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Map<String, Object> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    // 貌似是jackson的bug,transient 变量的 annotation必须加到方法上才起作用
    @JSONField(serialize = false)
    public HttpServletRequest getOriginRequest() {
        return originRequest;
    }

    public void setOriginRequest(HttpServletRequest originRequest) {
        this.originRequest = originRequest;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + appId;
//        result = prime * result + (int) (currentUid ^ (currentUid >>> 32));
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RequestContext other = (RequestContext) obj;
        if (appId != other.appId)
            return false;

        if (currentUid != other.currentUid)
            return false;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        if (requestId == null) {
            if (other.requestId != null)
                return false;
        } else if (!requestId.equals(other.requestId))
            return false;
        return true;
    }

    @Override
    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientType() {
        return clientType;
    }
}
