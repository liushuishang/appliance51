package appliance51.rest.controller.workerman;

import appliance51.dao.domain.Workman;
import appliance51.rest.dto.UserLoginResult;
import appliance51.rest.dto.WorkmanRegistration;
import appliance51.rest.model.RestResult;
import appliance51.rest.service.UserAccountService;
import appliance51.security.annotation.AuthInfo;
import appliance51.security.annotation.AuthScope;
import appliance51.security.annotation.AuthType;
import appliance51.security.constants.RequestHeaderConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by yuananyun on 2016/9/11.
 */
@RestController
@RequestMapping("/workman")
@Api(value = "workman", description = "师傅端帐号接口", consumes = "application/json")
public class WorkmanController {

    @Autowired
    private UserAccountService accountService;

    /**
     * 师傅注册
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ApiOperation(value = "师傅注册", notes = "师傅注册接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",
                    name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                    required = true, dataType = "String", defaultValue = "workman"),
            @ApiImplicitParam(paramType = "body",
                    name = "workman", value = "师傅的注册信息",
                    required = true, dataType = "WorkmanRegistration", defaultValue = "null")}
    )
    public RestResult register(@RequestBody WorkmanRegistration workman) {
        UserLoginResult loginResult = accountService.workmanRegister(workman);
        return RestResult.success(loginResult);
    }

    @RequestMapping(value = "login", method = RequestMethod.PUT)
    @ApiOperation(value = "师傅登录", notes = "师傅登录接口", httpMethod = "PUT")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                            required = true, dataType = "String", defaultValue = "workman"),
                    @ApiImplicitParam(paramType = "query",
                            name = "mobile", value = "手机号码",
                            required = true, dataType = "String", defaultValue = ""),
                    @ApiImplicitParam(paramType = "query",
                            name = "password", value = "登录的密码",
                            required = false, dataType = "String", defaultValue = ""),
                    @ApiImplicitParam(paramType = "query",
                            name = "code", value = "手机动态验证码",
                            required = false, dataType = "String", defaultValue = ""),
                    @ApiImplicitParam(paramType = "query",
                            name = "loginType", value = "登录的方式,0:密码;1:手机验证码",
                            required = true, dataType = "Integer", defaultValue = "0"),
            }
    )
    public RestResult login(
            String mobile,
            String password,
            String code,
            Integer loginType
    ) {
        UserLoginResult result = accountService.workmanLogin(mobile, password, code, loginType);
        return RestResult.success(result);
    }

    /**
     * 获取师傅的信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取师傅的信息", notes = "获取师傅的信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",
                    name = RequestHeaderConstant.CLIENT_TOKEN, value = "Token",
                    required = true, dataType = "String", defaultValue = ""),
            @ApiImplicitParam(paramType = "header",
                    name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                    required = true, dataType = "String", defaultValue = "workman"),
            @ApiImplicitParam(paramType = "path",
                    name = "id", value = "师傅的id",
                    required = true, dataType = "String", defaultValue = "")}
    )
    @AuthInfo(needAuth = AuthType.REQUIRED, authScope = AuthScope.WORKMAN)
    public RestResult getWorkmanInfo(@PathVariable("id") String id) {
        Workman result = accountService.findWorkman(id);
        return RestResult.success(result);
    }


}
