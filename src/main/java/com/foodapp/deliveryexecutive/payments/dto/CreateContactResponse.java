/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.springframework.stereotype.Component
 */
package com.foodapp.deliveryexecutive.payments.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import org.springframework.stereotype.Component;

@Component
public class CreateContactResponse {
    private String id;
    private String entity;
    private String name;
    private String contact;
    private String email;
    private String type;
    private String reference_id;
    private String batch_id;
    private boolean active;
    private Map<String, String> notes = new HashMap<String, String>();
    private int created_at;

    @Generated
    public CreateContactResponse() {
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
    public String getReference_id() {
        return this.reference_id;
    }

    @Generated
    public String getBatch_id() {
        return this.batch_id;
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
    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    @Generated
    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
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
        if (!(o instanceof CreateContactResponse)) {
            return false;
        }
        CreateContactResponse other = (CreateContactResponse)o;
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
        String this$reference_id = this.getReference_id();
        String other$reference_id = other.getReference_id();
        if (this$reference_id == null ? other$reference_id != null : !this$reference_id.equals(other$reference_id)) {
            return false;
        }
        String this$batch_id = this.getBatch_id();
        String other$batch_id = other.getBatch_id();
        if (this$batch_id == null ? other$batch_id != null : !this$batch_id.equals(other$batch_id)) {
            return false;
        }
        Map<String, String> this$notes = this.getNotes();
        Map<String, String> other$notes = other.getNotes();
        return !(this$notes == null ? other$notes != null : !(this$notes).equals(other$notes));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof CreateContactResponse;
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
        String $reference_id = this.getReference_id();
        result = result * 59 + ($reference_id == null ? 43 : $reference_id.hashCode());
        String $batch_id = this.getBatch_id();
        result = result * 59 + ($batch_id == null ? 43 : $batch_id.hashCode());
        Map<String, String> $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : ((Object)$notes).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "CreateContactResponse(id=" + this.getId() + ", entity=" + this.getEntity() + ", name=" + this.getName() + ", contact=" + this.getContact() + ", email=" + this.getEmail() + ", type=" + this.getType() + ", reference_id=" + this.getReference_id() + ", batch_id=" + this.getBatch_id() + ", active=" + this.isActive() + ", notes=" + String.valueOf(this.getNotes()) + ", created_at=" + this.getCreated_at() + ")";
    }
}
