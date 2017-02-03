package com.ensoa.orderamqp;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.ensoa")
public class AppConfig  {
    @Bean
    public CachingConnectionFactory rabbitConnectionFactory() {
    	CachingConnectionFactory rabbitConnectionFactory = new CachingConnectionFactory("localhost");
    	rabbitConnectionFactory.setUsername("guest");
    	rabbitConnectionFactory.setPassword("guest");
    	return rabbitConnectionFactory;
    }
    @Bean
    public Queue customerQueue() {
    	Queue queue = new Queue("customer.queue");
    	return queue;
    }
    @Bean
    public Queue orderQueue() {
    	Queue queue = new Queue("order.queue");
    	return queue;
    }
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rabbitConnectionFactory());
        container.setQueues(customerQueue(), orderQueue());
        MessageListenerAdapter adapter = new MessageListenerAdapter(new CustomerListener());
        container.setMessageListener(adapter);
        return container;
    }
}
