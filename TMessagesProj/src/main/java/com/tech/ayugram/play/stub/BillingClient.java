package com.tech.ayugram.play.stub;

import java.util.List;

/**
 * Phase 2: Stub for com.android.billingclient.api.BillingClient
 * Billing dependency removed in Phase 2
 */
public class BillingClient {
    public static class BillingResponseCode {
        public static final int OK = 0;
        public static final int USER_CANCELED = 1;
        public static final int SERVICE_UNAVAILABLE = 2;
        public static final int BILLING_UNAVAILABLE = 3;
        public static final int ITEM_UNAVAILABLE = 4;
        public static final int DEVELOPER_ERROR = 5;
        public static final int ERROR = 6;
        public static final int ITEM_ALREADY_OWNED = 7;
        public static final int ITEM_NOT_OWNED = 8;
    }

    public static class ProductType {
        public static final int INAPP = 1;
        public static final int SUBS = 2;
    }

    public static class QueryProductDetailsParams {
        public static class Product {
            private String productId;
            private int productType;

            private Product() {}

            public static class Builder {
                private String productId;
                private int productType;

                public Builder setProductId(String productId) {
                    this.productId = productId;
                    return this;
                }

                public Builder setProductType(int productType) {
                    this.productType = productType;
                    return this;
                }

                public Product build() {
                    Product product = new Product();
                    product.productId = this.productId;
                    product.productType = this.productType;
                    return product;
                }
            }

            public static Builder newBuilder() {
                return new Builder();
            }

            public String getProductId() {
                return productId;
            }

            public int getProductType() {
                return productType;
            }
        }
    }

    public static class BillingFlowParams {
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
}