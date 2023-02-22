package sp0214;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BMICalculator {
	
//	@Value("18.5") -- 주로 form으로 입력받는다. value는 거의 사용하지 않음.
	@Value("${lowWeight}")
	private double lowWeight;
	@Value("${normal}")
	private double normal;
	@Value("${overWeight}")
	private double overWeight;
	@Value("${obesity}")
	private double obesity;
	
	public void bmicalculation(double weight, double height) {
		double h = height * 0.01;
		double result = weight / (h*h);
		
		System.out.printf("당신의 BMI 지수 : %.2f\n",result);
		
		if(result > obesity) System.out.println("[당신은 비만 판정입니다.]");
		else if(result > overWeight) System.out.println("[당신은 과체중 입니다.]");
		else if(result > normal) System.out.println("[당신은 정상입니다.]");
		else System.out.println("[당신은 저체중 판정입니다.]");
	}
}
