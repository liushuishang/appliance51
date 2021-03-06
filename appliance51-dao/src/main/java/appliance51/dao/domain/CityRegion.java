package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by yuananyun on 2016/9/4.
 */
@Entity
@Table(name = "city_region")
public class CityRegion {

    public CityRegion() {
    }
    public CityRegion(String id) {
        this.id=id;
    }
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 38,nullable = false)
    private String id;

    @Column(name = "name", columnDefinition = "varchar(20) ",nullable = false)
    private String name;

    @Column(name = "code", columnDefinition = "varchar(20) ")
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
