package main;

import java.rmi.Naming;

import frames.CalculatorFrame;
import utils.Interface;

public class Client {
	static CalculatorFrame cf;
	static Interface obj = null;
	static boolean connected = connect();
	int total = 0;

	public int calculate(String type, int num1, int num2) {
		try{
			switch (type){
			case ("add"):
				total = obj.add(num1, num2);
			break;
			case("subtract"):
				total = obj.subtract(num1, num2);
			break;
			case("divide"):
				total = obj.divide(num1, num2);
			break;
			case("multiply"):
				total = obj.multiply(num1, num2);
			break;
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return total;
	}

	public static boolean connect() {
		try{
			obj = (Interface) Naming.lookup("//localhost/Calculator");
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}	
}