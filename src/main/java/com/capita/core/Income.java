package com.capita.core;


public class Income {
    private String country;
    private String city;
    private String gender;
    private Currency currency;
    private Double avgIncome;

    //TODO: can be replaced with builder or constructor chaining-- too long to handle
//    public Income(String country, String city, String gender, Currency currency, Double avgIncome) {
//        this.country = country;
//        this.city = city;
//        this.gender = gender;
//        this.currency = currency;
//        this.avgIncome = avgIncome;
//    }

    public Income(IncomeBuilder builder) {
        this.country = builder.country;
        this.city = builder.city;
        this.gender = builder.gender;
        this.currency = builder.currency;
        this.avgIncome = builder.avgIncome;
    }

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

    public double getAvgUSDIncome() {
        return currency.getUSD(avgIncome);
    }

    public static class IncomeBuilder {

        private String country;
        private String city;
        private String gender;
        private Currency currency;
        private Double avgIncome;

        public IncomeBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public IncomeBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public IncomeBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public IncomeBuilder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public IncomeBuilder setAvgIncome(Double avgIncome) {
            this.avgIncome = avgIncome;
            return this;
        }

        public Income build() {
            return new Income(this);
        }
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