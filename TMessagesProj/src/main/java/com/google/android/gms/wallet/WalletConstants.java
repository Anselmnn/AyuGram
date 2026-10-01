package com.google.android.gms.wallet;

public class WalletConstants {
    public static final int ENVIRONMENT_TEST = 0;
    public static final int ENVIRONMENT_PRODUCTION = 1;
    public static final int ENVIRONMENT_SANDBOX = 2;

    public static final int THEME_LIGHT = 0;
    public static final int THEME_DARK = 1;

    public static final int ERROR_CODE_UNKNOWN = -1;
    public static final int ERROR_CODE_DEVELOPER_ERROR = 0;
    public static final int ERROR_CODE_INTERNAL_ERROR = 1;
    public static final int ERROR_CODE_INVALID_PARAMETERS = 2;
    public static final int ERROR_CODE_MERCHANT_ACCOUNT_ERROR = 3;
    public static final int ERROR_CODE_NETWORK_ERROR = 4;
    public static final int ERROR_CODE_PERMISSION_DENIED = 5;
    public static final int ERROR_CODE_BUYER_ACCOUNT_ERROR = 6;

    public static final String EXTRA_PAYMENT_DATA = "com.google.android.gms.wallet.EXTRA_PAYMENT_DATA";
    public static final String EXTRA_IS_READY_TO_PAY = "com.google.android.gms.wallet.EXTRA_IS_READY_TO_PAY";

    public static final int PAYMENT_METHOD_TYPE_CARD = 1;
    public static final int PAYMENT_METHOD_TYPE_TOKENIZED_CARD = 2;
}