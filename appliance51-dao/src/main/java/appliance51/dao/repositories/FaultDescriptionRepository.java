package appliance51.dao.repositories;

import appliance51.dao.domain.FaultDescription;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by  yuananyun on 2016/12/7.
 */
public interface FaultDescriptionRepository extends PagingAndSortingRepository<FaultDescription, String> {
    List<FaultDescription> findAllByCategoryId(String categoryId);

}
