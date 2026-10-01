package com.google.android.gms.wallet;

public class IsReadyToPayRequest {
    private String allowedPaymentMethods;
    private String allowedCardNetworks;
    private String existingPaymentMethodRequired;

    public static class Builder {
        private IsReadyToPayRequest request;

        public Builder() {
            request = new IsReadyToPayRequest();
        }

        public Builder setAllowedPaymentMethods(String[] methods) {
            request.allowedPaymentMethods = android.text.TextUtils.join(",", methods);
            return this;
        }

        public Builder setAllowedCardNetworks(String[] networks) {
            request.allowedCardNetworks = android.text.TextUtils.join(",", networks);
            return this;
        }

        public Builder setExistingPaymentMethodRequired(boolean required) {
            request.existingPaymentMethodRequired = Boolean.toString(required);
            return this;
        }

        public IsReadyToPayRequest build() {
            return request;
        }
    }

    public String getAllowedPaymentMethods() {
        return allowedPaymentMethods;
    }

    public String getAllowedCardNetworks() {
        return allowedCardNetworks;
    }

    public String getExistingPaymentMethodRequired() {
        return existingPaymentMethodRequired;
    }
}