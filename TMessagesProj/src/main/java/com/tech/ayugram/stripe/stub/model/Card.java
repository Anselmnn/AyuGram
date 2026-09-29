package com.tech.ayugram.stripe.stub.model;

public class Card {
    private String number;
    private int expMonth;
    private int expYear;
    private String cvc;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String addressCity;
    private String addressState;
    private String addressZip;
    private String addressCountry;
    private String currency;

    public Card() {}

    public Card(String number, Integer expMonth, Integer expYear, String cvc, String name,
                String addressLine1, String addressLine2, String addressCity, String addressState,
                String addressZip, String addressCountry, String currency) {
        this.number = number;
        this.expMonth = expMonth != null ? expMonth : 0;
        this.expYear = expYear != null ? expYear : 0;
        this.cvc = cvc;
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZip = addressZip;
        this.addressCountry = addressCountry;
        this.currency = currency;
    }

    public Card setNumber(String number) { this.number = number; return this; }
    public Card setExpMonth(int expMonth) { this.expMonth = expMonth; return this; }
    public Card setExpYear(int expYear) { this.expYear = expYear; return this; }
    public Card setCvc(String cvc) { this.cvc = cvc; return this; }
    public Card setName(String name) { this.name = name; return this; }
    public Card setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; return this; }
    public Card setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; return this; }
    public Card setAddressCity(String addressCity) { this.addressCity = addressCity; return this; }
    public Card setAddressState(String addressState) { this.addressState = addressState; return this; }
    public Card setAddressZip(String addressZip) { this.addressZip = addressZip; return this; }
    public Card setAddressCountry(String addressCountry) { this.addressCountry = addressCountry; return this; }
    public Card setCurrency(String currency) { this.currency = currency; return this; }

    public String getNumber() { return number; }
    public int getExpMonth() { return expMonth; }
    public int getExpYear() { return expYear; }
    public String getCvc() { return cvc; }
    public String getName() { return name; }
    public String getAddressLine1() { return addressLine1; }
    public String getAddressLine2() { return addressLine2; }
    public String getAddressCity() { return addressCity; }
    public String getAddressState() { return addressState; }
    public String getAddressZip() { return addressZip; }
    public String getAddressCountry() { return addressCountry; }
    public String getCurrency() { return currency; }

    public String getBrand() { return "Visa"; }
    public String getLast4() { return "4242"; }

    public boolean validateNumber() { return true; }
}