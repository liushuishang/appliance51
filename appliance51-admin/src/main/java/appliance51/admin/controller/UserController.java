package appliance51.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yuananyun on 2016/10/29.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/proprietor",method = RequestMethod.GET)
    public ModelAndView proprietor()
    {
        ModelAndView mv = new ModelAndView("proprietor_manager");
        return mv;
    }
    @RequestMapping(value = "/workman",method = RequestMethod.GET)
    public ModelAndView workman()
    {
        ModelAndView mv = new ModelAndView("workman_manager");
        return mv;
    }
}
