package appliance51.rest.service;

import appliance51.dao.domain.User;
import appliance51.dao.domain.Workman;
import appliance51.dao.repositories.ProprietorRespository;
import appliance51.dao.repositories.WorkmanRepository;
import appliance51.rest.model.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户帐号服务
 * Created by yuananyun on 2016/9/11.
 */
@Component
public class UserAccountService {

    @Autowired
    private WorkmanRepository workmanRepository;

    @Autowired
    private ProprietorRespository proprietorRespository;


    /**
     * 师傅注册
     *
     * @param workman
     * @return
     */
    public String workmanRegister(Workman workman) {
        String mobile = workman.getMobile();
        boolean isExists = isAccountExists(workman.getAccountType(), mobile);
        if (isExists)
            return "手机号已经被注册";

        if (workmanRepository.save(workman) == null)
            return "注册信息保存失败！";
        return null;
    }

    /**
     * 根据帐号类型和手机号码判断帐号是否存在
     *
     * @param accountType 帐号的类型
     * @param mobile      手机号
     * @return
     */
    public boolean isAccountExists(String accountType, String mobile) {
        User user = null;
        if (AccountType.Workman.equals(accountType))
            user = findWorkmanByMobile(mobile);
        else if (AccountType.Proprietor.equals(accountType))
            user = findProprietorByMobile(mobile);
        return user == null;
    }

    /**
     * 查找业主
     *
     * @param mobile
     * @return
     */
    public User findProprietorByMobile(String mobile) {
        return proprietorRespository.findOneByMobile(mobile);
    }

    /**
     * 查找工人
     *
     * @param mobile
     * @return
     */
    public User findWorkmanByMobile(String mobile) {
        return workmanRepository.findOneByMobile(mobile);
    }


}
