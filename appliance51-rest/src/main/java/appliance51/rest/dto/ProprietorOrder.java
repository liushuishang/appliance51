package appliance51.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yuananyun on 2016/10/12.
 */
@ApiModel(value = "业主提交的订单信息")
public class ProprietorOrder {
    @ApiModelProperty(value = "订单所在区划代码", required = true)
    private String adCode;
    @ApiModelProperty(value = "订单的上门地址", required = true)
    private String address;
    @ApiModelProperty(value = "订单的联系电话", required = true)
    private String mobile;
    @ApiModelProperty(value = "订单的备注", required = true)
    private String remark;
    @ApiModelProperty(value = "订单的描述", required = true)
    private String description;
    @ApiModelProperty(value = "订单包含的服务id列表", required = true)
    private List<String> serviceIdList;
    @ApiModelProperty(value = "订单包含的附件，比如拍照图片", required = true)
    private List<String> attachmentList;


    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getServiceIdList() {
        return serviceIdList;
    }

    public void setServiceIdList(List<String> serviceIdList) {
        this.serviceIdList = serviceIdList;
    }

    public List<String> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<String> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
