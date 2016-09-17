package appliance51.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@ImportResource(locations = {"classpath*:spring/*.xml"})
@EntityScan(basePackages = {"appliance51.dao.domain"})
@EnableJpaRepositories(basePackages = {"appliance51.dao.repositories"})
public class Appliance51RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Appliance51RestApplication.class, args);
    }

//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
//                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
//                container.addErrorPages(new ErrorPage(java.lang.Throwable.class, "/error/500"));
//            }
//        };
//    }
}
