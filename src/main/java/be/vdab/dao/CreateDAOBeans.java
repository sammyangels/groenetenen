package be.vdab.dao;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;


@Configuration
@ComponentScan
public class CreateDAOBeans {
    @Autowired
    private DataSource dataSource;

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(Filiaal.class.getPackage().getName(), Adres.class.getPackage().getName());
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        factory.setJpaVendorAdapter(adapter);
        factory.getJpaPropertyMap().put("hibernate.format_sql", true);
        factory.getJpaPropertyMap().put("hibernate.use_sql_comments", true);
        return factory;
    }

    @Bean
    JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactoryBean().getObject());
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
