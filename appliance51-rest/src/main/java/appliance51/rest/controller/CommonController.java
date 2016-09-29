package appliance51.rest.controller;

import appliance51.rest.dto.UserLoginResult;
import appliance51.rest.model.RestResult;
import appliance51.security.service.MobileCodeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yuananyun on 2016/9/17.
 */
@RestController
@RequestMapping(value = "/")
public class CommonController {

    @Autowired
    private MobileCodeService codeService;

    @RequestMapping(value = {"", "/", "/index"})
    public Object index() {
        return "Hello,请访问如下链接查看接口：/swagger-ui.html";
    }

    @RequestMapping(value = "/common/mobileCode", method = RequestMethod.GET)
    @ApiOperation(value = "发送手机验证码", notes = "发送手机验证码接口", httpMethod = "GET")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "query",
                            name = "mobile", value = "手机号码",
                            required = true, dataType = "String", defaultValue = "")
            }
    )
    public Object sendMobileCode(String mobile) {
        boolean isSuccess = codeService.sendMobileCode(mobile, "");
        return RestResult.success(isSuccess ? "发送成功" : "发送失败");
    }


}
