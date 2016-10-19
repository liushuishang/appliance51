package appliance51.dao.repositories;

import appliance51.dao.domain.RealAuthenticatonLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/10/18.
 */
@Repository
public interface RealAuthLogRespository extends CrudRepository<RealAuthenticatonLog, String> {


}
