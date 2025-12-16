package com.foodapp.deliveryexecutive.map.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Generated;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private String formatted_address;
    private List<String> types;
    private String name;
    private Geometry geometry;
    private List<AddressComponent> address_components;
    private PlusCode plus_code;
    private String place_id;
    private List<String> layer;

    @Generated
    public Result() {
    }

    @Generated
    public String getFormatted_address() {
        return this.formatted_address;
    }

    @Generated
    public List<String> getTypes() {
        return this.types;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public Geometry getGeometry() {
        return this.geometry;
    }

    @Generated
    public List<AddressComponent> getAddress_components() {
        return this.address_components;
    }

    @Generated
    public PlusCode getPlus_code() {
        return this.plus_code;
    }

    @Generated
    public String getPlace_id() {
        return this.place_id;
    }

    @Generated
    public List<String> getLayer() {
        return this.layer;
    }

    @Generated
    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    @Generated
    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Generated
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Generated
    public void setAddress_components(List<AddressComponent> address_components) {
        this.address_components = address_components;
    }

    @Generated
    public void setPlus_code(PlusCode plus_code) {
        this.plus_code = plus_code;
    }

    @Generated
    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    @Generated
    public void setLayer(List<String> layer) {
        this.layer = layer;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Result)) {
            return false;
        }
        Result other = (Result)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$formatted_address = this.getFormatted_address();
        String other$formatted_address = other.getFormatted_address();
        if (this$formatted_address == null ? other$formatted_address != null : !this$formatted_address.equals(other$formatted_address)) {
            return false;
        }
        List<String> this$types = this.getTypes();
        List<String> other$types = other.getTypes();
        if (this$types == null ? other$types != null : !(this$types).equals(other$types)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        Geometry this$geometry = this.getGeometry();
        Geometry other$geometry = other.getGeometry();
        if (this$geometry == null ? other$geometry != null : !(this$geometry).equals(other$geometry)) {
            return false;
        }
        List<AddressComponent> this$address_components = this.getAddress_components();
        List<AddressComponent> other$address_components = other.getAddress_components();
        if (this$address_components == null ? other$address_components != null : !(this$address_components).equals(other$address_components)) {
            return false;
        }
        PlusCode this$plus_code = this.getPlus_code();
        PlusCode other$plus_code = other.getPlus_code();
        if (this$plus_code == null ? other$plus_code != null : !(this$plus_code).equals(other$plus_code)) {
            return false;
        }
        String this$place_id = this.getPlace_id();
        String other$place_id = other.getPlace_id();
        if (this$place_id == null ? other$place_id != null : !this$place_id.equals(other$place_id)) {
            return false;
        }
        List<String> this$layer = this.getLayer();
        List<String> other$layer = other.getLayer();
        return !(this$layer == null ? other$layer != null : !(this$layer).equals(other$layer));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Result;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $formatted_address = this.getFormatted_address();
        result = result * 59 + ($formatted_address == null ? 43 : $formatted_address.hashCode());
        List<String> $types = this.getTypes();
        result = result * 59 + ($types == null ? 43 : ((Object)$types).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Geometry $geometry = this.getGeometry();
        result = result * 59 + ($geometry == null ? 43 : ((Object)$geometry).hashCode());
        List<AddressComponent> $address_components = this.getAddress_components();
        result = result * 59 + ($address_components == null ? 43 : ((Object)$address_components).hashCode());
        PlusCode $plus_code = this.getPlus_code();
        result = result * 59 + ($plus_code == null ? 43 : ((Object)$plus_code).hashCode());
        String $place_id = this.getPlace_id();
        result = result * 59 + ($place_id == null ? 43 : $place_id.hashCode());
        List<String> $layer = this.getLayer();
        result = result * 59 + ($layer == null ? 43 : ((Object)$layer).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Result(formatted_address=" + this.getFormatted_address() + ", types=" + String.valueOf(this.getTypes()) + ", name=" + this.getName() + ", geometry=" + String.valueOf(this.getGeometry()) + ", address_components=" + String.valueOf(this.getAddress_components()) + ", plus_code=" + String.valueOf(this.getPlus_code()) + ", place_id=" + this.getPlace_id() + ", layer=" + String.valueOf(this.getLayer()) + ")";
    }
}
