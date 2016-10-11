package appliance51.dao.repositories;

import appliance51.dao.domain.ClientLoginStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/10/10.
 */
@Repository
public interface ClientLoginLogRespository extends CrudRepository<ClientLoginStatus, String> {

    @Query(value = "select device_Identification from status_client_login  where mobile=:mobile and account_type=:accountType", nativeQuery = true)
    String findDeviceIdentificationByMobileAndAccountType(@Param("mobile")String mobile,@Param("accountType") String accountType);

    ClientLoginStatus findByUserId(String id);
}
