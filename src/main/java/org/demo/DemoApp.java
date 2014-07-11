package org.demo;


import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class DemoApp
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DemoConfiguration.class);

        PersonRepository repo = ctx.getBean(PersonRepository.class);
        repo.save(new Person("Ram","Bangalore",25));
        repo.save(new Person("Rahim","Chennai",35));
        repo.save(new Person("Robert","Pune",45));
        
        Iterable<Person> ps = repo.findAll();
        for (Person p :ps)
        {
            System.out.println(p);
        }
        System.out.println("Running a custom method..");
        System.out.println(repo.findByName("Ram"));
    }
}

@Configuration
@EnableJpaRepositories
class DemoConfiguration
{
    @Bean 
    public DriverManagerDataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:testdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    @Bean 
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("org.demo");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();

        jpaProperties.setProperty("hibernate.hbm2ddl.auto","create-drop");
        jpaProperties.setProperty("hibernate.dialect","org.hibernate.dialect.HSQLDialect");
        jpaProperties.setProperty("hibernate.show_sql","true");

        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }
    
    @Bean
    public JpaTransactionManager transactionManager()
    {
        return new JpaTransactionManager();
    }
}