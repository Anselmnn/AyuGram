package com.stripe.android.model;

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

    public static boolean validateNumber(String number) {
        if (number == null) {
            return false;
        }
        // Luhn algorithm
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            char c = number.charAt(i);
            if (!Character.isDigit(c)) {
                continue;
            }
            int n = Integer.parseInt(String.valueOf(c));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10) == 0;
    }

    public static boolean validateExpiryDate(int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }
        // Simple check - year should be current or future
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int currentYear = cal.get(java.util.Calendar.YEAR) % 100;
        int currentMonth = cal.get(java.util.Calendar.MONTH) + 1;
        if (year < currentYear || (year == currentYear && month < currentMonth)) {
            return false;
        }
        return true;
    }

    public static boolean validateCVC(String cvc) {
        return cvc != null && cvc.length() >= 3 && cvc.length() <= 4 && cvc.matches("\\d+");
    }

    public String getNumber() {
        return number;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public String getCvc() {
        return cvc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}