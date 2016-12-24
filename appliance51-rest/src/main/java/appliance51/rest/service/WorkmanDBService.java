package appliance51.rest.service;

import appliance51.dao.domain.CityRegion;
import appliance51.dao.domain.ServiceItem;
import appliance51.dao.domain.User;
import appliance51.dao.domain.Workman;
import appliance51.dao.repositories.WorkmanRepository;
import appliance51.rest.dto.WorkmanRegistration;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yuananyun on 2016/9/22.
 */
@Service
public class WorkmanDBService {
    @Autowired
    private WorkmanRepository workmanRepository;


    @Transactional
    public Workman save(WorkmanRegistration workmanRegistration) {
        Workman workman = new Workman();
        BeanUtils.copyProperties(workmanRegistration, workman);
        workman.setStatus(Workman.STATUS_NOT_VERIFIED);//未验证状态
        workman.setUserName(workmanRegistration.getMobile());

        List<String> serviceItemIdList = workmanRegistration.getServiceItemIdList();
        List<String> serviceAdCodeList = workmanRegistration.getServiceAdCodeList();
        Set<ServiceItem> serviceItemSet = new HashSet<>();
        Set<CityRegion> cityRegionSet = new HashSet<>();
        //TODO 这里暂时没有验证这两个参数的必须
        serviceItemSet.addAll(serviceItemIdList.stream().map(ServiceItem::new).collect(Collectors.toList()));
        cityRegionSet.addAll(serviceAdCodeList.stream().map(CityRegion::new).collect(Collectors.toList()));
        workman.setServiceItemSet(serviceItemSet);
        workman.setServiceRegionSet(cityRegionSet);
        Workman savedEntity = workmanRepository.save(workman);
        return savedEntity;
    }

    public boolean save(Workman workman) {
        return workmanRepository.save(workman) != null;
    }


    public Workman findOneByMobile(String mobile) {
        return workmanRepository.findOneByMobile(mobile);
    }

    public Workman findOne(String id) {
        return workmanRepository.findOne(id);
    }

    public User findOneById(String userId) {
        return workmanRepository.findOneById(userId);

    }
}
