package appliance51.rest.controller;

import appliance51.dao.domain.ServiceCategory;
import appliance51.dao.repositories.ServiceCategoryRespository;
import appliance51.rest.model.RestResult;
import appliance51.rest.service.UserAccountService;
import appliance51.security.annotation.AuthInfo;
import appliance51.security.annotation.AuthScope;
import appliance51.security.annotation.AuthType;
import appliance51.security.constants.RequestHeaderConstant;
import appliance51.security.service.MobileCodeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuananyun on 2016/9/17.
 */
@RestController
@RequestMapping(value = "/")
public class CommonController {

    @Autowired
    private MobileCodeService codeService;

    @Autowired
    private UserAccountService accountService;

    @Autowired
    private ServiceCategoryRespository categoryRespository;

    @RequestMapping(value = {"", "/", "/index"})
    public Object index() {
        return "api document link：/swagger-ui.html";
    }

    @RequestMapping(value = "/common/mobileCode", method = RequestMethod.GET)
    @ApiOperation(value = "发送手机验证码", notes = "发送手机验证码接口", httpMethod = "GET")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                            required = true, dataType = "String", defaultValue = "proprietor"),
                    @ApiImplicitParam(paramType = "query",
                            name = "mobile", value = "手机号码",
                            required = true, dataType = "String", defaultValue = "")
            }
    )
    @AuthInfo(needAuth = AuthType.OPTION, authScope = AuthScope.ALL)
    public Object sendMobileCode(String mobile) {
        boolean isSuccess = codeService.sendMobileCode(mobile, "");
        return RestResult.success(isSuccess ? "发送成功" : "发送失败");
    }

    @RequestMapping(value = "/common/serviceList", method = RequestMethod.GET)
    @ApiOperation(value = "获取服务分类和列表", notes = "获取服务分类和列表接口", httpMethod = "GET")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header",
                    name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                    required = true, dataType = "String", defaultValue = "proprietor")
    )
    @AuthInfo(needAuth = AuthType.OPTION, authScope = AuthScope.ALL)
    public Object serviceList() {
        Iterable<ServiceCategory> data = categoryRespository.findAll();
        return RestResult.success(data);
    }

    @RequestMapping(value = "/common/gainToken", method = RequestMethod.GET)
    @ApiOperation(value = "获取Token", notes = "获取Token接口", httpMethod = "GET")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                            required = true, dataType = "String", defaultValue = "proprietor"),
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_DEVICE, value = "客户端设备号",
                            required = true, dataType = "String", defaultValue = "test"),
                    @ApiImplicitParam(paramType = "query",
                            name = "mobile", value = "手机号码",
                            required = true, dataType = "String", defaultValue = "")
            }
    )
    @AuthInfo(needAuth = AuthType.OPTION, authScope = AuthScope.ALL)
    public Object gainToken(String mobile, @RequestHeader(RequestHeaderConstant.CLIENT_TYPE) String accountType,
                            @RequestHeader(RequestHeaderConstant.CLIENT_DEVICE) String deviceId) {
        String token = accountService.gainToken(mobile, deviceId, accountType);
        return RestResult.success(token);
    }


}
