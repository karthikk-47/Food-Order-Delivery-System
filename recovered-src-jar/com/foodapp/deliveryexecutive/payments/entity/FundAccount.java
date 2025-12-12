/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.AttributeOverride
 *  jakarta.persistence.AttributeOverrides
 *  jakarta.persistence.Column
 *  jakarta.persistence.Embeddable
 *  jakarta.persistence.Embedded
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.payments.entity;

import com.foodapp.deliveryexecutive.payments.entity.BankAccount;
import com.foodapp.deliveryexecutive.payments.entity.Card;
import com.foodapp.deliveryexecutive.payments.entity.Vpa;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Generated;

@Embeddable
public class FundAccount {
    private String id;
    private String entity;
    private String contact_id;
    private String account_type;
    @Embedded
    @AttributeOverrides(value={@AttributeOverride(name="name", column=@Column(name="bank_account_name")), @AttributeOverride(name="bank_name", column=@Column(name="bank_account_bank_name")), @AttributeOverride(name="ifsc", column=@Column(name="bank_account_ifsc")), @AttributeOverride(name="account_number", column=@Column(name="bank_account_number"))})
    private BankAccount bank_account;
    @Embedded
    @AttributeOverrides(value={@AttributeOverride(name="username", column=@Column(name="vpa_username")), @AttributeOverride(name="handle", column=@Column(name="vpa_handle")), @AttributeOverride(name="address", column=@Column(name="vpa_address"))})
    private Vpa vpa;
    @Embedded
    @AttributeOverrides(value={@AttributeOverride(name="name", column=@Column(name="card_name")), @AttributeOverride(name="last4", column=@Column(name="card_last4")), @AttributeOverride(name="network", column=@Column(name="card_network")), @AttributeOverride(name="type", column=@Column(name="card_type")), @AttributeOverride(name="issuer", column=@Column(name="card_issuer"))})
    private Card card;

    @Generated
    public FundAccount() {
    }

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getEntity() {
        return this.entity;
    }

    @Generated
    public String getContact_id() {
        return this.contact_id;
    }

    @Generated
    public String getAccount_type() {
        return this.account_type;
    }

    @Generated
    public BankAccount getBank_account() {
        return this.bank_account;
    }

    @Generated
    public Vpa getVpa() {
        return this.vpa;
    }

    @Generated
    public Card getCard() {
        return this.card;
    }

    @Generated
    public void setId(String id) {
        this.id = id;
    }

    @Generated
    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Generated
    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    @Generated
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    @Generated
    public void setBank_account(BankAccount bank_account) {
        this.bank_account = bank_account;
    }

    @Generated
    public void setVpa(Vpa vpa) {
        this.vpa = vpa;
    }

    @Generated
    public void setCard(Card card) {
        this.card = card;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FundAccount)) {
            return false;
        }
        FundAccount other = (FundAccount)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$id = this.getId();
        String other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) {
            return false;
        }
        String this$entity = this.getEntity();
        String other$entity = other.getEntity();
        if (this$entity == null ? other$entity != null : !this$entity.equals(other$entity)) {
            return false;
        }
        String this$contact_id = this.getContact_id();
        String other$contact_id = other.getContact_id();
        if (this$contact_id == null ? other$contact_id != null : !this$contact_id.equals(other$contact_id)) {
            return false;
        }
        String this$account_type = this.getAccount_type();
        String other$account_type = other.getAccount_type();
        if (this$account_type == null ? other$account_type != null : !this$account_type.equals(other$account_type)) {
            return false;
        }
        BankAccount this$bank_account = this.getBank_account();
        BankAccount other$bank_account = other.getBank_account();
        if (this$bank_account == null ? other$bank_account != null : !((Object)this$bank_account).equals(other$bank_account)) {
            return false;
        }
        Vpa this$vpa = this.getVpa();
        Vpa other$vpa = other.getVpa();
        if (this$vpa == null ? other$vpa != null : !((Object)this$vpa).equals(other$vpa)) {
            return false;
        }
        Card this$card = this.getCard();
        Card other$card = other.getCard();
        return !(this$card == null ? other$card != null : !((Object)this$card).equals(other$card));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof FundAccount;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        String $entity = this.getEntity();
        result = result * 59 + ($entity == null ? 43 : $entity.hashCode());
        String $contact_id = this.getContact_id();
        result = result * 59 + ($contact_id == null ? 43 : $contact_id.hashCode());
        String $account_type = this.getAccount_type();
        result = result * 59 + ($account_type == null ? 43 : $account_type.hashCode());
        BankAccount $bank_account = this.getBank_account();
        result = result * 59 + ($bank_account == null ? 43 : ((Object)$bank_account).hashCode());
        Vpa $vpa = this.getVpa();
        result = result * 59 + ($vpa == null ? 43 : ((Object)$vpa).hashCode());
        Card $card = this.getCard();
        result = result * 59 + ($card == null ? 43 : ((Object)$card).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "FundAccount(id=" + this.getId() + ", entity=" + this.getEntity() + ", contact_id=" + this.getContact_id() + ", account_type=" + this.getAccount_type() + ", bank_account=" + String.valueOf(this.getBank_account()) + ", vpa=" + String.valueOf(this.getVpa()) + ", card=" + String.valueOf(this.getCard()) + ")";
    }
}
