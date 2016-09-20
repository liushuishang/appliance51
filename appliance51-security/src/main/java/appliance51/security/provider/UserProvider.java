package appliance51.security.provider;


import appliance51.security.model.AuthRequest;

public interface UserProvider {

    /**
     * 是否为有效用户
     *
     * @param uid
     * @return
     */
    public boolean isValidUser(String uid);

    /**
     * 该用户是否有权限访问
     *
     * @param request
     * @param uid
     */
    boolean checkCanAccess(AuthRequest request, long uid);


    /**
     * 通过用户名密码认证用户
     *
     * @param loginName 不能为空
     * @param password  不能为空
     * @return uid
     */
    public String authUser(String loginName, String password);

}
