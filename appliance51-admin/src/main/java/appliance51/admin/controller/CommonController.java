package appliance51.admin.controller;

import appliance51.admin.event.EventManager;
import appliance51.admin.event.EventType;
import appliance51.admin.model.QueryResult;
import appliance51.admin.service.CommonService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = {"/{entityName}/create"}, method = RequestMethod.GET)
    public ModelAndView create(@PathVariable(value = "entityName") String entityName) {
        ModelAndView mv = new ModelAndView(entityName + "_form");
        mv.addObject("action", "create");
        mv.addObject("entityName", entityName);
        //触发事件
        eventManager.fire(EventType.BEFORE_CREATE_FORM, entityName, mv);
        return mv;
    }

    @RequestMapping(value = {"/{entityName}/edit"}, method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request, @PathVariable(value = "entityName") String entityName,
                             @RequestParam(value = "id") String id) {
        CrudRepository respository = commonService.getCrudRepository(request, entityName);
        Object entity = respository.findOne(id);
        ModelAndView mv = new ModelAndView(entityName + "_form");
        mv.addObject("action", "edit");
        mv.addObject("entityName", entityName);
        mv.addObject("entity", entity);
        //触发事件
        eventManager.fire(EventType.BEFORE_EDIT_FORM, entityName, mv);
        return mv;
    }

    @RequestMapping(value = {"/{entityName}/review"}, method = RequestMethod.GET)
    public ModelAndView review(HttpServletRequest request, @PathVariable(value = "entityName") String entityName,
                               @RequestParam(value = "id") String id) {
        CrudRepository respository = commonService.getCrudRepository(request, entityName);
        Object entity = respository.findOne(id);
        ModelAndView mv = new ModelAndView(entityName + "_form");
        mv.addObject("action", "review");
        mv.addObject("entityName", entityName);
        mv.addObject("entity", entity);
        //触发事件
        eventManager.fire(EventType.BEFORE_REVIEW, entityName, mv);
        return mv;
    }

    @RequestMapping(value = "/{entityName}/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(HttpServletRequest request, @PathVariable(value = "entityName") String entityName, @RequestBody Map paramsMap) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        CrudRepository respositoryHolder = commonService.getCrudRepository(request, entityName);
        Object entityInstance = commonService.getEntityInstance(entityName);
        BeanUtils.populate(entityInstance, paramsMap);
        Object result = respositoryHolder.save(entityInstance);
        return result != null;
    }

    @RequestMapping(value = "/{entityName}/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(HttpServletRequest request, @PathVariable(value = "entityName") String entityName, String ids) {
        if (StringUtils.isBlank(ids)) return false;
        CrudRepository respositoryHolder = commonService.getCrudRepository(request, entityName);
        List<String> idList = Arrays.asList(StringUtils.split(ids, ","));
        //触发事件
        eventManager.fire(EventType.BEFORE_DELETED, entityName, ids);
        for (String id : idList) {
            respositoryHolder.delete(id);
        }
        eventManager.fire(EventType.AFTER_DELETED, entityName, ids);
        return true;
    }

    @RequestMapping(value = "/data/{entityName}/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(HttpServletRequest request, @PathVariable(value = "entityName") String entityName, int pageSize, int pageIndex) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        PagingAndSortingRepository respositoryHolder = commonService.getCrudRepository(request, entityName);
        if (respositoryHolder == null) return null;
        
//        Sort sort = new Sort(Sort.Direction.ASC, "order");
        Page serviceCategoryPage = respositoryHolder.findAll(new PageRequest(pageIndex, pageSize));
        return new QueryResult(serviceCategoryPage.getContent(), serviceCategoryPage.getTotalElements());
    }


}
