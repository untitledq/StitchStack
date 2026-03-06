package com.domain.model;

import com.domain.enums.MeasurementType;

public class Measurement {

    private MeasurementType type;
    private double valueMm;

    public Measurement(MeasurementType type, double valueMm) {

        if (valueMm <= 0) {
            throw new IllegalArgumentException("Measurement must be positive");
        }

        this.type = type;
        this.valueMm = valueMm;
    }

    public MeasurementType getType() {
        return type;
    }

    public double getValueMm() {
        return valueMm;
    }
}