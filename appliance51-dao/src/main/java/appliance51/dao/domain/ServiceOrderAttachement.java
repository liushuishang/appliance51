package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yuananyun on 2016/10/24.
 */
@Entity
@Table(name = "service_order_attachement")
public class ServiceOrderAttachement {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38, nullable = false)
    private String id;

    @Column(name = "order_id", columnDefinition = "varchar(20)", nullable = false, updatable = false)
    private String orderId;

    @Column(name = "resource_url", columnDefinition = "varchar(20)", nullable = false, updatable = false)
    private String resourceUrl;

    @Column(columnDefinition = "timestamp default now()", updatable = false, nullable = false)
    private Date createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
