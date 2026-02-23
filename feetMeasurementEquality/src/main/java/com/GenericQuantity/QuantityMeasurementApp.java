package com.GenericQuantity;

public class QuantityMeasurementApp {
	public static boolean checkFeetEquality(double v1, double v2) {
        return new Feet(v1).equals(new Feet(v2));
    }

    public static boolean checkInchesEquality(double v1, double v2) {
        return new Inches(v1).equals(new Inches(v2));
    }

    public static void main(String[] args) {

        
        boolean feetResult = checkFeetEquality(5.0, 5.0);
        boolean inchResult = checkInchesEquality(12.0, 12.0);

        System.out.println("Feet Equality Result: " + feetResult);
        System.out.println("Inches Equality Result: " + inchResult);
    }
}
