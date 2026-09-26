package com.tech.ayugram.stripe.stub;

/**
 * Phase 2: Stub for com.stripe.android.model.Token
 * Stripe dependency removed in Phase 2
 */
public class Token {
    private String id;
    private String cardId;
    private String clientIp;
    private long created;
    private boolean livemode;
    private String type;
    private boolean used;
    private Card card;

    public Token() {}

    public String getId() { return id; }
    public String getCardId() { return cardId; }
    public String getClientIp() { return clientIp; }
    public long getCreated() { return created; }
    public boolean getLivemode() { return livemode; }
    public String getType() { return type; }
    public boolean getUsed() { return used; }
    public Card getCard() { return card; }

    public void setId(String id) { this.id = id; }
    public void setCardId(String cardId) { this.cardId = cardId; }
    public void setClientIp(String clientIp) { this.clientIp = clientIp; }
    public void setCreated(long created) { this.created = created; }
    public void setLivemode(boolean livemode) { this.livemode = livemode; }
    public void setType(String type) { this.type = type; }
    public void setUsed(boolean used) { this.used = used; }
    public void setCard(Card card) { this.card = card; }
}