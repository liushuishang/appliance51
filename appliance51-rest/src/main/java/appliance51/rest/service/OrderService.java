package appliance51.rest.service;

import appliance51.common.exception.EngineExceptionHelper;
import appliance51.common.exception.ExcepFactor;
import appliance51.common.utils.ExceptionAssert;
import appliance51.dao.domain.ServiceItem;
import appliance51.dao.domain.ServiceOrder;
import appliance51.dao.domain.ServiceOrderAttachement;
import appliance51.dao.domain.User;
import appliance51.dao.model.AccountType;
import appliance51.dao.repositories.ServiceOrderRespository;
import appliance51.rest.dto.ProprietorOrder;
import appliance51.rest.exception.RequestExcepFactor;
import appliance51.rest.exception.UserExcepFactor;
import appliance51.security.context.ThreadLocalContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by yuananyun on 2016/10/12.
 */
@Service
public class OrderService {
    @Autowired
    private UserAccountService accountService;

    @Autowired
    private ServiceOrderRespository orderRespository;

    @Transactional
    public boolean saveOrder(ProprietorOrder orderDto) {
        ExceptionAssert.notBlank(orderDto.getMobile(), ExcepFactor.E_PARAM_ERROR);
        ExceptionAssert.notBlank(orderDto.getAddress(), ExcepFactor.E_PARAM_ERROR);
        ExceptionAssert.notBlank(orderDto.getAdCode(), ExcepFactor.E_PARAM_ERROR);
        ExceptionAssert.notEmpty(orderDto.getServiceIdList(), ExcepFactor.E_PARAM_ERROR);

        String userId = ThreadLocalContext.getRequestContext().getCurrentUid();
        User proprietor = accountService.getUserInfo(AccountType.Proprietor, userId);
        if (proprietor == null) throw EngineExceptionHelper.localException(UserExcepFactor.E_USER_NOTOPEN);

        ServiceOrder order = new ServiceOrder();
        order.setSubmitterId(userId);
        order.setRemark(orderDto.getRemark());
        order.setAdCode(orderDto.getAdCode());
        order.setAddress(orderDto.getAddress());
        order.setOrder_mobile(orderDto.getMobile());
        order.setStatus(ServiceOrder.STATUS_INIT);
        order.setDescription(orderDto.getDescription());
        order.setCreatedDate(new Date());

        Set<ServiceItem> serviceItemSet = new LinkedHashSet<>();
        List<String> serviceIdList = orderDto.getServiceIdList();
        for (String serviceId : serviceIdList) {
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setId(serviceId);
            serviceItemSet.add(serviceItem);
        }
        order.setServiceItems(serviceItemSet);

        List<String> attachmentList = orderDto.getAttachmentList();
        for (String attachment : attachmentList) {
            ServiceOrderAttachement orderAttachement = new ServiceOrderAttachement();
            orderAttachement.setResourceUrl(attachment);
            orderAttachement.setCreatedDate(new Date());

            attachmentList.add(attachment);
        }

        //保存订单信息
        ServiceOrder savedOrder = orderRespository.save(order);
        if (savedOrder == null) EngineExceptionHelper.localException(RequestExcepFactor.REQUEST_ENTITY_SAVED_ERROR);
        return true;
    }
}
