package springcontainer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class App {

	public static void main(String[] args) {
		//user객체를 만들어낸 객체는 APP이다
		//User 객체의 주체는 App이다
//		User u = new User(1l, "홍길동");
//		System.out.println(u);
//		testBeanFactory();
		testApplicationContext();
	}
	
	public static void testApplicationContext() {
		//ApplicationContext 이용
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		
		//클래스로 만들어진 Bean이 많을 경우에는 
		//type(class)로 getbean을 할수 없다
//		User user1 = ac.getBean(User.class); 
		User user2 = (User)ac.getBean("user2");
		System.out.println("user2:" + user2);
		User user3 = (User)ac.getBean("user3");
		System.out.println("user3:" + user3);
		User user4 = (User)ac.getBean("user4");
		System.out.println("user4:" + user4);
		
		//외부 객체가 주입된 Bean의 확인'
		User user5 = (User)ac.getBean("user5");
		System.out.println("user5:" + user5);
		
		//컬렉션 주입한 bean의 확인
		User user6 = (User)ac.getBean("user6");
		System.out.println("user6:" + user6);
		
	}
	public static void testBeanFactory() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("config/applicationContext.xml"));
		
		//factory로부터 bean을 얻어올 수 있다
		User user = bf.getBean(User.class); //클래스(type)명으로 받아오기
		System.out.println("User:" + user);
		
		//id로 객체를 얻어 올수 있다
		User userById = (User)bf.getBean("user");
		System.out.println("userById:" + userById);
		
		//name으로도 객체를 얻어올 수 있다
		User userByName = (User)bf.getBean("member");
		System.out.println("userByName" + userByName);
		
		//만약에 존자하지 않는 id혹은 name으로 객체를 받아올 경우
		//->nosearchbeandefinitionexception발생: 주의
//		User userUnknown = (User)bf.getBean("unknown");
//		System.out.println("userUnknown:" + userUnknown);
		
		//기본적으로 Spring Container는 단일 객체를 유지한다
		System.out.println("user가 userById와 동일객체인가?" + (user == userById));
		System.out.println("userById는 userByName과 동일 객체인가?" + (userById == userByName));
	}
}
