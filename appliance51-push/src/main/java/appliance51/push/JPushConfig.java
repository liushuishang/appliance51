package appliance51.push;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Platform;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by yuananyun on 2016/10/16.
 */
@Component
@ConfigurationProperties(prefix = "jiguang")
public class JPushConfig {

    private String appKey;
    private String masterSecret;
    private Long timeToLive;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public Long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public JPushClient getClient(){
        if(timeToLive==null||timeToLive==0) timeToLive = this.timeToLive;

        return new JPushClient(masterSecret,appKey);
    }

}
