package com.google.android.gms.wallet;

import com.google.android.gms.tasks.Task;

public class PaymentsClient {
    private android.content.Context context;
    private Wallet.WalletOptions options;

    public PaymentsClient(android.content.Context context, Wallet.WalletOptions options) {
        this.context = context;
        this.options = options;
    }

    public Task<PaymentData> loadPaymentData(PaymentDataRequest request) {
        return null;
    }

    public Task<Boolean> isReadyToPay(IsReadyToPayRequest request) {
        return null;
    }

    public Task<Void> savePaymentData(PaymentData paymentData) {
        return null;
    }
}