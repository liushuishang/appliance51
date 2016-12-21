package appliance51.dao.domain;

import appliance51.dao.model.AccountType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yuana on 2016/9/4.
 */
@Entity
@Table(name = "uam_workman")
public class Workman extends User {

    public  static int STATUS_NOT_VERIFIED=0;
    public  static int STATUS_ALREADY_VERIFIED=1;
    public  static int STATUS_DISABLE=2;

    @Column(columnDefinition = "varchar(50) ")
    private String realName;

    /**
     * 帐号的状态，0：未验证；1：已实名验证；2：已禁用
     */
    @Column(name = "status", columnDefinition = "int default 0 ",nullable = false)
    private Integer status;

    /**
     * 身份证号码
     */
    @Column(name = "certificateNo", length = 20, nullable = true)
    private String certificateNo;

    /**
     * 二次验证密码
     */
    @Column(columnDefinition = "varchar(50) ")
    private String validationPassword;

    @Column(columnDefinition = "tinyint default 0")
    private Boolean isRealAuth;



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String value) {
        this.certificateNo = value;
    }

    @Override
    public String getAccountType() {
        return AccountType.Workman.getValue();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getValidationPassword() {
        return validationPassword;
    }

    public void setValidationPassword(String validationPassword) {
        this.validationPassword = validationPassword;
    }

    public Boolean getRealAuth() {
        return isRealAuth;
    }

    public void setRealAuth(Boolean realAuth) {
        isRealAuth = realAuth;
    }
}
