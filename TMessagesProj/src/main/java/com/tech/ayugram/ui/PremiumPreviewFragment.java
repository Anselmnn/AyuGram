package com.tech.ayugram.ui;

public class PremiumPreviewFragment extends BaseFragment {
    public static final String TRANSACTION_PATTERN = "^(.*?)(?:\\.\\d*|)$";
    private final static boolean IS_PREMIUM_TIERS_UNAVAILABLE = false;
    
    public void setForcePremium() {}
    public String getPremiumButtonText(int currentAccount, SubscriptionTier tier) { return ""; }
    
    public static class SubscriptionTier {
        public String getFormattedPrice(String currency) { return "0"; }
        public String getPriceCurrencyCode() { return "USD"; }
        public long getPriceAmountMicros() { return 0; }
        public String getOfferToken() { return ""; }
        public Object getOfferDetails() { return null; }
        public int getMonths() { return 1; }
        public String store_product;
        public String currency;
        public long amount;
        public boolean loadingStorePrice;
        public boolean missingStorePrice;
        public Object starsOption;
    }
}
