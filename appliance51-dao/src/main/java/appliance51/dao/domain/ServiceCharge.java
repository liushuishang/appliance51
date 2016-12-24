package appliance51.dao.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**服务的收费
 * Created by  yuananyun on 2016/12/7.
 */
@Entity
@Table(name = "service_charge")
public class ServiceCharge {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38,nullable = false)
    private String id;

//    /**
//     * 服务的ID
//     */
//    @Column(length = 38,nullable = false)
//    private String serviceId;

    /**
     * 规格
     */
    private String  specification;

    /**
     * 价格
     */
    private Double price;

    /**
     * 排序
     */
    @Column(name = "`order`")
    private int order;

    @JSONField(serialize = false)
    @ManyToOne(cascade = CascadeType.MERGE)
    private ServiceItem  serviceItem;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getServiceId() {
//        return serviceId;
//    }
//
//    public void setServiceId(String serviceId) {
//        this.serviceId = serviceId;
//    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ServiceItem getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(ServiceItem serviceItem) {
        this.serviceItem = serviceItem;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
