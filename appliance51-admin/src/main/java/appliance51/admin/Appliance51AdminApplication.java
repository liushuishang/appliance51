package appliance51.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource(locations = {"classpath*:spring/*.xml"})
public class Appliance51AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(Appliance51AdminApplication.class, args);
    }

}
