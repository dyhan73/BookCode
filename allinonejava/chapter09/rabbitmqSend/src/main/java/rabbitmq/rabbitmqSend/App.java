package rabbitmq.rabbitmqSend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.commons.lang3.SerializationUtils;

import com.ensoa.order.domain.Customer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException  {
    
    	Customer customer = new Customer();
    	customer.setId(1);
    	customer.setName("전병선");
    	customer.setAddress("서울시");
    	customer.setEmail("bjsun@ensoa.co.kr");
    	String message = "안녕하세요?";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("customer", "topic");
        channel.basicPublish("customer",  "com.ensoa.customer.update",  null,  SerializationUtils.serialize(customer));
 //       channel.queueDeclare("customer",  false,  false,  false,  null);
 //       channel.basicPublish("",  "customer",  null,  SerializationUtils.serialize(customer));
//        channel.basicPublish("",  "customer",  null, message.getBytes());
        channel.close();
        connection.close();
    }
    
}
