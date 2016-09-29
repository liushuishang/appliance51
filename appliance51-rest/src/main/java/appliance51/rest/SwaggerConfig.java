package appliance51.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by yuananyun on 2016/9/6.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    /**
     * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/映射
     * 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义（我没有尝试）
     * 重写该方法需要 extends WebMvcConfigurerAdapter
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Bean
    public Docket ProprietorApi() {
        ResponseMessage message = new ResponseMessageBuilder().code(500)
                .message("服务出错啦").responseModel(new ModelRef("Error")).build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("proprietor")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
//                .paths(or(regex("/.*")))//过滤的接口
//                .paths(not(regex("/error")))//过滤的接口
                .paths(or(regex("/proprietor/.*")))
                .build()
                .globalResponseMessage(RequestMethod.GET, newArrayList(message))
                .forCodeGeneration(true)
                .apiInfo(ProprietorApiInfo());
    }


    private ApiInfo ProprietorApiInfo() {
        ApiInfo apiInfo = new ApiInfo("家电无忧API",//大标题
                "业主手机端接口<br/>" +
                        "每一次的请求头中请附加如下参数：<br/>" +
                        "X-Client-Type:客户端的类型，指定为proprietor<br/>" +
                        "X-Client-Paltform:手机操作系统，android或ios<br/>" +
                        "X-Client-Version:客户端的版本，如V1.0<br/>" +
                        "X-Device:设备相关的参数，比如设备id，IEE等",//小标题
                "1.0",//版本
                "",
                "liushuishang",//作者
                "",//链接显示文字
                ""//网站链接
        );
        return apiInfo;
    }

    @Bean
    public Docket WorkmanApi() {
        ResponseMessage message = new ResponseMessageBuilder().code(500)
                .message("服务出错啦").responseModel(new ModelRef("Error")).build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("workman")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
//                .paths(or(regex("/.*")))//过滤的接口
//                .paths(not(regex("/error")))//过滤的接口
                .paths(or(regex("/workman/.*")))
                .build()
                .globalResponseMessage(RequestMethod.GET, newArrayList(message))
                .forCodeGeneration(true)
                .apiInfo(ProprietorApiInfo());
    }


    private ApiInfo WorkmanApiInfo() {
        ApiInfo apiInfo = new ApiInfo("家电无忧API",//大标题
                "师傅手机端接口<br/>" +
                        "每一次的请求头中请附加如下参数：<br/>" +
                        "X-Client-Type:客户端的类型，指定为workman<br/>" +
                        "X-Client-Paltform:手机操作系统，android或ios<br/>" +
                        "X-Client-Version:客户端的版本，如V1.0<br/>" +
                        "X-Device:设备相关的参数，比如设备id，IEE等",//小标题
                "1.0",//版本
                "No terms of service",
                "liushuishang",//作者
                "",//链接显示文字
                ""//网站链接
        );
        return apiInfo;
    }

    @Bean
    public Docket CommonApi() {
        ResponseMessage message = new ResponseMessageBuilder().code(500)
                .message("服务出错啦").responseModel(new ModelRef("Error")).build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("common")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
//                .paths(or(regex("/.*")))//过滤的接口
//                .paths(not(regex("/error")))//过滤的接口
                .paths(or(regex("/common/.*")))
                .build()
                .globalResponseMessage(RequestMethod.GET, newArrayList(message))
                .forCodeGeneration(true)
                .apiInfo(CommonApiInfo());
    }


    private ApiInfo CommonApiInfo() {
        ApiInfo apiInfo = new ApiInfo("家电无忧API",//大标题
                "通用接口",//小标题
                "1.0",//版本
                "No terms of service",
                "liushuishang",//作者
                "",//链接显示文字
                ""//网站链接
        );
        return apiInfo;
    }

}
