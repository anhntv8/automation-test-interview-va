package core;

public class Calculator {
	private int a;
	private int b;
	
	public Calculator(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public int sum() {
		return a + b;
	}
	
	public int sum(int a, int b) {
		return a + b;
	}
}
