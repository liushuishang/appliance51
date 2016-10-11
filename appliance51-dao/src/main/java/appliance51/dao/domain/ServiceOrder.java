package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by yuananyun on 2016/9/4.
 */
@Entity
@Table(name = "service_order")
public class ServiceOrder {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38,nullable = false)
    private String id;

    @Column(columnDefinition = "varchar(38)",nullable = false)
    private String submitterId;

    @Column(columnDefinition = "varchar(38)",nullable = false)
    private String performerId;

    @OneToMany(fetch =FetchType.EAGER )
    @JoinTable(name = "service_order_item", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "service_item_id"))
    private Set<ServiceItem> serviceItems;

    @Column(columnDefinition = "float default 0",nullable = false)
    private Float amount;

    @Column(columnDefinition = "varchar(200)")
    private String description;

    @Column(columnDefinition = "timestamp default now()",updatable = false,nullable = false)
    private Date createdDate;

    @Column(columnDefinition = "timestamp")
    private Date completedDate;


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
}
