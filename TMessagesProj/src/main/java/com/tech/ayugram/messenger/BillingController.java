package com.tech.ayugram.messenger;

import com.tech.ayugram.play.stub.BillingClient;
import com.tech.ayugram.play.stub.BillingFlowParams;
import com.tech.ayugram.play.stub.ProductDetails;

import java.util.List;

/**
 * Phase 2: Stub implementation of BillingController
 * Billing dependency removed in Phase 2
 */
public class BillingController {
    private static volatile BillingController instance;

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
        // No-op, billing removed
        callback.onResult(new BillingResult(BillingClient.BillingResponseCode.ERROR, "Billing removed"), null);
    }

    public void addResultListener(String productId, BillingResultListener listener) {
        // No-op
    }

    public void launchBillingFlow(Activity activity, AccountInstance accountInstance, Object giftPremium, List<BillingFlowParams.ProductDetailsParams> params) {
        // No-op
    }

    public String formatCurrency(double amount, String currency) {
        return "0";
    }

    public String formatCurrency(double amount, String currency, int precision) {
        return "0";
    }

    public int getCurrencyExp(String currency) {
        return 6;
    }

    public static class BillingResult {
        private final int responseCode;
        private final String debugMessage;

        public BillingResult(int responseCode, String debugMessage) {
            this.responseCode = responseCode;
            this.debugMessage = debugMessage;
        }

        public int getResponseCode() {
            return responseCode;
        }

        public String getDebugMessage() {
            return debugMessage;
        }
    }

    public interface BillingResultCallback {
        void onResult(BillingResult billingResult, List<ProductDetails> list);
    }

    public interface BillingResultListener {
        void onResult(BillingResult billingResult);
    }
}