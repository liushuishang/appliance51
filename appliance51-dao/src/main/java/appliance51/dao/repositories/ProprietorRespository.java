package appliance51.dao.repositories;

import appliance51.dao.domain.Proprietor;
import appliance51.dao.domain.Workman;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/9/11.
 */
@Repository
public interface ProprietorRespository extends PagingAndSortingRepository<Proprietor, String> {

    Proprietor findOneByMobile(String mobile);
}
