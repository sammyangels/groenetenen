package be.vdab.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
public class CreateDataSourceBean {
    @Bean
    javax.sql.DataSource dataSource() {
        return new JndiDataSourceLookup().getDataSource("jdbc/groenetenen");
    }
}
