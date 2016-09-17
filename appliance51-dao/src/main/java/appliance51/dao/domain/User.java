package appliance51.dao.domain;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by yuana on 2016/9/4.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("default")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @NotNull
    @Column(name = "name", columnDefinition = "varchar(50) ")
    private String name;

    @Column(columnDefinition = "varchar(20) ")
    private String password;

    @Column(columnDefinition = "varchar(50) ")
    private String email;
    @NotNull
    @Column(columnDefinition = "varchar(50) ")
    private String mobile;

    @Column(columnDefinition = "varchar(150) ")
    private String avatar;
    @NotNull
    @Column(columnDefinition = "timestamp default now()")
    private Date createdDate;

    @NotNull
    @Column(columnDefinition = "tinyint default 0")
    private Integer isEnable;

    @NotNull
    @Column(columnDefinition = "int default 0")
    private Integer loginCount;

    @Column(columnDefinition = "varchar(10) ")
    private String accountType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
