package com.java.www;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyCal {
   
   Calculator calculator;
   private int firstNum;
   private int secondNum;

   public void add() {
	   calculator.addition(firstNum, secondNum);
   }

   public void sub() {
	   calculator.subtraction(firstNum, secondNum);
   }
}
