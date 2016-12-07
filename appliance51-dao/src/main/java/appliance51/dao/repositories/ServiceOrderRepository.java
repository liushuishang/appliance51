package appliance51.dao.repositories;

import appliance51.dao.domain.ServiceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yuananyun on 2016/10/24.
 */
@Repository
public interface ServiceOrderRepository extends PagingAndSortingRepository<ServiceOrder, String> {

    @Query("select o from ServiceOrder as o where submitterId=:userId order by createdDate desc")
    List<ServiceOrder> findAllBySubmitterId(@Param("userId") String userId);

    @Query("select o from ServiceOrder as o where submitterId=:userId and status=:status order by createdDate desc")
    List<ServiceOrder> findAllBySubmitterIdAndStatus(@Param("userId") String userId, @Param("status") int status);

    @Query("select o from ServiceOrder as o where submitterId=:userId and status<:status order by createdDate desc")
    List<ServiceOrder> findAllBySubmitterIdAndStatusLessThan(@Param("userId") String userId, @Param("status") int status);
    
    @Query("select o from ServiceOrder as o where submitterId=:userId and status>:status order by createdDate desc")
    List<ServiceOrder> findAllBySubmitterIdAndStatusGreaterThan(@Param("userId") String userId, @Param("status") int status);


}
