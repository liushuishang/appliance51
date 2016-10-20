package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户的银行卡信息
 * Created by yuananyun on 2016/10/18.
 */
@Entity
@Table(name = "uam_bank_card")
public class UserBankCard {
    public static int STATUS_DISABLE=0;
    public static int STATUS_ENABLE=1;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38, nullable = false)
    private String id;

    /**
     * 用户的id
     */
    @Column(columnDefinition = "varchar(38) ", nullable = false, updatable = false)
    private String userId;
    /**
     * 用户的真实姓名
     */
    @Column(columnDefinition = "varchar(100) ", nullable = false, updatable = false)
    private String realName;


    /**
     * 银行卡绑定的手机号码
     */
    @Column(columnDefinition = "varchar(15) ", nullable = false, updatable = false)
    private String mobile;

    /**
     * 身份证号码
     */
    @Column(name = "certificateNo", length = 20, nullable = false)
    private String CertificateNo;

    /**
     * 银行卡号
     */
    @Column(columnDefinition = "varchar(20) ", nullable = false, updatable = false)
    private String bankcardNo;

    /**
     * 所属银行名称
     */
    @Column(columnDefinition = "varchar(20) ", nullable = true, updatable = true)
    private String bankName;

    @Column(columnDefinition = "timestamp default now()", updatable = false)
    private Date BindTime;

    /**
     * 当前的状态，0：禁用；1：有效
     */
    @Column(columnDefinition = "tinyint default 0", updatable = true)
    private int status;
    /**
     * 最后更新时间
     */
    @Column(columnDefinition = "timestamp ", nullable = true, updatable = true)
    private Date lastUpdatedDate;


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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBankcardNo() {
        return bankcardNo;
    }

    public void setBankcardNo(String bankcardNo) {
        this.bankcardNo = bankcardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Date getBindTime() {
        return BindTime;
    }

    public void setBindTime(Date bindTime) {
        BindTime = bindTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCertificateNo() {
        return CertificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        CertificateNo = certificateNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
