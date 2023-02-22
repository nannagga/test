package sp0214;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		//[xml 설정방법]
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("sp0214/app_config.xml"); //XML 직접정의?
		//("myInfo",My.class) xml 파일에서 id="myInfo"
//		My myInfo = ctx.getBean("myInfo", My.class);
		
		
		//[어노테이션 설정방법]
		//따로 이름을 설정해주지 않으니 이름이 필요없다. 
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/sp0214/app_config2.xml"); // xml문서에 어노테이션을사용할때
		My myInfo = ctx.getBean(My.class);
		
		myInfo.getInfo();
		
		
	}  
}
