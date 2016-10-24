package appliance51.admin;

import org.springframework.boot.ImageBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;


@SpringBootApplication
@ImportResource(locations = {"classpath*:spring/*.xml"})
public class Appliance51AdminApplication {

    public static void main(String[] args) {
        SpringApplication springApplication =new SpringApplication(Appliance51AdminApplication.class);
        springApplication.addListeners(new EventRegisterListener());
//        springApplication.setBanner(new ImageBanner(new ClassPathResource("classpath:/banner.jpg")));
        springApplication.run(args);
    }

}
