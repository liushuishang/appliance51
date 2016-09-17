package appliance51.dao.domain;

import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yuana on 2016/9/4.
 */
@Entity
@Table(name = "u_workman")
@DiscriminatorValue("workman")
public class Workman extends User {


    /**
     * 帐号的状态，0：未验证；1：已实名验证；2：已禁用
     */
    @NotNull
    @Column(name = "status", columnDefinition = "int default 0 ")
    public Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
