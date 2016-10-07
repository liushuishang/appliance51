package appliance51.dao.repositories;

import appliance51.dao.domain.ServiceCategory;
import appliance51.dao.domain.ServiceItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/9/11.
 */
@Repository
public interface ServiceItemRespository extends CrudRepository<ServiceItem, String> {


}
