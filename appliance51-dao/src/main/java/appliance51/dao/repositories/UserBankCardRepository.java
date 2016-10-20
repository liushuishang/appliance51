package appliance51.dao.repositories;

import appliance51.dao.domain.UserBankCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/10/18.
 */
@Repository
public interface UserBankCardRepository extends CrudRepository<UserBankCard, String> {
    UserBankCard findByBankcardNo(String bankNo);
}
