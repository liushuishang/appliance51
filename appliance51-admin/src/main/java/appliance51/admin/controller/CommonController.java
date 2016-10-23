package appliance51.admin.controller;

import appliance51.admin.event.EventManager;
import appliance51.admin.event.EventType;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by yuananyun on 2016/10/22.
 */
@Controller
@RequestMapping("/")
public class CommonController {

    @Autowired
    private EventManager eventManager;

    @RequestMapping(value = {"/{entityName}/create"}, method = RequestMethod.GET)
    public ModelAndView create(@PathVariable(value = "entityName") String entityName) {
        ModelAndView mv = new ModelAndView(entityName + "_form");
        mv.addObject("action", "create");
        mv.addObject("entityName", entityName);
        //触发事件
        eventManager.fire(EventType.BEFORE_CREATE_FORM, entityName, mv);
        return mv;
    }

    @RequestMapping(value = {"/{entityName}/edit/{id}"}, method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request, @PathVariable(value = "entityName") String entityName,
                             @PathVariable(value = "id") String id) {
        CrudRepository respository = getCrudRepository(request, entityName);
        Object entity = respository.findOne(id);
        ModelAndView mv = new ModelAndView(entityName + "_form");
        mv.addObject("action", "edit");
        mv.addObject("entityName", entityName);
        mv.addObject("entity", entity);
        //触发事件
        eventManager.fire(EventType.BEFORE_EDIT_FORM, entityName, mv);
        return mv;
    }

    @RequestMapping(value = "/{entityName}/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(HttpServletRequest request, @PathVariable(value = "entityName") String entityName, @RequestBody Map paramsMap) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        CrudRepository respositoryHolder = getCrudRepository(request, entityName);
        Object entityInstance = getEntityInstance(entityName);
        BeanUtils.populate(entityInstance, paramsMap);
        return respositoryHolder.save(entityInstance) != null;
    }

    @RequestMapping(value = "/{entityName}/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(HttpServletRequest request, @PathVariable(value = "entityName") String entityName, String ids) {
        if (StringUtils.isBlank(ids)) return false;
        CrudRepository respositoryHolder = getCrudRepository(request, entityName);
        List<String> idList = Arrays.asList(StringUtils.split(ids, ","));
        //触发事件
        eventManager.fire(EventType.BEFORE_DELETED, entityName, ids);
        for (String id : idList) {
            respositoryHolder.delete(id);
        }
        return true;
    }


    private Object getEntityInstance(String entityName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class entityClass = Class.forName("appliance51.dao.domain." + entityName.replace(entityName.substring(0, 1), entityName.substring(0, 1).toUpperCase()));
        return entityClass.newInstance();
    }

    private CrudRepository getCrudRepository(HttpServletRequest request, String entityName) {
        String repositoryName = entityName + "Respository";
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        return applicationContext.getBean(repositoryName, CrudRepository.class);
    }
}
