package appliance51.dao.repositories;

import appliance51.dao.domain.ServiceItem;
import appliance51.dao.domain.ServiceOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/10/24.
 */
@Repository
public interface ServiceOrderRespository extends CrudRepository<ServiceOrder, String> {
}
