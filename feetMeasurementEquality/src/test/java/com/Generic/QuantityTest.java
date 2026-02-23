package com.Generic;

import com.GenericQuantity.Feet;
import com.GenericQuantity.Inches;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    @Test
    void testFeetEquality_SameValue() {
        Feet f1 = new Feet(5.0);
        Feet f2 = new Feet(5.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    void testFeetEquality_DifferentValue() {
        Feet f1 = new Feet(5.0);
        Feet f2 = new Feet(6.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    void testFeetEquality_SameReference() {
        Feet f1 = new Feet(5.0);

        assertTrue(f1.equals(f1));
    }

    @Test
    void testFeetEquality_NullComparison() {
        Feet f1 = new Feet(5.0);

        assertFalse(f1.equals(null));
    }

    @Test
    void testFeetEquality_DifferentType() {
        Feet f1 = new Feet(5.0);
        Inches inches = new Inches(5.0);

        assertFalse(f1.equals(inches));
    }

    @Test
    void testInchesEquality_SameValue() {
        Inches i1 = new Inches(12.0);
        Inches i2 = new Inches(12.0);

        assertTrue(i1.equals(i2));
    }

    @Test
    void testInchesEquality_DifferentValue() {
        Inches i1 = new Inches(12.0);
        Inches i2 = new Inches(10.0);

        assertFalse(i1.equals(i2));
    }

    @Test
    void testInchesEquality_SameReference() {
        Inches i1 = new Inches(12.0);

        assertTrue(i1.equals(i1));
    }

    @Test
    void testInchesEquality_NullComparison() {
        Inches i1 = new Inches(12.0);

        assertFalse(i1.equals(null));
    }

}

