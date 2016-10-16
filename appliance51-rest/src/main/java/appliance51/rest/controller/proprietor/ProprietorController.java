package appliance51.rest.controller.proprietor;

import appliance51.rest.dto.ProprietorOrder;
import appliance51.rest.dto.UserLoginResult;
import appliance51.rest.model.RestResult;
import appliance51.rest.service.OrderService;
import appliance51.rest.service.UserAccountService;
import appliance51.security.annotation.AuthInfo;
import appliance51.security.annotation.AuthScope;
import appliance51.security.annotation.AuthType;
import appliance51.security.constants.RequestHeaderConstant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private OrderService orderService;

    /**
     * 验证用户登录
     *
     * @param mobile
     * @param code
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    @ApiOperation(value = "业主登录", notes = "业主登录接口", httpMethod = "PUT")
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
                            required = true, dataType = "String", defaultValue = ""),
                    @ApiImplicitParam(paramType = "query",
                            name = "code", value = "手机动态验证码",
                            required = true, dataType = "String", defaultValue = "")
            }
    )
    public Object login(
            String mobile,
            String code) {
        UserLoginResult result = accountService.proprietorLogin(mobile, code);
        return RestResult.success(result);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ApiOperation(value = "业主下订单", notes = "业主下订单接口", httpMethod = "POST")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                            required = true, dataType = "String", defaultValue = "proprietor"),
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_DEVICE, value = "客户端设备号",
                            required = true, dataType = "String", defaultValue = "test"),
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_TOKEN, value = "Token",
                            required = true, dataType = "String", defaultValue = "test"),
                    @ApiImplicitParam(paramType = "body",
                            name = "order", value = "订单详情",
                            required = true, dataType = "ServiceOrder"),
            }
    )
    @AuthInfo(needAuth = AuthType.REQUIRED, authScope = AuthScope.PROPRIETOR)
    public Object order(@RequestBody ProprietorOrder order) {
        boolean result = orderService.saveOrder(order);
        return RestResult.success(result);
    }


}
