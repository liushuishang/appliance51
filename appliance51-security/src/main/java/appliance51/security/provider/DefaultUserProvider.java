//package appliance51.security.provider;
//
//import appliance51.dao.domain.User;
//import appliance51.security.model.AuthRequest;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * Authors: sofn
// * Version: 1.0  Created at 2015-10-02 22:10.
// */
//@Service
//public class DefaultUserProvider implements UserProvider {
//    @Resource
//    private UserService userService;
//
//    @Override
//    public boolean isValidUser(String uid) {
//        return userService.getAndRemove(uid) != null;
//    }
//
//    @Override
//    public boolean checkCanAccess(AuthRequest request, long uid) {
//        return true;
//    }
//
//    @Override
//    public String authUser(String loginName, String password) {
//        User user = userService.login(loginName, password);
//        return user != null ? user.getId() : null;
//    }
//}
