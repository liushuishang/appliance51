//package appliance51.rest.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.jme.security.IdentificationManager;
//import com.jme.security.MapCipher;
//import com.jme.system.user.manager.UserManager;
//import com.jme.system.user.pojo.User;
//import com.jme.web.util.ExtRtvModel;
//
///**
// * 用户登录
// * @author
// * @see
// * @since 1.0
// */
//
//@Controller("restIdentificationController")
//@RequestMapping(value="/identification")
//public class IdentificationController {
//    /**
//     * 长超时
//     */
//    @Value("#{configProperties['Identification.expires_long']}")
//    int expires_long = 1000 * 60 * 60 * 2;
//    /**
//     * 短超时
//     */
//    @Value("#{configProperties['Identification.expires_short']}")
//    int expires_short = 1000 * 60 * 60 * 1;
//
//    /**
//     * 密码管理者
//     */
//    @Autowired
//    MapCipher mapCipher = null;
//
//    @Autowired
//    IdentificationManager identificationManager;
//    /**
//     * 用户逻辑处理类
//     */
//    @Autowired
//    private UserManager userManager = null;
//
//    /**
//     * 登录请求
//     * http://localhost:8081/rms/rest/identification?userName=admin&password=e10adc3949ba59abbe56e057f20f883e
//     * http://localhost:8081/rms/rest/identification?userName=admin&password=K1OeBisZK9AZdXlThy_PzJECHsKIU-o_Qz9dZCjzNaUYmf8R-B6zsHYAc3LovwfF79g-SpgRRStCeWQA7f79pW9EYG9K4TyZ2jvxNNkwkXhprckmdt6XqouOTxCbggIP3zYc0VMHHFxTjRHbu2MZrnrNJzsEs97xzLvP19c33h8=
//     * 返回 {"success":true,"message":"OK","errCode":"","data":{"ticket":"08c6abfa3f6ae6b2661abe9352ade7ea","expires":7113841}}
//     * @param loginInfo
//     * @return
//     */
//    @RequestMapping(method=RequestMethod.GET)
//    @ResponseBody
//    public ExtRtvModel get(String userName,String password){
//        return doGet(userName,password);
//    }
//    protected ExtRtvModel doGet(String userName,String password){
//        boolean success = true;
//        String message = "OK";
//        Identification identification = null;
//        try {
//            User condition = new User();
//            condition.setLoginname(userName);
//
//            User user = userManager.find(condition);
//            identification = doIdentification(userName, password, user, identificationManager, mapCipher, expires_long, expires_short);
//        } catch (Exception e) {
//            success = false;
//            message = e.getLocalizedMessage();
//            //message = String.format("%s : %s, %s  失败! \n\r %s", getModeleName(),id,"新建",message);
//        }
//
//        return new ExtRtvModel(success,identification,message);
//    }
//
//    static Identification doIdentification(String userName,String password,User user,IdentificationManager identificationManager,MapCipher mapCipher,int expires_long,int expires_short) {
//        Identification identification = null;
//        try {
//
//            if (user != null) {
//                //这是最简单的MD5加密后的测试
//                boolean test = password.equals(user.getPassword());
//                //这种是以 经过 mapCiphere.cipherKey(userName + MD5(password))后的密码
//                if (test == false) {
//                    //long day = System.currentTimeMillis() / (1000 * 60 * 60 * 24);
//                    //user.getPassword(); 是password 的 MD5
//                    String namePassword = userName + user.getPassword();
//                    //boolean test = mapCipher.testKey(namePassword + day, null, password);
//                    test = mapCipher.testKey(namePassword, null, password);
//
//                    /*
//                    //前后两天时间可以处理
//                    for(int i = -2; test == false && i < 0; i++) {
//                        test = mapCipher.testKey(namePassword + (day + i), null, password);
//                    }
//                    for(int i = 1; test == false && i < 3; i++) {
//                        test = mapCipher.testKey(namePassword + (day + i), null, password);
//                    }
//                    */
//                }
//                if (test == false) {
//                    throw new IllegalArgumentException("用户名或密码错误");
//                } else {
//                    String ticket = identificationManager.getTicket(userName);
//                    int expires = expires_long;
//                    long time = 0L;
//                    if (ticket != null) {
//                        time = identificationManager.getTicketTime(ticket);
//                        //超过了短超时 可以生成一个新的ticket
//                        if (time < System.currentTimeMillis() - expires_short) {
//                            ticket = null;
//                        } else {
//                            expires = (int)(time - System.currentTimeMillis() + expires_long);
//                        }
//                    }
//                    if (ticket == null) {
//                        time = System.currentTimeMillis();
//                        ticket = mapCipher.md5Sign(userName + time,password);
//                        expires = expires_long;
//
//                        identificationManager.putIdentification(userName, ticket, time, expires);
//                    }
//
//                    identification = new Identification();
//                    identification.setTicket(ticket);
//                    identification.setExpires(expires);
//
//                }
//            } else {
//                throw new IllegalArgumentException("用户名或密码错误");
//            }
//        } catch (Exception e) {
//            throw new IllegalArgumentException(e.getLocalizedMessage());
//        }
//
//        return identification;
//    }
//
//}
