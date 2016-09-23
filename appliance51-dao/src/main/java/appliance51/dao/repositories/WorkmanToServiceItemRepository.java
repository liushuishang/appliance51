package appliance51.dao.repositories;

import appliance51.dao.domain.Workman;
import appliance51.dao.domain.WorkmanToServiceItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/9/22.
 */
@Repository
public interface WorkmanToServiceItemRepository extends CrudRepository<WorkmanToServiceItem, String> {
}
