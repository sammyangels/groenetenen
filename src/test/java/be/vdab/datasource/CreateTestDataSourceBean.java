package be.vdab.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class CreateTestDataSourceBean {
    @Bean
    DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:hsqldb:mem:groenetenen", "sa", "");
    }
}
