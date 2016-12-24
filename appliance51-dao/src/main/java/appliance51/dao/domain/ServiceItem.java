package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by  yuananyun on 2016/9/4.
 */
@Entity
@Table(name = "service_item")
public class ServiceItem {

    public ServiceItem() {
    }
    public ServiceItem(String id) {
        this.id=id;
    }
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38,nullable = false)
    private String id;

    @Column(columnDefinition = "varchar(38) ",nullable = false)
    private String categoryId;

    @Column(name = "name", columnDefinition = "varchar(50) ",nullable = false)
    private String name;

    @Column(name = "category_name", columnDefinition = "varchar(50) ",nullable = true)
    private String categoryName;

    @Column(name = "icon", columnDefinition = "varchar(250) ")
    private String icon;

    /**
     * 服务的描述
     */
    @Column(columnDefinition = "varchar(1000)")
    private String description;

    /**
     * 服务的标准
     */
    @Column(columnDefinition = "varchar(1000)")
    private String standard;

    @OneToMany(mappedBy="serviceItem")
    private List<ServiceCharge> serviceChargeList;


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

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public List<ServiceCharge> getServiceChargeList() {
        return serviceChargeList;
    }

    public void setServiceChargeList(List<ServiceCharge> serviceChargeList) {
        this.serviceChargeList = serviceChargeList;
    }
}
