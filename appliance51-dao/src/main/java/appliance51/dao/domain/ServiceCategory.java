package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yuananyun on 2016/9/4.
 */
@Entity
@Table(name="service_category")
public class ServiceCategory {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38,nullable = false)
    private String id;

    @Column(columnDefinition = "varchar(38)",nullable = true)
    private String parentId;

    @Column(name = "parent_name", columnDefinition = "varchar(50)",nullable = true)
    private String parentName;

    @Column(name = "name", columnDefinition = "varchar(50)",nullable = false,updatable = true)
    private String name;

    @Column(name = "`order`",columnDefinition = "int default 0")
    private  int order;

    @Column(columnDefinition = "varchar(100)")
    private String description;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="categoryId")
    private Set<ServiceItem> items;

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

    public Set<ServiceItem> getItems() {
        return items;
    }

    public void setItems(Set<ServiceItem> items) {
        this.items = items;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
