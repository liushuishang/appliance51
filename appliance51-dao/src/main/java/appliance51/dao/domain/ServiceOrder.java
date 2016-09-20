package appliance51.dao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by yuananyun on 2016/9/4.
 */
@Entity
@Table(name = "service_order")
public class ServiceOrder {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(columnDefinition = "varchar(38)",nullable = false)
    private String submitterId;

    @Column(columnDefinition = "varchar(38)",nullable = false)
    private String performerId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pageId", insertable = false, updatable = false)
    private Set<ServiceItem> ServiceItems;

    @Column(columnDefinition = "float default 0",nullable = false)
    private Float amount;

    @Column(columnDefinition = "varchar(200)")
    private String description;

    @Column(columnDefinition = "timestamp default now()",updatable = false,nullable = false)
    private Date createdDate;

    @Column(columnDefinition = "timestamp")
    private Date completedDate;

}
