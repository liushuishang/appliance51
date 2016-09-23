package appliance51.dao.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**业主实体
 * Created by yuana on 2016/9/4.
 */
@Entity
@Table(name="uam_proprietor")
public class Proprietor extends User {
}
