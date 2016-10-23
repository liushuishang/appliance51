package appliance51.dao.repositories;

import appliance51.dao.domain.ServiceCategory;
import appliance51.dao.model.NamingObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yuananyun on 2016/9/11.
 */
@Repository
public interface ServiceCategoryRespository extends CrudRepository<ServiceCategory, String> {


    Page<ServiceCategory> findAll(Pageable pageable);

    @Query(value ="select id,`name` from service_category" ,nativeQuery = true,countName = "id,name")
    List<Object[]> findAllWithIdAndName();

}
