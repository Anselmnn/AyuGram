package com.tech.ayugram.pay.stub;

import android.content.Intent;

/**
 * Phase 2: Stub for com.google.android.gms.wallet.PaymentData
 * Google Pay dependency removed in Phase 2
 */
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