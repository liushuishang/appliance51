package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yuananyun on 2016/9/22.
 */
@Entity
@Table(name = "service_workman_region")
public class WorkmanToServiceRegion {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38, nullable = false)
    private String id;
    @Column(columnDefinition = "varchar(38) ", nullable = false)
    private String workmanId;
    @Column(columnDefinition = "varchar(38) ", nullable = false)
    private String serviceRegionId;
    @Column(columnDefinition = "timestamp default now()")
    private java.sql.Date createdDate;

    public WorkmanToServiceRegion(String workmanId, String serviceRegionId) {
        this.workmanId = workmanId;
        this.serviceRegionId = serviceRegionId;
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

    public String getServiceRegionId() {
        return serviceRegionId;
    }

    public void setServiceRegionId(String serviceRegionId) {
        this.serviceRegionId = serviceRegionId;
    }

    public void setCreatedDate(java.sql.Date createdDate) {
        this.createdDate = createdDate;
    }


    public java.sql.Date getCreatedDate() {
        return createdDate;
    }
}
