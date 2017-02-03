package jms.jmsSend;


import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.ensoa.order.domain.Customer;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
    public static void main( String[] args ) throws Exception
    {
    	/*
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        */

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);       
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,  Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("customer.queue");
        //Destination destination = session.createTopic("customer.topic");
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("전병선");
        customer.setAddress("서울시");
        customer.setEmail("bsjun@ensoa.co.kr");
        ObjectMessage message = session.createObjectMessage(customer);
        producer.send(message);
        session.close();
        connection.close();

        /*
        QueueConnection connection = factory.createQueueConnection();
        connection.start();
        QueueSession session = connection.createQueueSession(false,  Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("ensoa.customer");
        QueueSender sender = session.createSender(queue);
        sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("전병선");
        customer.setAddress("서울시");
        customer.setEmail("bsjun@ensoa.co.kr");
        ObjectMessage message = session.createObjectMessage(customer);
        sender.send(message);
        session.close();
        connection.close();


        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);       
        TopicConnection connection = factory.createTopicConnection();
        connection.start();
        TopicSession session = connection.createTopicSession(false,  Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("order.customer.topic");
        TopicPublisher publisher = session.createPublisher(topic);
        publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("전병선");
        customer.setAddress("서울시");
        customer.setEmail("bsjun@ensoa.co.kr");
        ObjectMessage message = session.createObjectMessage(customer);
        publisher.send(message);
        session.close();
        connection.close();
        */
    }
}
