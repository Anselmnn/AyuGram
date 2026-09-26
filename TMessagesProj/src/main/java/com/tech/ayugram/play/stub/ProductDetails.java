package com.tech.ayugram.play.stub;

import java.util.List;

/**
 * Phase 2: Stub for com.android.billingclient.api.ProductDetails
 * Billing dependency removed in Phase 2
 */
public class ProductDetails {
    private String productId;
    private String name;
    private String description;
    private OneTimePurchaseOfferDetails oneTimePurchaseOfferDetails;
    private SubscriptionOfferDetails subscriptionOfferDetails;

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public OneTimePurchaseOfferDetails getOneTimePurchaseOfferDetails() {
        return oneTimePurchaseOfferDetails;
    }

    public SubscriptionOfferDetails getSubscriptionOfferDetails() {
        return subscriptionOfferDetails;
    }

    public static class OneTimePurchaseOfferDetails {
        private long priceAmountMicros;
        private String formattedPrice;
        private String currencyCode;

        public long getPriceAmountMicros() {
            return priceAmountMicros;
        }

        public String getFormattedPrice() {
            return formattedPrice;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }
    }

    public static class SubscriptionOfferDetails {
        private String offerIdToken;
        private String basePlanId;
        private PricingPhase pricingPhase;

        public String getOfferIdToken() {
            return offerIdToken;
        }

        public String getBasePlanId() {
            return basePlanId;
        }

        public PricingPhase getPricingPhase() {
            return pricingPhase;
        }

        public static class PricingPhase {
            private long priceAmountMicros;
            private String formattedPrice;
            private String currencyCode;
            private int billingPeriodCount;
            private String billingPeriod;

            public long getPriceAmountMicros() {
                return priceAmountMicros;
            }

            public String getFormattedPrice() {
                return formattedPrice;
            }

            public String getCurrencyCode() {
                return currencyCode;
            }

            public int getBillingPeriodCount() {
                return billingPeriodCount;
            }

            public String getBillingPeriod() {
                return billingPeriod;
            }
        }
    }
}