package com.ensoa.order.root;

import java.io.IOException;
import java.util.Locale;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.ensoa.order.service.CustomerService;
import com.ensoa.order.service.ProductService;

@Configuration
@EnableWebMvc
@PropertySource("classpath:environment.properties")
public class RootConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	// 로케일 변경
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.KOREAN);
		/*
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("lang");
		localeResolver.setCookieMaxAge(100000);
		localeResolver.setCookiePath("web/cookie");
		*/
		return localeResolver;
	}
	// i18n 설정
	@Bean
	public MessageSource messageSource() {
			ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//			ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
			messageSource.setCacheSeconds(5); 
			messageSource.setDefaultEncoding("UTF-8"); 
			messageSource.setBasenames(
					"/WEB-INF/i18n/message", "/WEB-INF/i18n/common", "/WEB-INF/i18n/validation"
//					"message", "common", "validation"
			);
			return messageSource;
	}
	// validation에서 i18n 사용
    @Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

    /*
    @Bean
    public DataSource dataSource() {
    	SingleConnectionDataSource dataSource = new SingleConnectionDataSource ();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/order_system");
    	dataSource.setUsername("root");
    	dataSource.setPassword("1234");
    	return dataSource;
    }
    
    @Bean
    public DataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource ();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/order_system");
    	dataSource.setUsername("root");
    	dataSource.setPassword("1234");
    	return dataSource;
    }
 
    @Bean
    public DataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/order_system");
    	dataSource.setUsername("root");
    	dataSource.setPassword("1234");
    	return dataSource;
    }

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
    @Value("${dataSource.driverClassName}") private String driverClassName;
    @Value("${dataSource.url}") private String url;
    @Value("${dataSource.username}") private String username;
    @Value("${dataSource.password}") private String password;
    @Bean
    public DataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(driverClassName);
    	dataSource.setUrl(url);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	return dataSource;
    } 
    */   
    @Autowired
    private Environment env;
    @Bean
    public DataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(env.getProperty("dataSource.driverClassName"));
    	dataSource.setUrl(env.getProperty("dataSource.url"));
    	dataSource.setUsername(env.getProperty("dataSource.username"));
    	dataSource.setPassword(env.getProperty("dataSource.password"));
    	return dataSource;
    }
    
    @Bean
    public SessionFactory sessionFactory() {
    	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
       	try {
    	 	sessionFactory.setDataSource(dataSource());
    	   	Resource configLocation = new ClassPathResource("hibernate.cfg.xml");
    	  	sessionFactory.setConfigLocation(configLocation); 
    		sessionFactory.afterPropertiesSet();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        return sessionFactory.getObject();
    }
    @Bean
    public HibernateTransactionManager txManager() {
    	HibernateTransactionManager txManager = new HibernateTransactionManager();
    	txManager.setSessionFactory(sessionFactory());
    	return txManager;
    }
}
