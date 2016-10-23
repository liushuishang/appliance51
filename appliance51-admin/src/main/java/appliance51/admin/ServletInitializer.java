package appliance51.admin;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.listeners(new EventRegisterListener());
        return application.sources(Appliance51AdminApplication.class);
    }


}
