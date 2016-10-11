package appliance51.security.constants;

/**请求头的常量字符串
 * Created by yuananyun on 2016/10/10.
 */
public class RequestHeaderConstant {

    public static final String MULTIPART = "multipart/";
    //    public static final String FROM_HEADER = "X-Engine-From"; //用于判断内网外网（Nginx配置添加Header）
//    public static final String SSL_HEADER = "X-Engine-SSL";
    //请求的Token
    public static final String CLIENT_TOKEN = "X-Client-Token";
    //终端的类别
    public static final String CLIENT_TYPE = "X-Client-Type";
    //终端的操作系统
    public static final String CLIENT_PLATFORM = "X-Client-Paltform";
    //终端安装的应用版本
    public static final String CLIENT_VERSION = "X-Client-Version";
    //设备ID，IMEI
    public static final String CLIENT_DEVICE = "X-Device";
}
