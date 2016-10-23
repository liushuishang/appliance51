package appliance51.admin;

import appliance51.admin.event.EventHandler;
import appliance51.admin.event.EventManager;
import appliance51.admin.event.EventRegister;
import appliance51.admin.event.EventType;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by ucs_yuananyun on 2016/5/27.
 */
public class EventRegisterListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        WebApplicationContext context = (WebApplicationContext) contextRefreshedEvent.getApplicationContext();
        Map<String, EventHandler> eventHandlerList = context.getBeansOfType(EventHandler.class);
        EventManager eventManager = context.getBean(EventManager.class);
        for (Map.Entry<String, EventHandler> entry : eventHandlerList.entrySet()) {
            EventHandler handler = entry.getValue();
            Class handlerClass = handler.getClass();
            Method[] methods = handlerClass.getMethods();
            for (Method method : methods) {
                EventRegister registerAnnation = method.getAnnotation(EventRegister.class);
                if (registerAnnation == null) continue;
                EventType eventType = registerAnnation.value();
                if(eventType==null) continue;
                eventManager.register(eventType, handler, method);
            }
        }
    }
}
