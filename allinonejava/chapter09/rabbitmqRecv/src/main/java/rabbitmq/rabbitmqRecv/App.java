package rabbitmq.rabbitmqRecv;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.lang3.SerializationUtils;

import com.ensoa.order.domain.Customer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // publish
        channel.exchangeDeclare("customer", "topic");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "customer", "#.customer.*");
        
//       channel.queueDeclare("customer",  false,  false,  false,  null);
        System.out.println("Waiting.....");
        QueueingConsumer consumer = new QueueingConsumer(channel);
//        channel.basicConsume("customer",  true, consumer);
        channel.basicConsume(queueName, true, consumer);
        while(true) {
        	Delivery delivery = consumer.nextDelivery();
//        	Customer customer = fromBytes(delivery.getBody());
        	Customer customer = (Customer)SerializationUtils.deserialize(delivery.getBody());
        	String routingKey = delivery.getEnvelope().getRoutingKey();
        	System.out.println("Received : " + routingKey + " : " +  customer);
 //       	String message = new String(delivery.getBody());
 //       	System.out.println("Received : " + message);
        }
    }
    

    public static Customer fromBytes(byte[] body) {
    	Customer obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(body);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = (Customer) ois.readObject();
            ois.close();
            bis.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
}
