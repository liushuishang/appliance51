package appliance51.admin.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yuananyun on 2016/10/23.
 */
public class InvokeProxy {
    private static Logger logger = LoggerFactory.getLogger(InvokeProxy.class);
    private EventHandler handler;
    private Method method;

    public InvokeProxy(EventHandler handler, Method method) {
        this.handler = handler;
        this.method = method;
    }

    public EventHandler getHandler() {
        return handler;
    }

    public void setHandler(EventHandler handler) {
        this.handler = handler;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void invoke(Object[] params) {
        try {
            method.invoke(handler, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
