package sp0214;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Configuration
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyInfo2 implements My{
	@Value("${my.name}")
	private String name;
	@Value("${height}")
	private double height;
	@Value("${weighet}")
	private double weight;
	@Value("${phone}")
	private String phone;
	@Value("${gender}")
	private String gender;
	@Value("${test}")
	private String test;
	@Value("#{${hobbys}}")
	private ArrayList<String> hobbys;
	
	
	@Autowired
	private BMICalculator bmiCalculator;
	
	public void getInfo() {
		System.out.println("[개인 신상정보]");
		System.out.println("이름 : "+name);
		System.out.println("키 : "+height);
		System.out.println("몸무게 : "+weight);
		System.out.println("phone : "+phone);
		System.out.println("gender : "+gender);
		System.out.println("test : "+test);
		System.out.println("취미 : "+hobbys);
		bmiCalculator.bmicalculation(weight, height);
	}
}
