package com.ensoa.order.root;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
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
    
    @Bean
    public HttpInvokerProxyFactoryBean productService() {
    	HttpInvokerProxyFactoryBean factory = new HttpInvokerProxyFactoryBean();
    	factory.setServiceUrl("http://localhost:8080/httpOrder/product.service");
    	factory.setServiceInterface(ProductService.class);
    	return factory;
    }
}
