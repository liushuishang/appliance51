package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**用户的积分信息
 * Created by yuana on 2016/9/4.
 */
@Entity
@Table(name = "uam_score")
public class UserScore {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38,nullable = false)
    private String id;


    @Column(columnDefinition = "varchar(38) ",nullable = false)
    private String userId;

    @Column(columnDefinition = "float default 0",nullable = false)
    private Long amount;

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
