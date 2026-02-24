package com.yardEqualityTest;

import com.yardEquality.LengthUnit;
import com.yardEquality.QuantityLength;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

	@Test
	void testEquality_FeetToFeet_SameValue() {
		assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.FEET));
	}

	@Test
	void testEquality_InchToInch_SameValue() {
		assertEquals(new QuantityLength(12.0, LengthUnit.INCH), new QuantityLength(12.0, LengthUnit.INCH));
	}

	@Test
	void testEquality_FeetToInch_EquivalentValue() {
		assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCH));
	}

	@Test
	void testEquality_InchToFeet_EquivalentValue() {
		assertEquals(new QuantityLength(12.0, LengthUnit.INCH), new QuantityLength(1.0, LengthUnit.FEET));
	}

	@Test
	void testInequality_DifferentFeetValues() {
		assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(2.0, LengthUnit.FEET));
	}

	@Test
	void testInequality_DifferentInchValues() {
		assertNotEquals(new QuantityLength(1.0, LengthUnit.INCH), new QuantityLength(2.0, LengthUnit.INCH));
	}

	@Test
	void testNullComparison() {
		assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), null);
	}

	@Test
	void testSameReference() {
		QuantityLength quantity = new QuantityLength(1.0, LengthUnit.FEET);
		assertEquals(quantity, quantity);
	}

	@Test
	void testNullUnitThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new QuantityLength(1.0, null));
	}

	@Test
	void testEquality_FeetToYard() {
		assertEquals(new QuantityLength(3.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.YARD));
	}

	@Test
	void testEquality_InchToCentimeter() {
		assertEquals(new QuantityLength(12.0, LengthUnit.INCH), new QuantityLength(30.48, LengthUnit.CENTIMETER));
	}

	@Test
	void testEquality_YardToInch() {
		assertEquals(new QuantityLength(1.0, LengthUnit.YARD), new QuantityLength(36.0, LengthUnit.INCH));
	}
}