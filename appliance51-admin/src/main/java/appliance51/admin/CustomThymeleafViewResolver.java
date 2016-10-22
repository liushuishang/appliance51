package appliance51.admin;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.util.Locale;

/**
 * Created by yuananyun on 2016/10/22.
 */
public class CustomThymeleafViewResolver extends ThymeleafViewResolver {
    private ThymeleafViewResolver viewResolver;

    public CustomThymeleafViewResolver(ThymeleafViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    @Override
    protected View createView(String viewName, Locale locale) throws Exception {
        View view = super.createView(viewName, locale);

        return view;
    }
}
