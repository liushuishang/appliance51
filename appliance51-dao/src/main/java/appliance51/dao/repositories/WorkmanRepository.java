package appliance51.dao.repositories;

import appliance51.dao.domain.User;
import appliance51.dao.domain.Workman;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/9/11.
 */
@Repository
public interface  WorkmanRepository extends PagingAndSortingRepository<Workman, String> {

    Workman findOneByMobile(String mobile);


    User findOneById(String userId);
}
