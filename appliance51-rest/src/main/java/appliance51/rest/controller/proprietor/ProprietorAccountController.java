package appliance51.rest.controller.proprietor;

import appliance51.rest.model.RestResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuananyun on 2016/9/11.
 */
@RestController(value = "proprietor")
public class ProprietorAccountController  {


    /**
     * 验证用户登录
     *
     * @param accountName
     * @param code
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.PUT)
    @ApiOperation(value = "用户登录", notes = "用户登录验证和处理")
    public Object login(
            @ApiParam(name = "accountName", value = "手机号")
                    String accountName,
            @ApiParam(name = "code", value = "手机验证码")
                    String code) {
        Assert.notNull(accountName);
        Assert.notNull(code);

        return RestResult.success("你好");
    }

}
