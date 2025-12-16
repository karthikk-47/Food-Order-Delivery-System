/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Inheritance
 *  jakarta.persistence.InheritanceType
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Generated;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Actor {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Role role;

    @Generated
    public Actor() {
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Actor)) {
            return false;
        }
        Actor other = (Actor)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Role this$role = this.getRole();
        Role other$role = other.getRole();
        return !(this$role == null ? other$role != null : !((Object)((Object)this$role)).equals((Object)other$role));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Actor;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Role $role = this.getRole();
        result = result * 59 + ($role == null ? 43 : ((Object)((Object)$role)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Actor(id=" + this.getId() + ", role=" + String.valueOf((Object)this.getRole()) + ")";
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Role getRole() {
        return this.role;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setRole(Role role) {
        this.role = role;
    }

    public static enum Role {
        HOMEMAKER,
        DELIVERYEXECUTIVE,
        ADMIN,
        USER;

    }
}
