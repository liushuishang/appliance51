package appliance51.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实名认证用的dto
 * Created by yuananyun on 2016/10/17.
 */
@ApiModel
public class RealAuthInfo {

    @ApiModelProperty(value = "真实姓名", required = true)
    private String realName;
    @ApiModelProperty(value = "手机号码", required = true)
    private String mobile;
    @ApiModelProperty(value = "手机动态验证码", required = true)
    private String mobileCode;
    @ApiModelProperty(value = "身份证号码", required = true)
    private String certificateNo;
    @ApiModelProperty(value = "银行卡号码", required = true)
    private String bankcardNo;
    @ApiModelProperty(value = "二次验证密码", required = true)
    private String validationPassword;


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
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }



    public String getBankcardNo() {
        return bankcardNo;
    }

    public void setBankcardNo(String bankcardNo) {
        this.bankcardNo = bankcardNo;
    }

    public String getValidationPassword() {
        return validationPassword;
    }

    public void setValidationPassword(String validationPassword) {
        this.validationPassword = validationPassword;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
