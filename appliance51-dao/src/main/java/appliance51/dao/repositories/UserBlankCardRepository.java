package appliance51.dao.repositories;

import appliance51.dao.domain.RealAuthenticatonLog;
import appliance51.dao.domain.UserBlankCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/10/18.
 */
@Repository
public interface UserBlankCardRepository extends CrudRepository<UserBlankCard, String> {
}
