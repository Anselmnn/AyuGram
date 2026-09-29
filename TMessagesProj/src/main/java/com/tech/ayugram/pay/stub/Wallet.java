package com.tech.ayugram.pay.stub;

import android.content.Context;

public class Wallet {
    public static final int ENVIRONMENT_TEST = 0;
    public static final int ENVIRONMENT_PRODUCTION = 1;
    public static final int THEME_LIGHT = 1;
    public static final int THEME_DARK = 2;

    public static class WalletOptions {
        public static class Builder {
            private int environment;
            private int theme;

            public Builder setEnvironment(int env) { this.environment = env; return this; }
            public Builder setTheme(int theme) { this.theme = theme; return this; }
            public WalletOptions build() { return new WalletOptions(); }
        }
    }

    public static PaymentsClient getPaymentsClient(Context context, WalletOptions walletOptions) {
        return new PaymentsClient();
    }
}
