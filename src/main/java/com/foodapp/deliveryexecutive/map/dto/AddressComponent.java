package com.foodapp.deliveryexecutive.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Generated;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AddressComponent {
    private List<String> types;
    private String short_name;
    private String long_name;

    @Generated
    public AddressComponent() {
    }

    @Generated
    public List<String> getTypes() {
        return this.types;
    }

    @Generated
    public String getShort_name() {
        return this.short_name;
    }

    @Generated
    public String getLong_name() {
        return this.long_name;
    }

    @Generated
    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Generated
    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    @Generated
    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AddressComponent)) {
            return false;
        }
        AddressComponent other = (AddressComponent)o;
        if (!other.canEqual(this)) {
            return false;
        }
        List<String> this$types = this.getTypes();
        List<String> other$types = other.getTypes();
        if (this$types == null ? other$types != null : !(this$types).equals(other$types)) {
            return false;
        }
        String this$short_name = this.getShort_name();
        String other$short_name = other.getShort_name();
        if (this$short_name == null ? other$short_name != null : !this$short_name.equals(other$short_name)) {
            return false;
        }
        String this$long_name = this.getLong_name();
        String other$long_name = other.getLong_name();
        return !(this$long_name == null ? other$long_name != null : !this$long_name.equals(other$long_name));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof AddressComponent;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        List<String> $types = this.getTypes();
        result = result * 59 + ($types == null ? 43 : ((Object)$types).hashCode());
        String $short_name = this.getShort_name();
        result = result * 59 + ($short_name == null ? 43 : $short_name.hashCode());
        String $long_name = this.getLong_name();
        result = result * 59 + ($long_name == null ? 43 : $long_name.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "AddressComponent(types=" + String.valueOf(this.getTypes()) + ", short_name=" + this.getShort_name() + ", long_name=" + this.getLong_name() + ")";
    }
}
