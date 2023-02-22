package sp0214;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration //IOC컨테이너에 넣을거라는 표시
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyInfo implements My{
	@Value("${my.name}")
	private String name;
	@Value("${height}")
	private double height;
	@Value("${weighet}")
	private double weight;
	@Value("#{${hobbys}}")
	private ArrayList<String> hobbys;
	
	@Autowired //IOC 컨테이너에서 객체를 가져와서 (set)주입해달라는 표시
	private BMICalculator bmiCalculator;
	
	public void getInfo() {
		System.out.println("[개인 신상정보]");
		System.out.println("이름 : "+name);
		System.out.println("키 : "+height);
		System.out.println("몸무게 : "+ weight);
		System.out.println("취미 : "+hobbys);
		bmiCalculator.bmicalculation(weight, height);
	}
}
