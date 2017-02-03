package com.ensoa.order.controller.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

@Configuration
@EnableWebMvc
public class ServletConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private FlowDefinitionRegistry flowRegistry; 
    @Autowired
    private FlowExecutor flowExecutor;

    @Bean
    public FlowHandlerMapping flowHandlerMapping() {
        FlowHandlerMapping flowHandlerMapping = new FlowHandlerMapping();
        flowHandlerMapping.setFlowRegistry(flowRegistry);
        return flowHandlerMapping;
    }
    @Bean
    public FlowHandlerAdapter flowHandlerAdapter() {
        FlowHandlerAdapter flowHandlerAdapter = new FlowHandlerAdapter();
        flowHandlerAdapter.setFlowExecutor(flowExecutor);
        return flowHandlerAdapter;
    }
    @Bean
    public MvcViewFactoryCreator viewFactoryCreator() {
    	MvcViewFactoryCreator viewFactoryCreator = new MvcViewFactoryCreator();
    	List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
    	viewResolvers.add(beanNameViewResolver());
    	viewResolvers.add(internalResourceViewResolver());
    	viewFactoryCreator.setViewResolvers(viewResolvers);
    	return viewFactoryCreator;
    }
	@Bean
	public ViewResolver beanNameViewResolver() {
		BeanNameViewResolver viewResolver = new BeanNameViewResolver();
		viewResolver.setOrder(1);
		return viewResolver;
	}
    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(2);
        viewResolver.setPrefix("/WEB-INF/views/orderflow/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Bean
	public View home() {
		JstlView view = new JstlView();
		view.setUrl("/WEB-INF/views/home.jsp");
		return view;
	}

}
