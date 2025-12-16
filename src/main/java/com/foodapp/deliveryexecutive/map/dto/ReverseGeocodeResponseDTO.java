/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.map.dto;

import com.foodapp.deliveryexecutive.map.dto.PlusCode;
import com.foodapp.deliveryexecutive.map.dto.Result;
import java.util.List;
import lombok.Generated;

public class ReverseGeocodeResponseDTO {
    private String error_message;
    private List<String> info_messages;
    private List<Result> results;
    private PlusCode plus_code;
    String status;

    @Generated
    public ReverseGeocodeResponseDTO() {
    }

    @Generated
    public String getError_message() {
        return this.error_message;
    }

    @Generated
    public List<String> getInfo_messages() {
        return this.info_messages;
    }

    @Generated
    public List<Result> getResults() {
        return this.results;
    }

    @Generated
    public PlusCode getPlus_code() {
        return this.plus_code;
    }

    @Generated
    public String getStatus() {
        return this.status;
    }

    @Generated
    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Generated
    public void setInfo_messages(List<String> info_messages) {
        this.info_messages = info_messages;
    }

    @Generated
    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Generated
    public void setPlus_code(PlusCode plus_code) {
        this.plus_code = plus_code;
    }

    @Generated
    public void setStatus(String status) {
        this.status = status;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReverseGeocodeResponseDTO)) {
            return false;
        }
        ReverseGeocodeResponseDTO other = (ReverseGeocodeResponseDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$error_message = this.getError_message();
        String other$error_message = other.getError_message();
        if (this$error_message == null ? other$error_message != null : !this$error_message.equals(other$error_message)) {
            return false;
        }
        List<String> this$info_messages = this.getInfo_messages();
        List<String> other$info_messages = other.getInfo_messages();
        if (this$info_messages == null ? other$info_messages != null : !(this$info_messages).equals(other$info_messages)) {
            return false;
        }
        List<Result> this$results = this.getResults();
        List<Result> other$results = other.getResults();
        if (this$results == null ? other$results != null : !(this$results).equals(other$results)) {
            return false;
        }
        PlusCode this$plus_code = this.getPlus_code();
        PlusCode other$plus_code = other.getPlus_code();
        if (this$plus_code == null ? other$plus_code != null : !(this$plus_code).equals(other$plus_code)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        return !(this$status == null ? other$status != null : !this$status.equals(other$status));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof ReverseGeocodeResponseDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $error_message = this.getError_message();
        result = result * 59 + ($error_message == null ? 43 : $error_message.hashCode());
        List<String> $info_messages = this.getInfo_messages();
        result = result * 59 + ($info_messages == null ? 43 : ((Object)$info_messages).hashCode());
        List<Result> $results = this.getResults();
        result = result * 59 + ($results == null ? 43 : ((Object)$results).hashCode());
        PlusCode $plus_code = this.getPlus_code();
        result = result * 59 + ($plus_code == null ? 43 : ((Object)$plus_code).hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "ReverseGeocodeResponseDTO(error_message=" + this.getError_message() + ", info_messages=" + String.valueOf(this.getInfo_messages()) + ", results=" + String.valueOf(this.getResults()) + ", plus_code=" + String.valueOf(this.getPlus_code()) + ", status=" + this.getStatus() + ")";
    }
}
