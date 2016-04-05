package cinema.client.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("cinema.client.data")
@EnableTransactionManagement
@ComponentScan("cinema.client")
@PropertySource("classpath:db.properties")
public class DatabaseConfig {

    @Resource
    Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb =
                new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource());
        emfb.setPackagesToScan(environment.getRequiredProperty("db.entity.package"));
        emfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emfb.setJpaProperties(getHibernateProperties());
        return emfb;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(environment.getRequiredProperty("db.url"));
        ds.setDriverClassName(environment.getRequiredProperty("db.driver"));
        ds.setUsername(environment.getRequiredProperty("db.username"));
        ds.setPassword(environment.getRequiredProperty("db.password"));

        ds.setInitialSize(Integer.valueOf(environment.getRequiredProperty("db.initialSize")));
        ds.setMinIdle(Integer.valueOf(environment.getRequiredProperty("db.minIdle")));
        ds.setMaxIdle(Integer.valueOf(environment.getRequiredProperty("db.maxIdle")));
        ds.setTimeBetweenEvictionRunsMillis(Long.valueOf(environment.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        ds.setMinEvictableIdleTimeMillis(Integer.valueOf(environment.getRequiredProperty("db.minEvictableIdleTimeMillis")));
        ds.setTestOnBorrow(Boolean.valueOf(environment.getRequiredProperty("db.testOnBorrow")));
        ds.setValidationQuery(environment.getRequiredProperty("db.validationQuery"));

        return ds;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        try (InputStream stream = getClass()
                .getClassLoader()
                .getResourceAsStream("hibernate.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find 'hibernate.properties' in classpath!");
        }
    return properties;

    }
}
