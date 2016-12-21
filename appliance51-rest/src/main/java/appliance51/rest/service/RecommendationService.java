package appliance51.rest.service;

import appliance51.dao.domain.ServiceItem;
import appliance51.dao.domain.ServiceOrder;
import appliance51.dao.domain.Workman;
import appliance51.dao.repositories.ServiceOrderRepository;
import appliance51.dao.repositories.WorkmanRepository;
import appliance51.rest.dto.WorkmanRecommend;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 师傅推荐算法
 * Created by  yuananyun on 2016/12/21.
 */
@Service
public class RecommendationService {

    @Autowired
    private ServiceOrderRepository orderRespository;

    @Autowired
    private WorkmanRepository workmanRepository;
    /**
     * 通过订单获取推荐的师傅
     * @param orderId
     * @return
     */
    public List<WorkmanRecommend> recommendByOrderId(String orderId,int take)
    {
        ServiceOrder orderEntity = orderRespository.findOne(orderId);
        if(orderEntity==null) throw new  RuntimeException("非法的订单编号");

        String adCode = orderEntity.getAdCode();
        Set<ServiceItem> serviceItems = orderEntity.getServiceItems();
        String[] serviceIdArray = new String[serviceItems.size()];
        int i=0;
        for (ServiceItem serviceItem : serviceItems) {
            serviceIdArray[i++] = serviceItem.getId();
        }
        List<Workman> workmanList = workmanRepository.findByAdCodeAndServiceItems(adCode, StringUtils.join(serviceIdArray, ","), take);
        List<WorkmanRecommend> workmanRecommendList = new ArrayList<>();
        for (Workman workman : workmanList) {
            WorkmanRecommend recommend=new WorkmanRecommend();
            recommend.setId(workman.getId());
            recommend.setAvatar(workman.getAvatar());
            recommend.setMobile(workman.getMobile());
            recommend.setRealAuth(workman.getRealAuth());
            recommend.setRegtime(workman.getCreatedDate());
            //TODO 获取师傅成功的订单数目
//            recommend.setSuccessOrderCount();
            //TODO 获取师傅的积分排名
//            recommend.setScoreRank();
            recommend.setUserName(workman.getRealName());

            workmanRecommendList.add(recommend);

        }
return workmanRecommendList;

    }
}
