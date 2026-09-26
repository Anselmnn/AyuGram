package com.tech.ayugram.stripe.stub;

/**
 * Phase 2: Stub for com.stripe.android.model.Card
 * Stripe dependency removed in Phase 2
 */
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

    public Card(String number, int expMonth, int expYear, String cvc) {
        this.number = number;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvc = cvc;
    }

    // Getters
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

    // Setters
    public void setNumber(String number) { this.number = number; }
    public void setExpMonth(int expMonth) { this.expMonth = expMonth; }
    public void setExpYear(int expYear) { this.expYear = expYear; }
    public void setCvc(String cvc) { this.cvc = cvc; }
    public void setName(String name) { this.name = name; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }
    public void setAddressCity(String addressCity) { this.addressCity = addressCity; }
    public void setAddressState(String addressState) { this.addressState = addressState; }
    public void setAddressZip(String addressZip) { this.addressZip = addressZip; }
    public void setAddressCountry(String addressCountry) { this.addressCountry = addressCountry; }
    public void setCurrency(String currency) { this.currency = currency; }

    public boolean validateCard() { return false; }
    public boolean validateExpMonth() { return false; }
    public boolean validateExpYear() { return false; }
    public boolean validateCvc() { return false; }
}