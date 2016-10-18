package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @Column(columnDefinition = "varchar(50) ", nullable = false)
    private String seviceAdCode;
    @Column(columnDefinition = "timestamp default now()")
    private java.sql.Date createdDate;

    public WorkmanToServiceRegion(String workmanId, String serviceAdCode) {
        this.workmanId = workmanId;
        this.seviceAdCode = seviceAdCode;
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

    public String getSeviceAdCode() {
        return seviceAdCode;
    }

    public void setSeviceAdCode(String seviceAdCode) {
        this.seviceAdCode = seviceAdCode;
    }

    public void setCreatedDate(java.sql.Date createdDate) {
        this.createdDate = createdDate;
    }


    public java.sql.Date getCreatedDate() {
        return createdDate;
    }
}
