package com.tech.ayugram.pay.stub;

import android.content.Context;

public class PaymentsClient {
    public static class WalletOptions {
        public static class Builder {
            private int environment;
            private int theme;

            public Builder setEnvironment(int environment) {
                this.environment = environment;
                return this;
            }

            public Builder setTheme(int theme) {
                this.theme = theme;
                return this;
            }

            public WalletOptions build() {
                return new WalletOptions();
            }
        }
    }

    private PaymentsClient() {}

    public static PaymentsClient getPaymentsClient(Context context, WalletOptions walletOptions) {
        return new PaymentsClient();
    }

    public com.tech.ayugram.play.stub.Task<Boolean> isReadyToPay(com.tech.ayugram.pay.stub.IsReadyToPayRequest request) {
        return com.tech.ayugram.play.stub.Task.forResult(false);
    }

    public com.tech.ayugram.play.stub.Task<com.tech.ayugram.pay.stub.PaymentData> loadPaymentData(com.tech.ayugram.pay.stub.PaymentDataRequest request) {
        return com.tech.ayugram.play.stub.Task.forResult(new com.tech.ayugram.pay.stub.PaymentData());
    }
}