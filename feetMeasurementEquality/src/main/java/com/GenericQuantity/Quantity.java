package com.GenericQuantity;

public abstract class Quantity<T extends Quantity<T>> {

    protected final double value;

    protected Quantity(double value) {
        if (Double.isNaN(value)) {
            throw new IllegalArgumentException("Invalid measurement value");
        }
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;
        return Double.compare(this.value, other.value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}

