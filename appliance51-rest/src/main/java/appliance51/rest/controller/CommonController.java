package appliance51.rest.controller;

import appliance51.common.exception.EngineExceptionHelper;
import appliance51.common.exception.ExcepFactor;
import appliance51.dao.domain.ServiceCategory;
import appliance51.dao.repositories.ServiceCategoryRepository;
import appliance51.rest.exception.RequestExcepFactor;
import appliance51.rest.model.RestResult;
import appliance51.rest.service.UserAccountService;
import appliance51.security.annotation.AuthInfo;
import appliance51.security.annotation.AuthScope;
import appliance51.security.annotation.AuthType;
import appliance51.security.constants.RequestHeaderConstant;
import appliance51.security.service.MobileCodeService;
import appliance51.store.service.FileStoreService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private ServiceCategoryRepository categoryRespository;

    @Autowired
    private FileStoreService fileStoreService;


    @RequestMapping(value = {"", "/", "/index"})
    public Object index() {
        return "api document link:/swagger-ui.html";
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

//    @RequestMapping(value = "/common/regionList", method = RequestMethod.GET)
//    @ApiOperation(value = "获取服务城市地区列表", notes = "获取服务城市地区列表接口", httpMethod = "GET")
//    @ApiImplicitParams(
//            @ApiImplicitParam(paramType = "header",
//                    name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
//                    required = true, dataType = "String", defaultValue = "proprietor")
//    )
//    @AuthInfo(needAuth = AuthType.OPTION, authScope = AuthScope.ALL)
//    public Object regionList() {
//        Iterable<CityRegion> data = cityRegionRespository.findAll();
//        return RestResult.success(data);
//    }


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


    @RequestMapping(value = "/common/upload", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "文件上传", notes = "文件上传接口", httpMethod = "POST")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_TYPE, value = "客户端标识",
                            required = true, dataType = "String", defaultValue = "proprietor"),
                    @ApiImplicitParam(paramType = "header",
                            name = RequestHeaderConstant.CLIENT_TOKEN, value = "Token",
                            required = true, dataType = "String", defaultValue = "test")
            }
    )
    @AuthInfo(needAuth = AuthType.REQUIRED, authScope = AuthScope.ALL)
    public Object upload(HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        if (!file.isEmpty()) {
            String fileDownloadUrl = fileStoreService.upload(file.getBytes(), file.getOriginalFilename(), null);
            if (!StringUtils.isEmpty(fileDownloadUrl))
                return RestResult.success(fileDownloadUrl);
            else throw EngineExceptionHelper.localException(RequestExcepFactor.REQUEST_FAILURE, "图片上传失败！");
        } else {
            throw EngineExceptionHelper.localException(ExcepFactor.E_PARAM_MISS_ERROR, "上传的文件不能为空！");
        }
    }


}
