package com.tech.ayugram.play.stub;

public class BillingFlowParams {
    public static class ProductDetailsParams {
        private ProductDetails productDetails;
        private String offerToken;

        private ProductDetailsParams() {}

        public static class Builder {
            private ProductDetails productDetails;
            private String offerToken;

            public Builder setProductDetails(ProductDetails productDetails) {
                this.productDetails = productDetails;
                return this;
            }

            public Builder setOfferToken(String offerToken) {
                this.offerToken = offerToken;
                return this;
            }

            public ProductDetailsParams build() {
                ProductDetailsParams params = new ProductDetailsParams();
                params.productDetails = this.productDetails;
                return params;
            }
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public ProductDetails getProductDetails() {
            return productDetails;
        }
    }

    public static class SubscriptionUpdateParams {
        public static class Builder {
            private String oldPurchaseToken;
            private int replacementMode;

            public Builder setOldPurchaseToken(String token) {
                this.oldPurchaseToken = token;
                return this;
            }

            public Builder setSubscriptionReplacementMode(int mode) {
                this.replacementMode = mode;
                return this;
            }

            public SubscriptionUpdateParams build() {
                return new SubscriptionUpdateParams();
            }
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static class ReplacementMode {
            public static final int CHARGE_FULL_PRICE = 1;
            public static final int CHARGE_PRORATED_PRICE = 2;
            public static final int WITHOUT_PRORATION = 3;
        }
    }
}