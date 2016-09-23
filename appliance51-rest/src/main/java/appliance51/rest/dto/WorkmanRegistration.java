package appliance51.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by yuananyun on 2016/9/22.
 */
@ApiModel
public class WorkmanRegistration {

    @ApiModelProperty(name = "用户名", required = true)
    private String userName;
    @ApiModelProperty(name = "手机号码", required = true)
    private String mobile;
    @ApiModelProperty(name = "登录密码", required = false)
    private String password;
    @ApiModelProperty(name = "手机动态验证码", required = false)
    private String mobileCode;
    @ApiModelProperty(name = "身份证号码", required = true)
    private String CertificateNo;
    @ApiModelProperty(name = "服务项目ID列表", required = true)
    private List<String> serviceItemIdList;
    @ApiModelProperty(name = "服务地区ID列表", required = true)
    private List<String> serviceRegionIdList;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCertificateNo() {
        return CertificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        CertificateNo = certificateNo;
    }

    public List<String> getServiceItemIdList() {
        return serviceItemIdList;
    }

    public void setServiceItemIdList(List<String> serviceItemIdList) {
        this.serviceItemIdList = serviceItemIdList;
    }

    public List<String> getServiceRegionIdList() {
        return serviceRegionIdList;
    }

    public void setServiceRegionIdList(List<String> serviceRegionIdList) {
        this.serviceRegionIdList = serviceRegionIdList;
    }


    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }
}
