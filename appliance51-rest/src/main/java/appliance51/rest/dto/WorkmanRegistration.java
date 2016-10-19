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

    @ApiModelProperty(value = "用户名",name = "用户名", required = true)
    private String userName;
    @ApiModelProperty(value = "手机号码", required = true)
    private String mobile;
//    @ApiModelProperty(value = "登录密码", required = false)
//    private String password;
    @ApiModelProperty(value = "手机动态验证码", required = false)
    private String mobileCode;
    @ApiModelProperty(value = "服务项目ID列表", required = true)
    private List<String> serviceItemIdList;
    @ApiModelProperty(value = "服务地区区划代码列表", required = true)
    private List<String> serviceAdCodeList;



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

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }


    public List<String> getServiceItemIdList() {
        return serviceItemIdList;
    }

    public void setServiceItemIdList(List<String> serviceItemIdList) {
        this.serviceItemIdList = serviceItemIdList;
    }




    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public List<String> getServiceAdCodeList() {
        return serviceAdCodeList;
    }

    public void setServiceAdCodeList(List<String> serviceAdCodeList) {
        this.serviceAdCodeList = serviceAdCodeList;
    }
}
