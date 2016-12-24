package appliance51.dao.domain;

import appliance51.dao.model.AccountType;

import javax.persistence.Entity;
import javax.persistence.Table;

/**业主实体
 * Created by yuana on 2016/9/4.
 */
@Entity
@Table(name="uam_proprietor")
public class Proprietor extends User {

    @Override
    public String getAccountType() {
        return AccountType.Proprietor.getValue();
    }


}
