//package appliance51.rest.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.jme.security.IdentificationManager;
//import com.jme.security.MapCipher;
//import com.jme.system.company.info.manager.CompanyManager;
//import com.jme.system.company.info.pojo.Company;
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
//@Controller("restLoginController")
//@RequestMapping(value="/login")
//public class LoginController {
//    /**
//     * 用户逻辑处理类
//     */
//    @Autowired
//    private UserManager userManager = null;
//
//    /**
//     * 用户逻辑处理类
//     */
//    @Autowired
//    private CompanyManager companyManager = null;
//
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
//
//    /**
//     * 登录请求
//     * http://localhost:8081/rms/rest/login?userName=admin&password=e10adc3949ba59abbe56e057f20f883e
//     * 返回 {"success":true,"message":"OK","errCode":"","data":{"identification":{"ticket":"928d1c3aae6156b032f659956499e998","expires":60000},"userName":"admin","loginName":"admin","companyCode":"XINHUA","companyName":"新华水力发电","company":{"companyGUID":"42","companyName":"新华水力发电","parentGUID":"ROOT","parentName":null,"ownDistricts":null,"ownProvince":null,"ownLocation":null,"riverBasin":null,"factoryType":"01","proportionAssets":null,"companyCode":"XINHUA","isLeaf":"0","overview":"1","realTimeTable":"XINHUA","parentCompanyCode":null,"location":"1","titlePicture":"2012_12_13_10_40_32_-1244746321.jpg","screenJump":null,"safeDay":524,"sort":0,"elecPrice":0.0,"queryByTreeNode":false,"centerPoint":"116.49798,39.893902","labelxy1":null,"labelxy2":null,"boundarys":"中国","buildType":"8","imsBuilds":null,"otherInfo":"1"},"sex":null,"id":"5","roles":null,"headship":null}}
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
//        User user = null;
//        Identification identification = null;
//        LoginInfo loginInfo = new LoginInfo();
//        try {
//            User condition = new User();
//            condition.setLoginname(userName);
//
//            user = userManager.find(condition);
//
//            identification = IdentificationController.doIdentification(userName, password, user, identificationManager, mapCipher, expires_long, expires_short);
//            if (user != null && identification != null) {
//                loginInfo.setIdentification(identification);
//                loginInfo.setUserName(user.getName());
//                loginInfo.setLoginName(user.getLoginname());
//                loginInfo.setCompanyCode(user.getCompanyCode());
//                loginInfo.setCompanyName(user.getCompanyName());
//                loginInfo.setCompany(findCompany(user.getCompanyCode()));
//                loginInfo.setId(user.getId());
//                loginInfo.setSex(user.getSex());
//
//                loginInfo.setHeadship(user.getHeadship());
//                loginInfo.setRoles(user.getRoles());
//
//            } else {
//                success = false;
//                message = "用户名或密码错误";
//            }
//        } catch (Exception e) {
//            success = false;
//            message = e.getLocalizedMessage();
//            //message = String.format("%s : %s, %s  失败! \n\r %s", getModeleName(),id,"新建",message);
//        }
//
//        return new ExtRtvModel(success,loginInfo,message);
//    }
//
//    private Company findCompany(String companyCode) {
//        Company rtv = new Company();
//
//        rtv.setCompanyCode(companyCode);
//        rtv = companyManager.find(rtv);
//
//        return rtv;
//    }
//
//}
