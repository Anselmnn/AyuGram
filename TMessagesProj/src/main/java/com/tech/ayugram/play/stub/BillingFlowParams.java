package com.tech.ayugram.play.stub;

/**
 * Phase 2: Stub for com.android.billingclient.api.BillingFlowParams
 * Billing dependency removed in Phase 2
 */
public class BillingFlowParams {
    public static class ProductDetailsParams {
        private ProductDetails productDetails;

        private ProductDetailsParams() {}

        public static class Builder {
            private ProductDetails productDetails;

            public Builder setProductDetails(ProductDetails productDetails) {
                this.productDetails = productDetails;
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
        // Stub
    }
}