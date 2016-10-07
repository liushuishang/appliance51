//package appliance51.dao.domain;
//
//
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//
///**
// * Created by yuananyun on 2016/9/17.
// */
//@Entity
//@Table(name="admin_user")
//public class AdminUser {
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid")
//    @Column(length = 38,nullable = false)
//    private String id;
//
//    private String firstname;
//    private String lastname;
//
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//}
