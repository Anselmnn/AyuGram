package com.tech.ayugram.play.stub;

import java.util.List;

public class ProductDetails {
    private String productId;
    private String name;
    private String description;
    private OneTimePurchaseOfferDetails oneTimePurchaseOfferDetails;
    private List<SubscriptionOfferDetails> subscriptionOfferDetailsList;

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public OneTimePurchaseOfferDetails getOneTimePurchaseOfferDetails() { return oneTimePurchaseOfferDetails; }
    public List<SubscriptionOfferDetails> getSubscriptionOfferDetails() { return subscriptionOfferDetailsList; }

    public static class OneTimePurchaseOfferDetails {
        private long priceAmountMicros;
        private String formattedPrice;
        private String currencyCode;

        public long getPriceAmountMicros() { return priceAmountMicros; }
        public String getFormattedPrice() { return formattedPrice; }
        public String getCurrencyCode() { return currencyCode; }

        // Stub for compatibility with code that incorrectly calls this on one-time purchases
        public PricingPhases getPricingPhases() { return null; }
    }

    public static class SubscriptionOfferDetails {
        private String offerIdToken;
        private String basePlanId;
        private PricingPhase pricingPhase;
        private PricingPhases pricingPhases;

        public String getOfferIdToken() { return offerIdToken; }
        public String getBasePlanId() { return basePlanId; }
        public PricingPhase getPricingPhase() { return pricingPhase; }
        public PricingPhases getPricingPhases() { return pricingPhases; }

        public static class PricingPhase {
            private long priceAmountMicros;
            private String formattedPrice;
            private String currencyCode;
            private int billingPeriodCount;
            private String billingPeriod;

            public long getPriceAmountMicros() { return priceAmountMicros; }
            public String getFormattedPrice() { return formattedPrice; }
            public String getCurrencyCode() { return currencyCode; }
            public int getBillingPeriodCount() { return billingPeriodCount; }
            public String getBillingPeriod() { return billingPeriod; }
        }
    }

    public static class PricingPhases {
        private List<PricingPhase> pricingPhaseList;

        public List<PricingPhase> getPricingPhaseList() { return pricingPhaseList; }

        public static class PricingPhase {
            private long priceAmountMicros;
            private String formattedPrice;
            private String currencyCode;
            private int billingPeriodCount;
            private String billingPeriod;

            public long getPriceAmountMicros() { return priceAmountMicros; }
            public String getFormattedPrice() { return formattedPrice; }
            public String getCurrencyCode() { return currencyCode; }
            public int getBillingPeriodCount() { return billingPeriodCount; }
            public String getBillingPeriod() { return billingPeriod; }
        }
    }
}