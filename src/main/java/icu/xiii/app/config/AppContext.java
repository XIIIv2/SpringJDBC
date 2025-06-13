package icu.xiii.app.config;

import icu.xiii.app.dao.customer.CustomerDao;
import icu.xiii.app.dao.customer.CustomerDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("icu.xiii.app")
@PropertySource("classpath:app.properties")
public class AppContext {

    @Autowired
    Environment env;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dMDS = new DriverManagerDataSource();
        dMDS.setUrl(env.getProperty("dbUrl"));
        dMDS.setUsername(env.getProperty("dbUser"));
        dMDS.setPassword(env.getProperty("dbPass"));
        dMDS.setDriverClassName(Objects.requireNonNull(env.getProperty("jdbcDriver")));
        return dMDS;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /*@Bean
    public CustomerDao customerDao(DataSource dataSource) {
        return new CustomerDaoImpl(dataSource);
    }*/
}
