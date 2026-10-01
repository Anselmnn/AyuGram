package com.android.billingclient.api;

import com.google.android.gms.tasks.Task;

public class BillingClient {
    private android.content.Context context;
    private BillingClientStateListener listener;

    private BillingClient(android.content.Context context) {
        this.context = context;
    }

    public static Builder newBuilder(android.content.Context context) {
        return new Builder(context);
    }

    public void startConnection(BillingClientStateListener listener) {
        this.listener = listener;
    }

    public boolean isReady() {
        return false;
    }

    public void endConnection() {
    }

    public Task<BillingResult> queryProductDetailsAsync(QueryProductDetailsParams params) {
        return null;
    }

    public Task<BillingResult> queryPurchasesAsync(QueryPurchasesParams params) {
        return null;
    }

    public Task<BillingResult> launchBillingFlow(android.app.Activity activity, BillingFlowParams params) {
        return null;
    }

    public Task<BillingResult> acknowledgePurchase(AcknowledgePurchaseParams params) {
        return null;
    }

    public Task<BillingResult> consumeAsync(ConsumeParams params) {
        return null;
    }

    public Task<BillingResult> getPurchaseHistoryAsync(QueryPurchaseHistoryParams params) {
        return null;
    }

    public interface BillingClientStateListener {
        void onBillingSetupFinished(BillingResult billingResult);
        void onBillingServiceDisconnected();
    }

    public static class Builder {
        private android.content.Context context;
        private BillingClientStateListener listener;

        public Builder(android.content.Context context) {
            this.context = context;
        }

        public Builder setListener(BillingClientStateListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder enablePendingPurchases() {
            return this;
        }

        public BillingClient build() {
            return new BillingClient(context);
        }
    }

    public static class BillingResult {
        private int responseCode;
        private String debugMessage;

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

    public static class QueryProductDetailsParams {
        private java.util.List<Product> productList;

        private QueryProductDetailsParams(Builder builder) {
            this.productList = builder.productList;
        }

        public java.util.List<Product> getProductList() {
            return productList;
        }

        public static class Builder {
            private java.util.List<Product> productList = new java.util.ArrayList<>();

            public Builder setProductList(java.util.List<Product> productList) {
                this.productList = productList;
                return this;
            }

            public QueryProductDetailsParams build() {
                return new QueryProductDetailsParams(this);
            }
        }

        public static class Product {
            private String productId;
            private ProductType productType;

            public Product(String productId, ProductType productType) {
                this.productId = productId;
                this.productType = productType;
            }

            public String getProductId() {
                return productId;
            }

            public ProductType getProductType() {
                return productType;
            }

            public enum ProductType {
                SUBS, INAPP
            }
        }
    }

    public static class QueryPurchasesParams {
        private String productType;

        private QueryPurchasesParams(Builder builder) {
            this.productType = builder.productType;
        }

        public String getProductType() {
            return productType;
        }

        public static class Builder {
            private String productType;

            public Builder setProductType(String productType) {
                this.productType = productType;
                return this;
            }

            public QueryPurchasesParams build() {
                return new QueryPurchasesParams(this);
            }
        }
    }

    public static class BillingFlowParams {
        private java.util.List<ProductDetailsParams> productDetailsParamsList;
        private SubscriptionUpdateParams subscriptionUpdateParams;

        private BillingFlowParams(Builder builder) {
            this.productDetailsParamsList = builder.productDetailsParamsList;
            this.subscriptionUpdateParams = builder.subscriptionUpdateParams;
        }

        public java.util.List<ProductDetailsParams> getProductDetailsParamsList() {
            return productDetailsParamsList;
        }

        public SubscriptionUpdateParams getSubscriptionUpdateParams() {
            return subscriptionUpdateParams;
        }

        public static class Builder {
            private java.util.List<ProductDetailsParams> productDetailsParamsList = new java.util.ArrayList<>();
            private SubscriptionUpdateParams subscriptionUpdateParams;

            public Builder setProductDetailsParamsList(java.util.List<ProductDetailsParams> productDetailsParamsList) {
                this.productDetailsParamsList = productDetailsParamsList;
                return this;
            }

            public Builder setSubscriptionUpdateParams(SubscriptionUpdateParams subscriptionUpdateParams) {
                this.subscriptionUpdateParams = subscriptionUpdateParams;
                return this;
            }

            public BillingFlowParams build() {
                return new BillingFlowParams(this);
            }
        }

        public static class ProductDetailsParams {
            private ProductDetails productDetails;
            private String offerToken;

            public ProductDetailsParams(ProductDetails productDetails, String offerToken) {
                this.productDetails = productDetails;
                this.offerToken = offerToken;
            }

            public ProductDetails getProductDetails() {
                return productDetails;
            }

            public String getOfferToken() {
                return offerToken;
            }
        }

        public static class SubscriptionUpdateParams {
            private String oldPurchaseToken;
            private String replacementMode;
            private String prorationMode;

            private SubscriptionUpdateParams(Builder builder) {
                this.oldPurchaseToken = builder.oldPurchaseToken;
                this.replacementMode = builder.replacementMode;
                this.prorationMode = builder.prorationMode;
            }

            public String getOldPurchaseToken() {
                return oldPurchaseToken;
            }

            public String getReplacementMode() {
                return replacementMode;
            }

            public String getProrationMode() {
                return prorationMode;
            }

            public static class Builder {
                private String oldPurchaseToken;
                private String replacementMode;
                private String prorationMode;

                public Builder setOldPurchaseToken(String oldPurchaseToken) {
                    this.oldPurchaseToken = oldPurchaseToken;
                    return this;
                }

                public Builder setReplacementMode(String replacementMode) {
                    this.replacementMode = replacementMode;
                    return this;
                }

                public Builder setProrationMode(String prorationMode) {
                    this.prorationMode = prorationMode;
                    return this;
                }

                public SubscriptionUpdateParams build() {
                    return new SubscriptionUpdateParams(this);
                }
            }
        }
    }

    public static class AcknowledgePurchaseParams {
        private String purchaseToken;
        private String developerPayload;

        private AcknowledgePurchaseParams(Builder builder) {
            this.purchaseToken = builder.purchaseToken;
            this.developerPayload = builder.developerPayload;
        }

        public String getPurchaseToken() {
            return purchaseToken;
        }

        public String getDeveloperPayload() {
            return developerPayload;
        }

        public static class Builder {
            private String purchaseToken;
            private String developerPayload;

            public Builder setPurchaseToken(String purchaseToken) {
                this.purchaseToken = purchaseToken;
                return this;
            }

            public Builder setDeveloperPayload(String developerPayload) {
                this.developerPayload = developerPayload;
                return this;
            }

            public AcknowledgePurchaseParams build() {
                return new AcknowledgePurchaseParams(this);
            }
        }
    }

    public static class ConsumeParams {
        private String purchaseToken;

        private ConsumeParams(Builder builder) {
            this.purchaseToken = builder.purchaseToken;
        }

        public String getPurchaseToken() {
            return purchaseToken;
        }

        public static class Builder {
            private String purchaseToken;

            public Builder setPurchaseToken(String purchaseToken) {
                this.purchaseToken = purchaseToken;
                return this;
            }

            public ConsumeParams build() {
                return new ConsumeParams(this);
            }
        }
    }

    public static class QueryPurchaseHistoryParams {
        private String productType;

        private QueryPurchaseHistoryParams(Builder builder) {
            this.productType = builder.productType;
        }

        public String getProductType() {
            return productType;
        }

        public static class Builder {
            private String productType;

            public Builder setProductType(String productType) {
                this.productType = productType;
                return this;
            }

            public QueryPurchaseHistoryParams build() {
                return new QueryPurchaseHistoryParams(this);
            }
        }
    }
}