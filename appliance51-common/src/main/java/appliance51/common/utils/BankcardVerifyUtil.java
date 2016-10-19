package appliance51.common.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 银行卡验证工具
 * Created by yuananyun on 2016/10/19.
 */
public class BankcardVerifyUtil {

    private static Logger logger = LoggerFactory.getLogger(BankcardVerifyUtil.class);
    /**
     * 聚合数据银行卡四元素校验地址
     */
    private static String API_URL = "http://v.juhe.cn/verifybankcard4/query";


    /**
     * 个人姓名、手机、银行卡和身份证四元素验证
     *
     * @param key      聚合数据的key
     * @param realName 个人真实姓名
     * @param idCard   个人身份证号码
     * @param bankcard 银行卡号码
     * @param mobile   手机号码
     * @return
     */
    public static boolean verifybankcard4(String key, String realName, String idCard, String bankcard, String mobile) {
        HttpUtil client = HttpUtil.getInstance();
        Map<String, String> paramMap = new HashMap();
        try {
            logger.info("【verifybankcard4】开始发送验证请求，参数为：" + JSON.toJSONString(paramMap));
            Map<String, Object> response = client.doGetForMap(API_URL, paramMap, null);
            logger.info("【verifybankcard4】收到请求相应，结果为：" + JSON.toJSONString(response));
            Map<String, String> result = (Map<String, String>) response.get("result");
            if(result==null) return false;
            String jobid = result.get("jobid");
            String res = result.get("res");
            if (!"1".equals(res)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return false;
    }
}
