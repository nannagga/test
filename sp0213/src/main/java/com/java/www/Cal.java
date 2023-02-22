package com.java.www;

public class Cal {
	private int firstNum;
	private int secondNum;
	
	public int getFirstNum() {
		return firstNum;
	}
	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}
	public int getSecondNum() {
		return secondNum;
	}
	public void setSecondNum(int secondNum) {
		this.secondNum = secondNum;
	}
	
	public void add() {
		System.out.println("두수의 합 : "+(firstNum+secondNum));
	}
	public void sub() {
		System.out.println("두수의 차 : "+(firstNum-secondNum));
	}
	
	
	
}
