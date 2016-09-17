package appliance51.rest.controller.workerman;

import appliance51.dao.domain.Workman;
import appliance51.rest.model.RestResult;
import appliance51.rest.service.SessionService;
import appliance51.rest.service.UserAccountService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yuananyun on 2016/9/11.
 */
@RestController
@Api(value = "workman", description = "师傅端帐号接口", consumes = "application/json")
public class WorkmanAccountController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserAccountService accountService;

    /**
     * 师傅注册
     */
    @RequestMapping(value = "/workman/register", method = RequestMethod.POST)
    @ApiOperation(value = "师傅注册", notes = "师傅注册接口", httpMethod = "POST")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "body",
                    name = "workman", value = "师傅的注册信息",
                    required = true, dataType = "Workman", defaultValue = "null")
    )
    public RestResult register(@RequestBody Workman workman) {
        String errorMsg = accountService.workmanRegister(workman);
        return RestResult.getRestResult(errorMsg, null);
    }

    @RequestMapping(value = "/workman/login", method = RequestMethod.PUT)
    @ApiOperation(value = "师傅登录", notes = "师傅登录接口", httpMethod = "PUT")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "query",
                            name = "acountName", value = "登录的账号名",
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
            String acountName,
            String password,
            String code,
            Integer loginType
    ) {
        String errorMsg = "";

        return RestResult.getRestResult(errorMsg, null);
    }



}
