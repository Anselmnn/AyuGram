package com.tech.ayugram.pay.stub;

import android.content.Context;

public class Wallet {
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

    public static class WalletConstants {
        public static final int ENVIRONMENT_TEST = 0;
        public static final int ENVIRONMENT_PRODUCTION = 1;
        public static final int THEME_LIGHT = 1;
        public static final int THEME_DARK = 2;
    }

    private Wallet() {}

    public static com.tech.ayugram.pay.stub.PaymentsClient getPaymentsClient(Context context, com.tech.ayugram.pay.stub.PaymentsClient.WalletOptions walletOptions) {
        return com.tech.ayugram.pay.stub.PaymentsClient.getPaymentsClient(context, walletOptions);
    }
}