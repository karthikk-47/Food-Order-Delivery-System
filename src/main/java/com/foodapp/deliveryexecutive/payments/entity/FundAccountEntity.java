package com.foodapp.deliveryexecutive.payments.entity;

import com.foodapp.deliveryexecutive.payments.entity.FundAccount;
import com.foodapp.deliveryexecutive.payments.entity.Results;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Generated;

@Entity
public class FundAccountEntity {
    @Id
    private String id;
    private String entity;
    @Embedded
    @AttributeOverrides(value={@AttributeOverride(name="entity", column=@Column(name="fund_account_entity")), @AttributeOverride(name="id", column=@Column(name="fund_account_id")), @AttributeOverride(name="contact_id", column=@Column(name="fund_account_contact_id")), @AttributeOverride(name="account_type", column=@Column(name="fund_account_account_type"))})
    private FundAccount fund_account;
    private boolean active;
    @Column(name="batch_id")
    private String batchId;
    private String status;
    @Embedded
    @AttributeOverrides(value={@AttributeOverride(name="account_status", column=@Column(name="results_account_status")), @AttributeOverride(name="registered_name", column=@Column(name="results_registered_name"))})
    private Results results;
    private int created_at;
    private String utr;

    @Generated
    public FundAccountEntity() {
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
    public FundAccount getFund_account() {
        return this.fund_account;
    }

    @Generated
    public boolean isActive() {
        return this.active;
    }

    @Generated
    public String getBatchId() {
        return this.batchId;
    }

    @Generated
    public String getStatus() {
        return this.status;
    }

    @Generated
    public Results getResults() {
        return this.results;
    }

    @Generated
    public int getCreated_at() {
        return this.created_at;
    }

    @Generated
    public String getUtr() {
        return this.utr;
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
    public void setFund_account(FundAccount fund_account) {
        this.fund_account = fund_account;
    }

    @Generated
    public void setActive(boolean active) {
        this.active = active;
    }

    @Generated
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Generated
    public void setStatus(String status) {
        this.status = status;
    }

    @Generated
    public void setResults(Results results) {
        this.results = results;
    }

    @Generated
    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    @Generated
    public void setUtr(String utr) {
        this.utr = utr;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FundAccountEntity)) {
            return false;
        }
        FundAccountEntity other = (FundAccountEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.isActive() != other.isActive()) {
            return false;
        }
        if (this.getCreated_at() != other.getCreated_at()) {
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
        FundAccount this$fund_account = this.getFund_account();
        FundAccount other$fund_account = other.getFund_account();
        if (this$fund_account == null ? other$fund_account != null : !(this$fund_account).equals(other$fund_account)) {
            return false;
        }
        String this$batchId = this.getBatchId();
        String other$batchId = other.getBatchId();
        if (this$batchId == null ? other$batchId != null : !this$batchId.equals(other$batchId)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        Results this$results = this.getResults();
        Results other$results = other.getResults();
        if (this$results == null ? other$results != null : !(this$results).equals(other$results)) {
            return false;
        }
        String this$utr = this.getUtr();
        String other$utr = other.getUtr();
        return !(this$utr == null ? other$utr != null : !this$utr.equals(other$utr));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof FundAccountEntity;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isActive() ? 79 : 97);
        result = result * 59 + this.getCreated_at();
        String $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        String $entity = this.getEntity();
        result = result * 59 + ($entity == null ? 43 : $entity.hashCode());
        FundAccount $fund_account = this.getFund_account();
        result = result * 59 + ($fund_account == null ? 43 : ((Object)$fund_account).hashCode());
        String $batchId = this.getBatchId();
        result = result * 59 + ($batchId == null ? 43 : $batchId.hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Results $results = this.getResults();
        result = result * 59 + ($results == null ? 43 : ((Object)$results).hashCode());
        String $utr = this.getUtr();
        result = result * 59 + ($utr == null ? 43 : $utr.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "FundAccountEntity(id=" + this.getId() + ", entity=" + this.getEntity() + ", fund_account=" + String.valueOf(this.getFund_account()) + ", active=" + this.isActive() + ", batchId=" + this.getBatchId() + ", status=" + this.getStatus() + ", results=" + String.valueOf(this.getResults()) + ", created_at=" + this.getCreated_at() + ", utr=" + this.getUtr() + ")";
    }
}
