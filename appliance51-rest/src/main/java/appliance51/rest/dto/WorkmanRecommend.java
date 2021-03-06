package appliance51.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by  yuananyun on 2016/12/19.
 */
@ApiModel
public class WorkmanRecommend {
    @ApiModelProperty(value = "编号")
    public String id;
    @ApiModelProperty(value = "手机号码")
    public String mobile;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "注册时间")
    private Date regtime;
    @ApiModelProperty(value = "是否已经实名认证")
    private boolean isRealAuth;
    @ApiModelProperty(value = "好评数")
    private int praiseCount;
    @ApiModelProperty(value = "中评数")
    private int ordinaryCount;
    @ApiModelProperty(value = "差评数")
    private int badCount;

    @ApiModelProperty(value = "成功的订单数")
    private int successOrderCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public boolean isRealAuth() {
        return isRealAuth;
    }

    public void setRealAuth(boolean realAuth) {
        isRealAuth = realAuth;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getOrdinaryCount() {
        return ordinaryCount;
    }

    public void setOrdinaryCount(int ordinaryCount) {
        this.ordinaryCount = ordinaryCount;
    }

    public int getBadCount() {
        return badCount;
    }

    public void setBadCount(int badCount) {
        this.badCount = badCount;
    }

    public int getSuccessOrderCount() {
        return successOrderCount;
    }

    public void setSuccessOrderCount(int successOrderCount) {
        this.successOrderCount = successOrderCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
