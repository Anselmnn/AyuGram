package com.google.android.gms.wallet;

public class Wallet {
    private static Wallet instance;

    private Wallet() {
    }

    public static Wallet getInstance(android.content.Context context) {
        if (instance == null) {
            instance = new Wallet();
        }
        return instance;
    }

    public PaymentsClient getPaymentsClient(android.content.Context context, Wallet.WalletOptions options) {
        return new PaymentsClient(context, options);
    }

    public static class WalletOptions {
        private android.os.Bundle bundle;
        private int environment;
        private int theme;

        public WalletOptions() {
            this.bundle = new android.os.Bundle();
            this.environment = WalletConstants.ENVIRONMENT_TEST;
            this.theme = WalletConstants.THEME_LIGHT;
        }

        public WalletOptions setEnvironment(int environment) {
            this.environment = environment;
            return this;
        }

        public WalletOptions setTheme(int theme) {
            this.theme = theme;
            return this;
        }

        public int getEnvironment() {
            return environment;
        }

        public int getTheme() {
            return theme;
        }
    }
}