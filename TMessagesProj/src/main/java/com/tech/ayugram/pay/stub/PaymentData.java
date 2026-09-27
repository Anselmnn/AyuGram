package com.tech.ayugram.pay.stub;

import android.content.Intent;

public class PaymentData {
    public static PaymentData getFromIntent(Intent intent) {
        return new PaymentData();
    }

    public String toJson() {
        return "{}";
    }

    public String getEmail() { return null; }
    public String getPaymentMethodToken() { return null; }
    public String getShippingAddress() { return null; }
    public String getBillingAddress() { return null; }
}