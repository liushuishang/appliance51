package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by yuananyun on 2016/9/4.
 */
@Entity
@Table(name = "service_order")
public class ServiceOrder {
    /**
     * 初始状态
     */
    public static int STATUS_INIT = 0;

    /**
     * 已经缴纳上门服务费
     */
    public static int STATUS_SERVICE_FEE_PAYED = 1;

    /**
     * 已经匹配师傅并确认
     */
    public static int STATUS_COMFIRMED = 2;

    /**
     * 已经完成
     */
    public static int STATUS_COMPLETED = 3;

    /**
     * 已经评价
     */
    public static int STATUS_EVALUATED = 4;


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38, nullable = false)
    private String id;

    @Column(columnDefinition = "varchar(38)", nullable = false)
    private String submitterId;

    @Column(columnDefinition = "varchar(38)", nullable = false)
    private String performerId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_order_item", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "service_item_id"))
    private Set<ServiceItem> serviceItems;

    @Column(columnDefinition = "float default 0", nullable = false)
    private Float amount;


    @Column(name = "adCode", columnDefinition = "varchar(20)")
    private String adCode;
    @Column(columnDefinition = "varchar(200)")
    private String address;
    @Column(columnDefinition = "varchar(20)")
    private String order_mobile;
    @Column(columnDefinition = "varchar(200)")
    private String remark;
    @Column(columnDefinition = "varchar(500)")
    private String description;

    /**
     * 订单的附件
     */
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id")
    private Set<ServiceOrderAttachement> attachmentSet;


    @Column(columnDefinition = "timestamp default now()", updatable = false, nullable = false)
    private Date createdDate;
    @Column(columnDefinition = "timestamp")
    private Date completedDate;
    @Column(columnDefinition = "timestamp")
    private Date evaluatedDate;

    /**
     * 订单的状态
     */
    @Column(columnDefinition = "tinyint default 0", nullable = false)
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getPerformerId() {
        return performerId;
    }

    public void setPerformerId(String performerId) {
        this.performerId = performerId;
    }

    public Set<ServiceItem> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(Set<ServiceItem> serviceItems) {
        this.serviceItems = serviceItems;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<ServiceOrderAttachement> getAttachmentSet() {
        return attachmentSet;
    }

    public void setAttachmentSet(Set<ServiceOrderAttachement> attachmentSet) {
        this.attachmentSet = attachmentSet;
    }

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

    public String getOrder_mobile() {
        return order_mobile;
    }

    public void setOrder_mobile(String order_mobile) {
        this.order_mobile = order_mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getEvaluatedDate() {
        return evaluatedDate;
    }

    public void setEvaluatedDate(Date evaluatedDate) {
        this.evaluatedDate = evaluatedDate;
    }
}
