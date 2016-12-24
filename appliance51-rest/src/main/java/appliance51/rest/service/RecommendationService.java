package appliance51.rest.service;

import appliance51.dao.repositories.ServiceOrderRepository;
import appliance51.dao.repositories.WorkmanRepository;
import appliance51.rest.dto.WorkmanRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
     *
     * @param orderId
     * @return
     */
    public List<WorkmanRecommend> recommendByOrderId(String orderId, int take) {
        List<WorkmanRecommend> workmanRecommendList = new ArrayList<>();
        for(int i=0;i<3;i++){
            WorkmanRecommend recommend = new WorkmanRecommend();
            recommend.setId(UUID.randomUUID().toString());
            recommend.setAvatar("默认头像");
            recommend.setMobile("15988888888");
            recommend.setAddress("长沙市岳麓区东方红中路123号长天大厦");
            recommend.setRealAuth(true);
            recommend.setRegtime(new Date());
            recommend.setSuccessOrderCount(10);
            recommend.setPraiseCount(5);
            recommend.setOrdinaryCount(3);
            recommend.setBadCount(2);
            recommend.setUserName("师傅"+1);

            workmanRecommendList.add(recommend);
        }
        return workmanRecommendList;


//        ServiceOrder orderEntity = orderRespository.findOne(orderId);
//        if (orderEntity == null) throw new RuntimeException("非法的订单编号");
//
//        String adCode = orderEntity.getAdCode();
//        Set<ServiceItem> serviceItems = orderEntity.getServiceItems();
//        String[] serviceIdArray = new String[serviceItems.size()];
//        int i = 0;
//        for (ServiceItem serviceItem : serviceItems) {
//            serviceIdArray[i++] = serviceItem.getId();
//        }
//        List<Workman> workmanList = null;//workmanRepository.findByAdCodeAndServiceItems(adCode, StringUtils.join(serviceIdArray, ","), take);
//
//        for (Workman workman : workmanList) {
//            WorkmanRecommend recommend = new WorkmanRecommend();
//            recommend.setId(workman.getId());
//            recommend.setAvatar(workman.getAvatar());
//            recommend.setMobile(workman.getMobile());
//            recommend.setRealAuth(workman.getRealAuth());
//            recommend.setRegtime(workman.getCreatedDate());
//            //TODO 获取师傅成功的订单数目
////            recommend.setSuccessOrderCount();
//            //TODO 获取师傅的积分排名
////            recommend.setScoreRank();
//            recommend.setUserName(workman.getRealName());
//
//            workmanRecommendList.add(recommend);
//
//        }
//        return workmanRecommendList;

    }
}
