package appliance51.payment.service;

import appliance51.common.utils.BeanToMapUtil;
import appliance51.payment.config.PingppConfig;
import appliance51.payment.model.OrderCharge;
import appliance51.payment.model.PingppChannel;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.ChargeCollection;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuananyun on 2016/10/30.
 */
@Service
public class ChargeService {
    private static Logger logger = LoggerFactory.getLogger(ChargeService.class);
    @Autowired
    private PingppConfig pingppConfig;

    /**
     * 创建Ping++的Charge对象
     *
     * @param orderCharge
     * @return
     */
    public Charge creageCharge(OrderCharge orderCharge) {
        try {
            Map<String, Object> chargeMap = BeanToMapUtil.toMap(orderCharge);
            chargeMap.remove("orderId");
            chargeMap.remove("id");
            chargeMap.put("order_no", createOrderNo(orderCharge.getOrderId()));
            chargeMap.put("currency", "cny");
            Map<String, String> app = new HashMap<String, String>();
            app.put("id", pingppConfig.getApiId());
            chargeMap.put("app", app);
            Map<String, Object> extra = new HashMap<String, Object>();
//        extra.put("open_id", "USER_OPENID");
            chargeMap.put("extra", extra);
            Charge charge = Charge.create(chargeMap);
            return charge;

        } catch (Exception ex) {
            logger.error("【creageCharge】" + ex.getMessage());
            return null;
        }
    }

    private String createOrderNo(String orderId) {
        return DigestUtils.md2Hex(orderId + System.currentTimeMillis());
    }

    /**
     * 查询 Charge
     * <p>
     * 该接口根据 charge Id 查询对应的 charge 。
     * 参考文档：https://pingxx.com/document/api#api-c-inquiry
     * <p>
     * 该接口可以传递一个 expand ， 返回的 charge 中的 app 会变成 app 对象。
     * 参考文档： https://pingxx.com/document/api#api-expanding
     *
     * @param id
     */
    public Charge retrieve(String id) {
        Charge charge = null;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
//            List<String> expand = new ArrayList<String>();
//            expand.add("app");
//            params.put("expand", expand);
            charge = Charge.retrieve(id, params);
            System.out.println(charge);
        } catch (PingppException e) {
            e.printStackTrace();
        }

        return charge;
    }

    /**
     * 分页查询 Charge
     * <p>
     * 该接口为批量查询接口，默认一次查询10条。
     * 用户可以通过添加 limit 参数自行设置查询数目，最多一次不能超过 100 条。
     * <p>
     * 该接口同样可以使用 expand 参数。
     *
     * @return chargeCollection
     */
    public ChargeCollection queryAllCharges() {
        ChargeCollection chargeCollection = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("limit", 3);
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", pingppConfig.getApiId());
        params.put("app", app);

        try {
            chargeCollection = Charge.all(params);
            System.out.println(chargeCollection);
        } catch (Exception ex) {
            logger.error("【queryAllCharges】" + ex.getMessage());
        }
        return chargeCollection;
    }

    public static void main(String[] args) {
        Pingpp.apiKey = "sk_test_800OG8vHiXz584Ki1Ci940u9";

        OrderCharge orderCharge = new OrderCharge();
        orderCharge.setOrderId("88888888888888888888888");
        orderCharge.setAmount(10000);
        orderCharge.setBody("这是一个测试订单");
        orderCharge.setChannel(PingppChannel.alipay);
        orderCharge.setClient_ip("127.0.0.1");
        orderCharge.setSubject("测试订单");
        orderCharge.setDescription("fwefwefwefwefwe");

        ChargeService service=new ChargeService();
        PingppConfig config=new PingppConfig();
        config.setApiId("app_4iHOuDrfvbT8nPO0");
        service.pingppConfig=config;
        String result = service.creageCharge(orderCharge).toString();


    }

}
