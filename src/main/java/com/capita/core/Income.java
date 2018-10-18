package com.capita.core;

public class Income {
    private String country;
    private String city;
    private String gender;
    private Currency currency;
    private Double avgIncome;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getAvgIncome() {
        return avgIncome;
    }

    public Income(String country, String city, String gender, Currency currency, Double avgIncome) {
        this.country = country;
        this.city = city;

        this.gender = gender;
        this.currency = currency;
        this.avgIncome = avgIncome;
    }

    @Override
    public String toString() {
        return "Income{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", currency=" + currency +
                ", avgIncome=" + avgIncome +
                '}';
    }
}