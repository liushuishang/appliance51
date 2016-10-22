package appliance51.admin.controller;

import appliance51.admin.model.QueryResult;
import appliance51.dao.domain.ServiceCategory;
import appliance51.dao.domain.ServiceItem;
import appliance51.dao.repositories.ServiceCategoryRespository;
import appliance51.dao.repositories.ServiceItemRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by yuananyun on 2016/10/7.
 */
@Controller
@RequestMapping("/service")
public class BusServiceController {

//    @Autowired
//    private CityRegionRespository regionRepository;

    @Autowired
    private ServiceCategoryRespository categoryRepository;

    @Autowired
    private ServiceItemRespository itemRepository;

    @RequestMapping(value = "region", method = RequestMethod.GET)
    public ModelAndView region() {
        ModelAndView mv = new ModelAndView("region_manager");
        return mv;
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public ModelAndView category() {
        ModelAndView mv = new ModelAndView("category_manager");
        return mv;
    }
    @RequestMapping(value = "category", method = RequestMethod.POST)
    @ResponseBody
    public Object category(@RequestBody Map paramsMap) {
        return true;
    }

    @RequestMapping(value = "/data/categoryList", method = RequestMethod.GET)
    @ResponseBody
    public Object categoryListData(int pageSize, int pageIndex) {
        Sort sort = new Sort(Sort.Direction.ASC, "order");
        Page<ServiceCategory> serviceCategoryPage = categoryRepository.findAll(new PageRequest(pageIndex, pageSize,sort));
        return new QueryResult<ServiceCategory>(serviceCategoryPage.getContent(), serviceCategoryPage.getTotalElements());
    }

    @RequestMapping(value = "item", method = RequestMethod.GET)
    public ModelAndView item() {
        ModelAndView mv = new ModelAndView("item_manager");
        return mv;
    }
    @RequestMapping(value = "/data/itemList", method = RequestMethod.GET)
    @ResponseBody
    public Object itemListData(int pageSize, int pageIndex) {
        Sort sort = new Sort(Sort.Direction.ASC, "categoryId");
        Page<ServiceItem> serviceItemPage = itemRepository.findAll(new PageRequest(pageIndex, pageSize,sort));
        return new QueryResult<ServiceItem>(serviceItemPage.getContent(), serviceItemPage.getTotalElements());
    }



    /*****************************************************************表单相关*************************************************************/


}
