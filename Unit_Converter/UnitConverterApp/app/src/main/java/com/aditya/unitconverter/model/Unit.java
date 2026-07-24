package com.aditya.unitconverter.model;

public class Unit {
    private final String name;
    private final String symbol;
    private final double conversionFactorToBase;

    public Unit(String name, String symbol, double conversionFactorToBase) {
        this.name = name;
        this.symbol = symbol;
        this.conversionFactorToBase = conversionFactorToBase;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getConversionFactorToBase() {
        return conversionFactorToBase;
    }
}
