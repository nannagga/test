package com.java.www;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//getter setter 만들어줌
//@Getter
//@Setter
@NoArgsConstructor //기본생성자
@AllArgsConstructor //전체 생성자 / null값을 알아서 넣어준다. 부분생성자 필요 없음.
@Data //getter setter를 한번에 만들어준다.
@Component //어노테이션 해주면 IOC컨테이너에 자동으로 넣어줌.
public class MyCal {
	private int firstNum;
	private int secondNum;
	
	public void add_class() {
		System.out.println("클래스 add : "+(10+5));
	}
	
}
