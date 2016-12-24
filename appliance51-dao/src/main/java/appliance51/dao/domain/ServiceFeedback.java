package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by  yuananyun on 2016/12/24.
 */
@Entity
@Table(name = "service_feedback")
public class ServiceFeedback {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38, nullable = false)
    private String id;

    /**
     * 服务的ID
     */
    @Column(length = 38, nullable = false)
    private String serviceId;
    @Column(length = 38, nullable = false)
    private String proprietorId;
    @Column(length = 38, nullable = false)
    private String workmanId;

    /**
     * 业主的评价得分，五星级
     */
    private int proprietorStar;

    /**
     * 业主被评价的时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date proprietorEvaluatedTime;
    /**
     * 师傅的评价得分，五星级
     */
    private int workmanStar;

    /**
     * 师傅被评价的时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date workmanEvaluatedTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getProprietorId() {
        return proprietorId;
    }

    public void setProprietorId(String proprietorId) {
        this.proprietorId = proprietorId;
    }

    public String getWorkmanId() {
        return workmanId;
    }

    public void setWorkmanId(String workmanId) {
        this.workmanId = workmanId;
    }

    public int getProprietorStar() {
        return proprietorStar;
    }

    public void setProprietorStar(int proprietorStar) {
        this.proprietorStar = proprietorStar;
    }

    public Date getProprietorEvaluatedTime() {
        return proprietorEvaluatedTime;
    }

    public void setProprietorEvaluatedTime(Date proprietorEvaluatedTime) {
        this.proprietorEvaluatedTime = proprietorEvaluatedTime;
    }

    public int getWorkmanStar() {
        return workmanStar;
    }

    public void setWorkmanStar(int workmanStar) {
        this.workmanStar = workmanStar;
    }

    public Date getWorkmanEvaluatedTime() {
        return workmanEvaluatedTime;
    }

    public void setWorkmanEvaluatedTime(Date workmanEvaluatedTime) {
        this.workmanEvaluatedTime = workmanEvaluatedTime;
    }
}
