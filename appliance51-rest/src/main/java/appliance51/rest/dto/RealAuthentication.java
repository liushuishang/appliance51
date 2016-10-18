package appliance51.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实名认证用的dto
 * Created by yuananyun on 2016/10/17.
 */
@ApiModel
public class RealAuthentication {

    @ApiModelProperty(value = "手机号码", required = true)
    private String mobile;
    @ApiModelProperty(value = "手机动态验证码", required = true)
    private String mobileCode;
    @ApiModelProperty(name = "身份证号码", required = true)
    private String CertificateNo;
    @ApiModelProperty(name = "身份证正面图片", required = true)
    private String CertificatePicture1;
    @ApiModelProperty(name = "身份证背面图片", required = true)
    private String CertificatePicute2;
    @ApiModelProperty(name = "银行卡号码", required = true)
    private String bankcardNo;
    @ApiModelProperty(name = "二次验证密码", required = true)
    private String password;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public String getCertificateNo() {
        return CertificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        CertificateNo = certificateNo;
    }

    public String getCertificatePicture1() {
        return CertificatePicture1;
    }

    public void setCertificatePicture1(String certificatePicture1) {
        CertificatePicture1 = certificatePicture1;
    }

    public String getCertificatePicute2() {
        return CertificatePicute2;
    }

    public void setCertificatePicute2(String certificatePicute2) {
        CertificatePicute2 = certificatePicute2;
    }

    public String getBankcardNo() {
        return bankcardNo;
    }

    public void setBankcardNo(String bankcardNo) {
        this.bankcardNo = bankcardNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
