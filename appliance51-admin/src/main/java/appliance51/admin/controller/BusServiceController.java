package appliance51.admin.controller;

import appliance51.admin.model.QueryResult;
import appliance51.dao.domain.CityRegion;
import appliance51.dao.domain.ServiceCategory;
import appliance51.dao.repositories.CityRegionRespository;
import appliance51.dao.repositories.ServiceCategoryRespository;
import appliance51.dao.repositories.ServiceItemRespository;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yuananyun on 2016/10/7.
 */
@Controller
@RequestMapping("/service")
public class BusServiceController {

    @Autowired
    private CityRegionRespository regionRepository;

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
    public Object category(String description,String name,String parentId) {
        return true;
    }

    @RequestMapping(value = "item", method = RequestMethod.GET)
    public ModelAndView item() {
        ModelAndView mv = new ModelAndView("item_manager");
        return mv;
    }

    @RequestMapping(value = "regionList", method = RequestMethod.GET)
    @ResponseBody
    public Object regionList(int pageSize, int pageIndex) {
        Page<CityRegion> cityRegions = regionRepository.findAll(new PageRequest(pageIndex, pageSize));
        return new QueryResult<CityRegion>(cityRegions.getContent(), cityRegions.getTotalElements());
    }

}
