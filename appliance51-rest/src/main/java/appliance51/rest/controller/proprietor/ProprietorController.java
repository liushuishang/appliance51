package appliance51.rest.controller.proprietor;

import appliance51.common.exception.EngineExceptionHelper;
import appliance51.common.exception.ExcepFactor;
import appliance51.dao.domain.FaultDescription;
import appliance51.dao.domain.ServiceOrder;
import appliance51.dao.repositories.FaultDescriptionRepository;
import appliance51.rest.dto.ProprietorOrder;
import appliance51.rest.dto.UserLoginResult;
import appliance51.rest.model.RestResult;
import appliance51.rest.service.OrderService;
import appliance51.rest.service.SystemDictionaryService;
import appliance51.rest.service.UserAccountService;
import appliance51.security.annotation.AuthInfo;
import appliance51.security.annotation.AuthScope;
import appliance51.security.annotation.AuthType;
import appliance51.security.constants.RequestHeaderConstant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private SystemDictionaryService dictionaryService;

    @Autowired
    private FaultDescriptionRepository faultDescriptionRepository;

    /**
     * 获取业主端的首页闪屏图片
     * @return
     */
    @RequestMapping(value = "/splash", method = RequestMethod.GET)
    @ApiOperation(value = "app进入广告图片", notes = "app进入广告图片接口", httpMethod = "GET")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                            required = true, dataType = "String", defaultValue = "proprietor"),
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_DEVICE, value = "客户端设备号",
                            required = true, dataType = "String", defaultValue = "test")
            }
    )
    public Object splash() {
        return RestResult.success(dictionaryService.getProprietorSplash());
    }

    /**
     * 家电维修收费说明接口
     * @return
     */
    @RequestMapping(value = "/charge", method = RequestMethod.GET)
    @ApiOperation(value = "家电维修收费说明", notes = "家电维修收费说明接口", httpMethod = "GET")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                            required = true, dataType = "String", defaultValue = "proprietor"),
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_DEVICE, value = "客户端设备号",
                            required = true, dataType = "String", defaultValue = "test")
            }
    )
    public Object charge() {
        return RestResult.success(dictionaryService.getChargeIllustrate());
    }

    @RequestMapping(value = "/fault", method = RequestMethod.GET)
    @ApiOperation(value = "家电故障描述", notes = "故障描述接口", httpMethod = "GET")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                            required = true, dataType = "String", defaultValue = "proprietor"),
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_DEVICE, value = "客户端设备号",
                            required = true, dataType = "String", defaultValue = "test"),
                    @ApiImplicitParam(paramType = "query",
                            name = "categoryId", value = "家电类别",
                            required = true, dataType = "String", defaultValue = "")
            }
    )
    public Object faultDescription(String categoryId) {
        Assert.notNull(categoryId);
        List<FaultDescription> data = faultDescriptionRepository.findAllByCategoryId(categoryId);
        return RestResult.success(data);
    }


    /**
     * 验证用户登录
     *
     * @param mobile
     * @param code
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.PUT)
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

    @RequestMapping(value = "order", method = RequestMethod.POST)
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
                            required = true, dataType = "ProprietorOrder",defaultValue = "null")
            }
    )
    @AuthInfo(needAuth = AuthType.REQUIRED, authScope = AuthScope.PROPRIETOR)
    public Object order(@RequestBody ProprietorOrder order) {
        boolean result = orderService.saveOrder(order);
        return RestResult.success(result);
    }

    @RequestMapping(value = "orderRetrieve", method = RequestMethod.GET)
    @ApiOperation(value = "订单查询", notes = "订单查询接口", httpMethod = "GET")
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
                    @ApiImplicitParam(paramType = "query",
                            name = "status", value = "订单状态，0：全部订单；1：未完成；2：已完成；3：未评论",
                            required = true, dataType = "Int")
            }
    )
    @AuthInfo(needAuth = AuthType.REQUIRED, authScope = AuthScope.PROPRIETOR)
    public Object orderRetrieve(int status)
    {
        if(status<0||status>3) throw EngineExceptionHelper.localException(ExcepFactor.E_PARAM_ERROR);
        List<ServiceOrder> data = orderService.orderRetrieveByStatus(status);
        return RestResult.success(data);
    }

}
