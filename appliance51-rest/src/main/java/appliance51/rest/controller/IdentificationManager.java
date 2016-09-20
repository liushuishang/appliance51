//package appliance51.rest.controller;
//
///**
// * 身份认证管理者
// * @author wangyong 2016年6月2日
// * @see
// * @since 1.0
// */
//public interface IdentificationManager
//{
//    /**
//     * 设置一个 一个key的认证票
//     * @param key 键 可以是用户名等等
//     * @param ticket 认证票
//     * @param time 生成 ticket 的时间 毫秒值
//     * @param expires 超时时间 毫秒
//     */
//    public void putIdentification(String key, String ticket, long time, int expires);
//
//    /**
//     * 取一个key 的话ticket
//     * @param key  认证票
//     * @return ticket
//     */
//    public String getTicket(String key);
//
//    /**
//     * 取一个的ticket ticket
//     * @param ticket  认证票
//     * @return key
//     */
//    public String getKey(String ticket);
//
//    /**
//     * 取一个ticket的生成时间
//     * @param ticket  认证票
//     * @return ticket的生成时间
//     */
//    public long getTicketTime(String ticket);
//}
