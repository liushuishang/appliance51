package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**实名认证日志表
 * Created by yuananyun on 2016/10/18.
 */
@Entity
@Table(name="log_real_authentication")
public class RealAuthenticatonLog {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38, nullable = false)
    private String id;

    @Column(columnDefinition = "varchar(38)", nullable = false, updatable = false)
    private String userId;

    /**
     * 实名认证提交的参数
     */
    @Column(columnDefinition = "text", nullable = false, updatable = false)
    private String data;

    /**
     * 实名认证的额结果，0：表示未验证；1：验证通过；2：验证失败
     */
    @Column(columnDefinition = "tinyint default 0", updatable = true)
    public int result;

    /**
     * 创建时间
     */
    @Column(columnDefinition = "timestamp default now()", nullable = false, updatable = false)
    private Date createdDate;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


}
