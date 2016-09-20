package appliance51.dao.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;

/**
 * Created by yuana on 2016/9/4.
 */
@Entity
@Table(name = "uam_user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "accountType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("default")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "name", columnDefinition = "varchar(50) ",nullable = false)
    @javax.validation.constraints.NotNull(message = "登录名不能为空")
    @Pattern(regexp = "[a-zA-Z0-9_]{5,10}", message = "用户名格式不正确")
    private String userName;

    @Column(columnDefinition = "varchar(20) ")
//    @Size(min = 5, max = 10, message = "{password.length.illegal}")
    private String password;

    @Column(columnDefinition = "varchar(20) ")
    private String salt;

    @Column(columnDefinition = "varchar(50) ")
    private String email;
    @Column(columnDefinition = "varchar(50) ",nullable = false)
    private String mobile;

    @Column(columnDefinition = "varchar(150) ")
    private String avatar;

    @Column(columnDefinition = "timestamp default now()",nullable = false)
    private Date createdDate;


    @Column(columnDefinition = "tinyint default 0",nullable = false)
    private Integer isEnable;


    @Column(columnDefinition = "int default 0",nullable = false)
    private Integer loginCount;

    @Column(columnDefinition = "varchar(10)" ,insertable = false,updatable = false)
    private String accountType;

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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
