package appliance51.admin.event;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by yuananyun on 2016/10/23.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Inherited
public @interface EventObject {
}
