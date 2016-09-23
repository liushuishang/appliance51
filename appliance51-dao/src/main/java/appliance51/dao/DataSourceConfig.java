package appliance51.dao;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by yuananyun on 2016/9/21.
 */
@Configuration
@EntityScan(basePackages = {"appliance51.dao.domain"})
@EnableJpaRepositories(basePackages = {"appliance51.dao.repositories"})
public class DataSourceConfig {

}
