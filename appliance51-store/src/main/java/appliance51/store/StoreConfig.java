package appliance51.store;

import com.qiniu.util.Auth;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by yuananyun on 2016/10/11.
 */
@Component
@ConfigurationProperties(prefix = "qiniu")
public class StoreConfig {

    private String accessKey;
    private String secretKey;
    private String defaultBucketName;
    private String bucketDomain;

    private Auth auth;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getDefaultBucketName() {
        return defaultBucketName;
    }

    public void setDefaultBucketName(String defaultBucketName) {
        this.defaultBucketName = defaultBucketName;
    }

    public Auth getAuth() {
        if (auth == null)
            auth = Auth.create(accessKey, secretKey);
        return auth;
    }

    public String getUploadToken(String bucketName, String key) {
        if (StringUtils.isEmpty(bucketName)) bucketName = this.defaultBucketName;
        if (!StringUtils.isEmpty(key))
            return getAuth().uploadToken(bucketName, key);
        else return getAuth().uploadToken(bucketName);
    }

    public String getBucketDomain() {
        return bucketDomain;
    }

    public void setBucketDomain(String bucketDomain) {
        this.bucketDomain = bucketDomain;
    }
}
