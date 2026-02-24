package com.yardEquality;

public class QuantityMeasurementApp {

	public static boolean compareFeet(double a, double b) {
		QuantityLength q1 = new QuantityLength(a, LengthUnit.FEET);
		QuantityLength q2 = new QuantityLength(b, LengthUnit.FEET);
		return q1.equals(q2);
	}

	public static boolean compareInches(double a, double b) {
		return new QuantityLength(a, LengthUnit.INCH).equals(new QuantityLength(b, LengthUnit.INCH));
	}

	public static boolean compareFeetAndInches(double feet, double inches) {
		return new QuantityLength(feet, LengthUnit.FEET).equals(new QuantityLength(inches, LengthUnit.INCH));
	}

	public static boolean compareFeetAndYard(double feet, double yard) {
		return new QuantityLength(feet, LengthUnit.FEET).equals(new QuantityLength(yard, LengthUnit.YARD));
	}

	public static boolean compareInchAndCentimeter(double inch, double cm) {
		return new QuantityLength(inch, LengthUnit.INCH).equals(new QuantityLength(cm, LengthUnit.CENTIMETER));
	}

	public static void main(String[] args) {
		System.out.println("Feet equal: " + compareFeet(1.0, 1.0));
		System.out.println("Inches equal: " + compareInches(12.0, 12.0));
		System.out.println("Feet & Inches equal: " + compareFeetAndInches(1.0, 12.0));
		System.out.println("Feet & Yard equal: " + compareFeetAndYard(3.0, 1.0));
		System.out.println("Inch & CM equal: " + compareInchAndCentimeter(12.0, 30.48));
	}
}