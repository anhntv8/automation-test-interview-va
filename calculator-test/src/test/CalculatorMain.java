package test;

import java.util.InputMismatchException;
import java.util.Scanner;

import core.Calculator;

public class CalculatorMain {

	public static void main(String[] args) {
		Calculator cal = new Calculator(3, 5);
		System.out.println("1. Calculate numbers get from the attribute fields.");
		System.out.println("Sum = " + cal.sum());
		System.out.println("Diff = " + cal.diff());
		
		System.out.println("2. Calculate numbers get from outsite");
		
		boolean done = false;
		do {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter number a = ");
				int a = sc.nextInt();
				System.out.print("Enter number b = ");
				int b = sc.nextInt();
				sc.close();
				System.out.println("Sum = " + cal.sum(a, b));
				System.out.println("Diff = " + cal.diff(a, b));
				// get user input
				done = true;
			} catch (InputMismatchException e) {
				System.out.println("The number entered needs to be a int");
			}
		} while (!done);
	}
}
