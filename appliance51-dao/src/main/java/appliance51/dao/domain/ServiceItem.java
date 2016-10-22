package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by  yuananyun on 2016/9/4.
 */
@Entity
@Table(name = "service_item")
public class ServiceItem {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38,nullable = false)
    private String id;

    @Column(columnDefinition = "varchar(38) ",nullable = false)
    private String categoryId;

    @Column(name = "name", columnDefinition = "varchar(50) ",nullable = false)
    private String name;

    @Column(name = "category_name", columnDefinition = "varchar(50) ",nullable = false)
    private String categoryName;

    @Column(name = "icon", columnDefinition = "varchar(250) ")
    private String icon;

    @Column(columnDefinition = "varchar(500)")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
