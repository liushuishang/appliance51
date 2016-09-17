package appliance51.dao.domain;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by yuananyun on 2016/9/4.
 */
@Entity
@Table(name="service_category")
public class ServiceCategory {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Column(columnDefinition = "varchar(38)")
    private String parentId;

    @NotNull
    @Column(name = "name", columnDefinition = "varchar(50)")
    private String name;

    @Column(columnDefinition = "varchar(100)")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
