package appliance51.admin.event;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 事件管理器
 * Created by yuananyun on 2016/10/23.
 */
@Component
public class EventManager {
    private ConcurrentMap<String, InvokeProxy> eventRegistries;

    public EventManager() {
        eventRegistries = new ConcurrentHashMap<>();
    }

    /**
     * 触发一个事件
     * @param entityName
     * @param params
     */
    public void fire(EventType eventType,String entityName,Object ... params){
        String key=getEventKey(eventType,entityName);
        if (!eventRegistries.containsKey(key)) return;
        InvokeProxy invokeProxy =eventRegistries.get(key);
        invokeProxy.invoke(params);
    }

    public boolean register(EventType eventType, EventHandler handler, Method method) {
        String key = getEventKey(eventType, handler.getEntityName());
        if (eventRegistries.containsKey(key)) return false;

        InvokeProxy invokeProxy = new InvokeProxy(handler, method);
        eventRegistries.put(key, invokeProxy);

        return eventRegistries.containsKey(key);
    }

    public boolean remove() {
        return false;
    }

    private String getEventKey(EventType eventType, String entityName) {
        return eventType.getValue() + "@" + entityName;
    }

}
