package com.android.billingclient.api;

import java.util.List;

public class ProductDetails {
    private String productId;
    private String productType;
    private String title;
    private String description;
    private String name;
    private List<SubscriptionOfferDetails> subscriptionOfferDetails;
    private List<OneTimePurchaseOfferDetails> oneTimePurchaseOfferDetails;

    public ProductDetails() {
    }

    public String getProductId() {
        return productId;
    }

    public String getProductType() {
        return productType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public List<SubscriptionOfferDetails> getSubscriptionOfferDetails() {
        return subscriptionOfferDetails;
    }

    public List<OneTimePurchaseOfferDetails> getOneTimePurchaseOfferDetails() {
        return oneTimePurchaseOfferDetails;
    }

    public static class SubscriptionOfferDetails {
        private String offerIdToken;
        private String basePlanId;
        private List<PricingPhase> pricingPhases;

        public String getOfferIdToken() {
            return offerIdToken;
        }

        public String getBasePlanId() {
            return basePlanId;
        }

        public List<PricingPhase> getPricingPhases() {
            return pricingPhases;
        }

        public static class PricingPhase {
            private String formattedPrice;
            private String priceCurrencyCode;
            private long priceAmountMicros;
            private int billingPeriodCount;
            private String billingPeriod;

            public String getFormattedPrice() {
                return formattedPrice;
            }

            public String getPriceCurrencyCode() {
                return priceCurrencyCode;
            }

            public long getPriceAmountMicros() {
                return priceAmountMicros;
            }

            public int getBillingPeriodCount() {
                return billingPeriodCount;
            }

            public String getBillingPeriod() {
                return billingPeriod;
            }
        }
    }

    public static class OneTimePurchaseOfferDetails {
        private String formattedPrice;
        private String priceCurrencyCode;
        private long priceAmountMicros;

        public String getFormattedPrice() {
            return formattedPrice;
        }

        public String getPriceCurrencyCode() {
            return priceCurrencyCode;
        }

        public long getPriceAmountMicros() {
            return priceAmountMicros;
        }
    }
}