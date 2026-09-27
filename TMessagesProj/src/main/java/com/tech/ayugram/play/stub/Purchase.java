package com.tech.ayugram.play.stub;

public class Purchase {
    private String orderId;
    private String packageName;
    private String productId;
    private long purchaseTime;
    private int purchaseState;
    private String developerPayload;
    private String token;
    private String originalJson;
    private String signature;
    private boolean isAcknowledged;

    public String getOrderId() { return orderId; }
    public String getPackageName() { return packageName; }
    public String getProductId() { return productId; }
    public long getPurchaseTime() { return purchaseTime; }
    public int getPurchaseState() { return purchaseState; }
    public String getDeveloperPayload() { return developerPayload; }
    public String getToken() { return token; }
    public String getOriginalJson() { return originalJson; }
    public String getSignature() { return signature; }
    public boolean isAcknowledged() { return isAcknowledged; }
}