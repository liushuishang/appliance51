package appliance51.rest.controller.proprietor;

import appliance51.rest.dto.UserLoginResult;
import appliance51.rest.model.RestResult;
import appliance51.rest.service.UserAccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuananyun on 2016/9/11.
 */
@RestController
@RequestMapping("proprietor")
public class ProprietorController {

    @Autowired
    private UserAccountService accountService;
    /**
     * 验证用户登录
     *
     * @param mobile
     * @param code
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    @ApiOperation(value = "业主登录", notes = "业主登录接口",httpMethod = "PUT")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "query",
                            name = "mobile", value = "手机号码",
                            required = true, dataType = "String", defaultValue = ""),
                    @ApiImplicitParam(paramType = "query",
                            name = "code", value = "手机动态验证码",
                            required = false, dataType = "String", defaultValue = "")
            }
    )
    public Object login(
                    String mobile,
                    String code) {
        UserLoginResult result = accountService.proprietorLogin(mobile, code);
        return RestResult.success(result);
    }

}
