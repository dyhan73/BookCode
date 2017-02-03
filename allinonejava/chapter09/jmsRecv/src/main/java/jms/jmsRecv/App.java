package jms.jmsRecv;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.ensoa.order.domain.Customer;

/**
 * Hello world!
 *
 */
public class App implements ExceptionListener
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
        connection.setExceptionListener(new App());
        Session session = connection.createSession(false,  Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("customer.queue");
        // Destination destination = session.createTopic("customer.topic");
        MessageConsumer consumer = session.createConsumer(destination);
        /*
        Message message = consumer.receive(1000);
        if(message instanceof ObjectMessage) {
        	ObjectMessage msg = (ObjectMessage)message;
        	try {
        		Customer customer = (Customer)msg.getObject();
        		System.out.println(customer);
        	} catch (JMSException e) {
        		e.printStackTrace();
        	}
        }
        */
        consumer.setMessageListener(new AsyncListener());
        System.out.println("메시지를 기다립니다.");
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
   
        /*
        QueueConnection connection = factory.createQueueConnection();
        connection.setExceptionListener(new App());
        connection.start();
        QueueSession session = connection.createQueueSession(false,  Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("ensoa.customer");
        QueueReceiver receiver = session.createReceiver(queue);
        // 동기식
       
        Message message = receiver.receive(1000);
        if(message instanceof ObjectMessage) {
        	ObjectMessage msg = (ObjectMessage)message;
        	try {
        		Customer customer = (Customer)msg.getObject();
        		System.out.println(customer);
        	} catch (JMSException e) {
        		e.printStackTrace();
        	}
        }
		 // 비동기식 
        receiver.setMessageListener(new AsyncListener());
        System.out.println("메시지를 기다립니다.");
        System.in.read();
  
        receiver.close();
        session.close();
        connection.close();
  
    	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
       TopicConnection connection = factory.createTopicConnection();
        connection.start();
        connection.setExceptionListener(new App());
        TopicSession session = connection.createTopicSession(false,  Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("order.customer");
        TopicSubscriber subscriber = session.createSubscriber(topic);
        subscriber.setMessageListener(new AsyncListener());
        System.out.println("메시지를 기다립니다.");
        System.in.read();
        subscriber.close();
        session.close();
        connection.close();
             */
    }

	public void onException(JMSException exception) {
		System.out.println(exception);
	}
}
