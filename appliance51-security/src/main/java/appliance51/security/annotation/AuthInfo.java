package appliance51.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yuananyun on 2016/9/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthInfo {
    /**
     * 表示是否需要认证
     * @return
     */
    AuthType needAuth() default AuthType.REQUIRED;

    /**
     * 表示授权认证的范围
     * @return
     */
    AuthScope authScope() default AuthScope.ALL;

    String description() default "";
}
