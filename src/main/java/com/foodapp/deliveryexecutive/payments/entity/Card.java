package com.foodapp.deliveryexecutive.payments.entity;

import jakarta.persistence.Embeddable;
import lombok.Generated;

@Embeddable
public class Card {
    private String name;
    private String last4;
    private String network;
    private String type;
    private String issuer;

    @Generated
    public Card() {
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getLast4() {
        return this.last4;
    }

    @Generated
    public String getNetwork() {
        return this.network;
    }

    @Generated
    public String getType() {
        return this.type;
    }

    @Generated
    public String getIssuer() {
        return this.issuer;
    }

    @Generated
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public void setLast4(String last4) {
        this.last4 = last4;
    }

    @Generated
    public void setNetwork(String network) {
        this.network = network;
    }

    @Generated
    public void setType(String type) {
        this.type = type;
    }

    @Generated
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        Card other = (Card)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$last4 = this.getLast4();
        String other$last4 = other.getLast4();
        if (this$last4 == null ? other$last4 != null : !this$last4.equals(other$last4)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$type = this.getType();
        String other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        String this$issuer = this.getIssuer();
        String other$issuer = other.getIssuer();
        return !(this$issuer == null ? other$issuer != null : !this$issuer.equals(other$issuer));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Card;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $last4 = this.getLast4();
        result = result * 59 + ($last4 == null ? 43 : $last4.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        String $issuer = this.getIssuer();
        result = result * 59 + ($issuer == null ? 43 : $issuer.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Card(name=" + this.getName() + ", last4=" + this.getLast4() + ", network=" + this.getNetwork() + ", type=" + this.getType() + ", issuer=" + this.getIssuer() + ")";
    }
}
