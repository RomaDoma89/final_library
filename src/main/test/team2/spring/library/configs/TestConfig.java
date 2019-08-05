package team2.spring.library.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import team2.spring.library.dao.*;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;
import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.dao.interfaces.CopyDaoInfs;
import team2.spring.library.dao.interfaces.StoryDaoInfs;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "team2.spring.library.dao")
@EnableTransactionManagement
@PropertySource(value = "classpath:hibernate.properties")
public class TestConfig {

  @Bean
  public AuthorDaoInfs getAuthorDao() {
    return new AuthorDao();
  }

  @Bean
  public BookDaoInfs getBookDao() {
    return new BookDao();
  }

  @Bean
  public CopyDaoInfs getCopyDao() {
    return new CopyDao();
  }

  @Bean
  public ReaderDao getReaderDao() {
    return new ReaderDao();
  }

  @Bean
  public StoryDaoInfs getStoryDao() {
    return new StoryDao();
  }

  private Environment environment;

  @Autowired
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
    properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
    properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));

    return properties;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    String[] packages = new String[1];
    packages[0] = "team2.spring.library.entities";
    sessionFactory.setPackagesToScan(packages);
    sessionFactory.setHibernateProperties(hibernateProperties());
    return sessionFactory;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
    dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
    dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
    dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
    return dataSource;
  }

  @Bean
  public HibernateTransactionManager transactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }
}
