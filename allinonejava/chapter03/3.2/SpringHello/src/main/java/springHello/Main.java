package springHello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		Hello hello = (Hello)ctx.getBean("hello");
		String message = hello.sayHello("전병선");
		System.out.println(message);
	}

}
