package com.tech.ayugram.messenger;

import com.tech.ayugram.play.stub.BillingClient;
import com.tech.ayugram.play.stub.BillingFlowParams;
import com.tech.ayugram.play.stub.ProductDetails;

import java.util.List;

public class BillingController {
    private static volatile BillingController instance;
    public static ProductDetails PREMIUM_PRODUCT_DETAILS = null;
    public static final String PREMIUM_PRODUCT_ID = "premium_subscription";

    public static BillingController getInstance() {
        if (instance == null) {
            synchronized (BillingController.class) {
                if (instance == null) {
                    instance = new BillingController();
                }
            }
        }
        return instance;
    }

    private BillingController() {}

    public boolean isReady() {
        return false;
    }

    public void queryProductDetails(List<BillingClient.QueryProductDetailsParams.Product> products, BillingResultCallback callback) {
        callback.onResult(new BillingResult(BillingClient.BillingResponseCode.ERROR, "Billing removed"), null);
    }

    public void queryPurchases(int productType, PurchaseCallback callback) {
        callback.onPurchasesResult(new BillingResult(BillingClient.BillingResponseCode.ERROR, "Billing removed"), null);
    }

    public void addResultListener(String productId, BillingResultListener listener) {}

    public void launchBillingFlow(android.app.Activity activity, AccountInstance accountInstance, Object giftPremium, List<BillingFlowParams.ProductDetailsParams> params) {}

    public String formatCurrency(double amount, String currency) {
        return "0";
    }

    public String formatCurrency(double amount, String currency, int precision) {
        return "0";
    }

    public String formatCurrency(long amount, String currency, int precision, boolean isRtl) {
        return "0";
    }

    public int getCurrencyExp(String currency) {
        return 6;
    }

    public String getLastPremiumTransaction() {
        return null;
    }

    public String getLastPremiumToken() {
        return null;
    }

    public static String getResponseCodeString(int responseCode) {
        return String.valueOf(responseCode);
    }

    public void whenSetuped(Runnable runnable) {
        // Billing removed in Phase 2
    }

    public void setOnCanceled(Runnable runnable) {
        // Billing removed in Phase 2
    }

    public String getPriceCurrencyCode() {
        return "USD";
    }

    public long getPriceAmountMicros() {
        return 0;
    }

    public Object getPricingPhases() {
        return null;
    }

    public String getBillingPeriod() {
        return "P1M";
    }

    public void consumeGiftPurchase(com.tech.ayugram.play.stub.Purchase purchase, Object purpose, Runnable onSuccess) {
        // Billing removed in Phase 2
        if (onSuccess != null) onSuccess.run();
    }

    public static class BillingResult {
        private final int responseCode;
        private final String debugMessage;

        public BillingResult(int responseCode, String debugMessage) {
            this.responseCode = responseCode;
            this.debugMessage = debugMessage;
        }

        public int getResponseCode() { return responseCode; }
        public String getDebugMessage() { return debugMessage; }
    }

    public interface BillingResultCallback {
        void onResult(BillingResult billingResult, List<ProductDetails> list);
    }

    public interface PurchaseCallback {
        void onPurchasesResult(BillingResult billingResult, List<com.tech.ayugram.play.stub.Purchase> list);
    }

    public interface BillingResultListener {
        void onResult(BillingResult billingResult);
    }
}