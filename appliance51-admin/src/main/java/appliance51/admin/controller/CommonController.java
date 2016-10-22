package appliance51.admin.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuananyun on 2016/10/22.
 */
@Controller
@RequestMapping("/")
public class CommonController {
    @RequestMapping(value = "/{entityName}/create", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable(value = "entityName") String entityName) {
        ModelAndView mv = new ModelAndView(entityName + "_form");
        mv.addObject("action", "create");
        mv.addObject("entityName", entityName);
        return mv;
    }

    @RequestMapping(value = "/{entityName}/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(HttpServletRequest request, @PathVariable(value = "entityName") String entityName, @RequestBody Map paramsMap) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String repositoryName = entityName + "Respository";
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        CrudRepository respositoryHolder = applicationContext.getBean(repositoryName, CrudRepository.class);
        Class entityClass = Class.forName("appliance51.dao.domain." + entityName.replace(entityName.substring(0, 1), entityName.substring(0, 1).toUpperCase()));
        Object entityInstance = entityClass.newInstance();
        BeanUtils.populate(entityInstance, paramsMap);
        return respositoryHolder.save(entityInstance) != null;
    }
}
