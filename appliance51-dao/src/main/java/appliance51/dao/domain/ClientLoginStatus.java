package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 客户端登录日志表
 * Created by yuananyun on 2016/10/10.
 */
@Entity
@Table(name = "status_client_login", indexes = {@Index(name = "INDEX_USERID", unique = true, columnList = "userId")})
public class ClientLoginStatus {
    /**
     * 离线
     */
    public static int STATUS_OFFLINE = 0;
    /**
     * 在线
     */
    public static int STATUS_ONLINE = 1;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38, nullable = false)
    private String id;

    @Column(columnDefinition = "varchar(38)", nullable = false)
    private String userId;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String accountType;

    @Column(columnDefinition = "varchar(20)", nullable = true)
    private String clientPlatform;

    @Column(columnDefinition = "varchar(50)", nullable = true)
    private String deviceIdentification;

    @Column(columnDefinition = "varchar(20)", nullable = true)
    private String appVersion;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String mobile;

    @Column(columnDefinition = "tinyint default 1", nullable = false)
    private int status;

    @Column(columnDefinition = "timestamp default now()", updatable = true, nullable = false)
    private Date lastLoginTime;

    @Column(columnDefinition = "timestamp default now()", updatable = true, nullable = false)
    private Date lastLogoutTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getClientPlatform() {
        return clientPlatform;
    }

    public void setClientPlatform(String clientPlatform) {
        this.clientPlatform = clientPlatform;
    }

    public String getDeviceIdentification() {
        return deviceIdentification;
    }

    public void setDeviceIdentification(String deviceIdentification) {
        this.deviceIdentification = deviceIdentification;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Date getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(Date lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
