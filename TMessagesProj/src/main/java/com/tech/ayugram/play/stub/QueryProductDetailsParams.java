package com.tech.ayugram.play.stub;

import java.util.List;

public class QueryProductDetailsParams {
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

        public String getProductId() { return productId; }
        public int getProductType() { return productType; }
    }
}