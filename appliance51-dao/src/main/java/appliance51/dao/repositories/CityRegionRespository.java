package appliance51.dao.repositories;

import appliance51.dao.domain.CityRegion;
import appliance51.dao.domain.Proprietor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/9/11.
 */
@Repository
public interface CityRegionRespository extends CrudRepository<CityRegion, String> {
    Page<CityRegion> findAll(Pageable pageable);
}
