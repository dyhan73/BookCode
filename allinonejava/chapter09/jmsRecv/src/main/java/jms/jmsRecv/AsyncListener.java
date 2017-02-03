package jms.jmsRecv;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import com.ensoa.order.domain.Customer;

public class AsyncListener implements MessageListener{
	public void onMessage(Message message) {
        if(message instanceof ObjectMessage) {
        	ObjectMessage msg = (ObjectMessage)message;
        	try {
        		Customer customer = (Customer)msg.getObject();
        		System.out.println(customer);
        	} catch (JMSException e) {
        		e.printStackTrace();
        	}
        }
        if(message instanceof TextMessage) {
        	try {
				String s = ((TextMessage)message).getText();
				System.out.println(s);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
