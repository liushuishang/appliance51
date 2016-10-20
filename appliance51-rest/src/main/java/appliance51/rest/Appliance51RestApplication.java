package appliance51.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ImportResource(locations = {"classpath*:spring/*.xml"})
@EnableTransactionManagement
//@EnableAspectJAutoProxy
public class Appliance51RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Appliance51RestApplication.class, args);
    }


}
