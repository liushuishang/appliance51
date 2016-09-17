package appliance51.rest.service;

import org.springframework.stereotype.Component;

/**手机短信服务
 * Created by yuananyun on 2016/9/11.
 */
@Component
public class MobileMsgService {

    /**
     * 发送手机验证码
     * @param mobile
     * @return
     */
    public String getSMSCode(String mobile)
    {

        return "abcde";
    }
}
