package com.capita.core;

public enum Currency {
    USD(1), INR(66), GBP(1.76f), SGP(1.34f);

    public float getConversionRate() {
        return conversionRate;
    }

    float conversionRate;

    Currency(float rate) {
        this.conversionRate = rate;
    }
}