package com.foodapp.deliveryexecutive.payments.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import org.springframework.stereotype.Component;

@Component
public class CreateContactRequest {
    String name;
    String email;
    String contact;
    String type;
    String reference_id;
    Map<String, String> notes = new HashMap<String, String>();

    @Generated
    public CreateContactRequest() {
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getContact() {
        return this.contact;
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
    public Map<String, String> getNotes() {
        return this.notes;
    }

    @Generated
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setContact(String contact) {
        this.contact = contact;
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
    public void setNotes(Map<String, String> notes) {
        this.notes = notes;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreateContactRequest)) {
            return false;
        }
        CreateContactRequest other = (CreateContactRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$contact = this.getContact();
        String other$contact = other.getContact();
        if (this$contact == null ? other$contact != null : !this$contact.equals(other$contact)) {
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
        Map<String, String> this$notes = this.getNotes();
        Map<String, String> other$notes = other.getNotes();
        return !(this$notes == null ? other$notes != null : !(this$notes).equals(other$notes));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof CreateContactRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $contact = this.getContact();
        result = result * 59 + ($contact == null ? 43 : $contact.hashCode());
        String $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        String $reference_id = this.getReference_id();
        result = result * 59 + ($reference_id == null ? 43 : $reference_id.hashCode());
        Map<String, String> $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : ((Object)$notes).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "CreateContactRequest(name=" + this.getName() + ", email=" + this.getEmail() + ", contact=" + this.getContact() + ", type=" + this.getType() + ", reference_id=" + this.getReference_id() + ", notes=" + String.valueOf(this.getNotes()) + ")";
    }
}
