package appliance51.rest.service;

import appliance51.dao.domain.Proprietor;
import appliance51.dao.domain.User;
import appliance51.dao.repositories.ProprietorRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuananyun on 2016/9/22.
 */
@Service
public class ProprietorDBService {
    @Autowired
    private ProprietorRespository proprietorRespository;

    public Proprietor findOneByMobile(String mobile) {
        return proprietorRespository.findOneByMobile(mobile);
    }
}
