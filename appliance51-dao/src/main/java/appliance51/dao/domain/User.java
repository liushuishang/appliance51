package appliance51.dao.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by yuana on 2016/9/4.
 */
@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract  class User implements Serializable{



    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38,nullable = false)
    private String id;

    @Column(name = "name", columnDefinition = "varchar(50) ",nullable = false)
    private String userName;

    @Column(columnDefinition = "varchar(50) ")
    private String password;

    @Column(columnDefinition = "varchar(100) ")
    private String salt;

    @Column(columnDefinition = "varchar(50) ")
    private String email;
    @Column(columnDefinition = "varchar(50) ",nullable = false)
    private String mobile;

    @Column(columnDefinition = "varchar(150) ")
    private String avatar;

    @Column
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date lastUpdatedDate;


    @Column(columnDefinition = "tinyint default 0",nullable = false)
    private Integer isEnable;


    @Column(columnDefinition = "int default 0",nullable = false)
    private Integer loginCount;

    public abstract String getAccountType();


    public User() {
        this.isEnable = 1;
        this.loginCount=0;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }



    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
