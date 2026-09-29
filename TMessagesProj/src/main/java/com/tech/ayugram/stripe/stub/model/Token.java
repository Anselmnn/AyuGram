package com.tech.ayugram.stripe.stub.model;

public class Token {
    private String id;
    private String type;
    private String cardId;

    public Token() {}

    public String getId() { return id; }
    public String getType() { return type; }
    public String getCardId() { return cardId; }

    public void setId(String id) { this.id = id; }
    public void setType(String type) { this.type = type; }
    public void setCardId(String cardId) { this.cardId = cardId; }
}