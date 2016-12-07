package appliance51.payment.model;

import java.util.Map;

/**
 * 用于创建ping++中charge的辅助对象
 * Created by yuananyun on 2016/10/30.
 */
public class OrderCharge {

    /**
     * 系统中的订单号
     */
    private String orderId;
    /**
     * 提交到ping++的订单号
     * 推荐使用 8-20 位，要求数字或字母，不允许其他字符
     */
//    private String order_no;
    /**
     * 支付使用的第三方支付渠道
     */
    private String channel;

    /**
     * 订单总金额, 单位为对应币种的最小货币单位，例如：人民币为分（如订单总金额为 1 元，此处请填 100）
     */
    private double amount;

    /**
     * 发起支付请求客户端的 IP 地址，格式为 IPV4，如: 127.0.0.1。
     */
    private String client_ip;

    /**
     * 商品的标题，该参数最长为 32 个 Unicode 字符
     */
    private String subject;

    /**
     * 商品的描述信息，该参数最长为 128 个 Unicode 字符，yeepay_wap 对于该参数长度限制为 100 个 Unicode 字符
     */
    private String body;

    /**
     * 可选
     * 订单附加说明，最多 255 个 Unicode 字符。
     */
    private String description;

    /**
     * 可选
     * 特定渠道发起交易时需要的额外参数以及部分渠道支付成功返回的额外参数。
     */
    private Object extra;

    /**
     * 可选
     * 订单失效时间，用 Unix 时间戳表示。时间范围在订单创建后的 1 分钟到 15 天，默认为 1 天，创建时间以 Ping++ 服务器时间为准。 微信对该参数的有效值限制为 2 小时内；银联对该参数的有效值限制为 1 小时内
     */
    private Long time_expire;

    /**
     * 可选
     * 每一个对象的 metadata 最多可以拥有 10 个键值对，数据总长度在 1000 个 Unicode 字符以内。
     */
    private Map<String,String> metadata;



    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public Long getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(Long time_expire) {
        this.time_expire = time_expire;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
