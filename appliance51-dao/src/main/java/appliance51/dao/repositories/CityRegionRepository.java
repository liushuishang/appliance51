package appliance51.dao.repositories;

import appliance51.dao.domain.CityRegion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/9/11.
 */
@Repository
public interface CityRegionRepository extends PagingAndSortingRepository<CityRegion, String> {

}
