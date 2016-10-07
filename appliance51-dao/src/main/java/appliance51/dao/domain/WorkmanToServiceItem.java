package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by yuananyun on 2016/9/22.
 */
@Entity
@Table(name = "service_workman_item")
public class WorkmanToServiceItem {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38,nullable = false)
    private String id;
    @Column(columnDefinition = "varchar(38) ",nullable = false)
    private String workmanId;
    @Column(columnDefinition = "varchar(38) ",nullable = false)
    private String serviceItemId;
    @Column(columnDefinition = "timestamp default now()")
    private java.sql.Date createdDate;

    public WorkmanToServiceItem(String workmanId, String serviceItemId) {
        this.workmanId = workmanId;
        this.serviceItemId = serviceItemId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkmanId() {
        return workmanId;
    }

    public void setWorkmanId(String workmanId) {
        this.workmanId = workmanId;
    }

    public String getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(String serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
