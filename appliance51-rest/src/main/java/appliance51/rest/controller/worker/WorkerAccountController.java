package appliance51.rest.controller.worker;

import appliance51.dao.domain.Workman;
import appliance51.rest.model.RestResult;
import appliance51.rest.service.SessionService;
import appliance51.rest.service.UserAccountService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuananyun on 2016/9/11.
 */
@RestController(value = "workman")
@Api
public class WorkerAccountController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserAccountService accountService;

    /**
     * 师傅注册
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation(value = "师傅注册",notes = "师傅注册接口",httpMethod = "PUT")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public RestResult register(@RequestBody @ApiParam(required = true,value="workman") Workman workman) {
        /**
         * TODO 数据验证
         */
        String errorMsg = accountService.workmanRegister(workman);
        return RestResult.getRestResult(errorMsg, null);
    }




}
