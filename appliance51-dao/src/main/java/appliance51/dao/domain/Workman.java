package appliance51.dao.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yuana on 2016/9/4.
 */
@Entity
@Table(name = "uam_workman")
public class Workman extends User {


    /**
     * 帐号的状态，0：未验证；1：已实名验证；2：已禁用
     */
    @Column(name = "status", columnDefinition = "int default 0 ",nullable = false)
    private Integer status;

    /**
     * 身份证号码
     */
    @Column(name = "certificateNo", length = 20, nullable = false)
    private String CertificateNo;

//    /**
//     * 师傅可以服务的区县
//     */
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "service_workman_region",//中间表名
//            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id", unique = false)},
//            inverseJoinColumns = {@JoinColumn(name = "regionId", referencedColumnName = "id", unique = false)})
//    private Set<CityRegion> serviceRegionSet;
//
//    /**
//     * 师傅可以提供的服务项目
//     */
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "service_workman_item",//中间表名
//            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id", unique = false)},
//            inverseJoinColumns = {@JoinColumn(name = "itemId", referencedColumnName = "id", unique = false)})
//    private Set<ServiceItem> serviceItemSets;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCertificateNo() {
        return CertificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        CertificateNo = certificateNo;
    }

}
