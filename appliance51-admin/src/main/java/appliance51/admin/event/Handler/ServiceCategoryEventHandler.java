package appliance51.admin.event.Handler;

import appliance51.admin.event.EventHandler;
import appliance51.admin.event.EventObject;
import appliance51.admin.event.EventRegister;
import appliance51.admin.event.EventType;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yuananyun on 2016/10/23.
 */
@EventObject
public class ServiceCategoryEventHandler extends EventHandler {

//    @Autowired
//    private ServiceCategoryRespository categoryRespository;


    @EventRegister(EventType.BEFORE_CREATE_FORM)
    public void beforeCreateForm(ModelAndView mv) {
        attachCagetoryList(mv);
    }


    private void attachCagetoryList(ModelAndView mv) {
//        List<Object[]> rowList = categoryRespository.findAllWithIdAndName();
//        List<NamingObject> categoryList = new ArrayList<>();
//        for (Object[] objects : rowList) {
//            categoryList.add(new NamingObject(String.valueOf(objects[0]), String.valueOf(objects[1])));
//        }
//        mv.addObject("categoryList", categoryList);
    }


    @EventRegister(EventType.BEFORE_EDIT_FORM)
    public void beforeEditForm(ModelAndView mv) {
        attachCagetoryList(mv);
    }

    @EventRegister(EventType.BEFORE_DELETED)
    public void beforeDeleted(String deleteIds) {

    }

    @Override
    public String getEntityName() {
        return "serviceCategory";
    }
}
