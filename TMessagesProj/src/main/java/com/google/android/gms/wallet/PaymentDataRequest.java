package com.google.android.gms.wallet;

public class PaymentDataRequest {
    private long totalPrice;
    private String totalPriceStatus;
    private String currencyCode;
    private String countryCode;
    private String emailRequired;
    private String shippingAddressRequired;
    private String phoneNumberRequired;
    private String uiRequired;
    private String allowedCardNetworks;
    private String allowedPaymentMethods;
    private String cardRequirements;
    private String shippingAddressParameters;
    private String transactionInfo;
    private String merchantInfo;

    public static class Builder {
        private PaymentDataRequest request;

        public Builder() {
            request = new PaymentDataRequest();
        }

        public Builder setTotalPrice(long totalPrice) {
            request.totalPrice = totalPrice;
            return this;
        }

        public Builder setTotalPriceStatus(String totalPriceStatus) {
            request.totalPriceStatus = totalPriceStatus;
            return this;
        }

        public Builder setCurrencyCode(String currencyCode) {
            request.currencyCode = currencyCode;
            return this;
        }

        public Builder setCountryCode(String countryCode) {
            request.countryCode = countryCode;
            return this;
        }

        public Builder setEmailRequired(boolean required) {
            request.emailRequired = Boolean.toString(required);
            return this;
        }

        public Builder setShippingAddressRequired(boolean required) {
            request.shippingAddressRequired = Boolean.toString(required);
            return this;
        }

        public Builder setPhoneNumberRequired(boolean required) {
            request.phoneNumberRequired = Boolean.toString(required);
            return this;
        }

        public Builder setUiRequired(boolean required) {
            request.uiRequired = Boolean.toString(required);
            return this;
        }

        public Builder setAllowedCardNetworks(String[] networks) {
            request.allowedCardNetworks = android.text.TextUtils.join(",", networks);
            return this;
        }

        public Builder setAllowedPaymentMethods(String[] methods) {
            request.allowedPaymentMethods = android.text.TextUtils.join(",", methods);
            return this;
        }

        public PaymentDataRequest build() {
            return request;
        }
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public String getTotalPriceStatus() {
        return totalPriceStatus;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}