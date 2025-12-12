/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.ElementCollection
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.MapKeyColumn
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.payments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

@Entity
public class Contact {
    @Id
    private String id;
    private String entity;
    private String name;
    private String contact;
    private String email;
    private String type;
    @Column(name="reference_id")
    private String referenceId;
    @Column(name="batch_id")
    private String batchId;
    private boolean active;
    @ElementCollection
    @MapKeyColumn(name="note_key")
    @Column(name="note_value", length=256)
    private Map<String, String> notes = new HashMap<String, String>();
    private int created_at;

    @Generated
    public Contact() {
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
    public String getName() {
        return this.name;
    }

    @Generated
    public String getContact() {
        return this.contact;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getType() {
        return this.type;
    }

    @Generated
    public String getReferenceId() {
        return this.referenceId;
    }

    @Generated
    public String getBatchId() {
        return this.batchId;
    }

    @Generated
    public boolean isActive() {
        return this.active;
    }

    @Generated
    public Map<String, String> getNotes() {
        return this.notes;
    }

    @Generated
    public int getCreated_at() {
        return this.created_at;
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
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public void setContact(String contact) {
        this.contact = contact;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setType(String type) {
        this.type = type;
    }

    @Generated
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    @Generated
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Generated
    public void setActive(boolean active) {
        this.active = active;
    }

    @Generated
    public void setNotes(Map<String, String> notes) {
        this.notes = notes;
    }

    @Generated
    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }
        Contact other = (Contact)o;
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
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$contact = this.getContact();
        String other$contact = other.getContact();
        if (this$contact == null ? other$contact != null : !this$contact.equals(other$contact)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$type = this.getType();
        String other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        String this$referenceId = this.getReferenceId();
        String other$referenceId = other.getReferenceId();
        if (this$referenceId == null ? other$referenceId != null : !this$referenceId.equals(other$referenceId)) {
            return false;
        }
        String this$batchId = this.getBatchId();
        String other$batchId = other.getBatchId();
        if (this$batchId == null ? other$batchId != null : !this$batchId.equals(other$batchId)) {
            return false;
        }
        Map<String, String> this$notes = this.getNotes();
        Map<String, String> other$notes = other.getNotes();
        return !(this$notes == null ? other$notes != null : !((Object)this$notes).equals(other$notes));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Contact;
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
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $contact = this.getContact();
        result = result * 59 + ($contact == null ? 43 : $contact.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        String $referenceId = this.getReferenceId();
        result = result * 59 + ($referenceId == null ? 43 : $referenceId.hashCode());
        String $batchId = this.getBatchId();
        result = result * 59 + ($batchId == null ? 43 : $batchId.hashCode());
        Map<String, String> $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : ((Object)$notes).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Contact(id=" + this.getId() + ", entity=" + this.getEntity() + ", name=" + this.getName() + ", contact=" + this.getContact() + ", email=" + this.getEmail() + ", type=" + this.getType() + ", referenceId=" + this.getReferenceId() + ", batchId=" + this.getBatchId() + ", active=" + this.isActive() + ", notes=" + String.valueOf(this.getNotes()) + ", created_at=" + this.getCreated_at() + ")";
    }
}
