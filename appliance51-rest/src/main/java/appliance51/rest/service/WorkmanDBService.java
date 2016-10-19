package appliance51.rest.service;

import appliance51.common.utils.PasswordUtl;
import appliance51.dao.domain.Workman;
import appliance51.dao.domain.WorkmanToServiceItem;
import appliance51.dao.domain.WorkmanToServiceRegion;
import appliance51.dao.repositories.WorkmanRepository;
import appliance51.dao.repositories.WorkmanToServiceItemRepository;
import appliance51.dao.repositories.WorkmanToServiceRegionRepository;
import appliance51.rest.dto.WorkmanRegistration;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yuananyun on 2016/9/22.
 */
@Service
public class WorkmanDBService {
    @Autowired
    private WorkmanRepository workmanRepository;

    @Autowired
    private WorkmanToServiceItemRepository serviceItemRepository;
    @Autowired
    private WorkmanToServiceRegionRepository serviceRegionRepository;

    @Transactional
    public Workman save(WorkmanRegistration workmanRegistration) {
        Workman workman = new Workman();
        BeanUtils.copyProperties(workmanRegistration, workman);
        workman.setStatus(0);

        Workman savedEntity = workmanRepository.save(workman);
        String manId = savedEntity.getId();
        List<String> serviceItemIdList = workmanRegistration.getServiceItemIdList();
        List<String> serviceAdCodeList = workmanRegistration.getServiceAdCodeList();
        for (String serviceItemId : serviceItemIdList) {
            serviceItemRepository.save(new WorkmanToServiceItem(manId, serviceItemId));
        }
        for (String serviceAdCode : serviceAdCodeList) {
            serviceRegionRepository.save(new WorkmanToServiceRegion(manId, serviceAdCode));
        }
        return savedEntity;
    }

    public boolean save(Workman workman)
    {
        return workmanRepository.save(workman)!=null;
    }


    public Workman findOneByMobile(String mobile) {
        return workmanRepository.findOneByMobile(mobile);
    }

    public Workman findOne(String id) {
        return workmanRepository.findOne(id);
    }
}
