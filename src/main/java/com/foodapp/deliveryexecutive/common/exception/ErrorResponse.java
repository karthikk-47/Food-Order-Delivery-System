package com.foodapp.deliveryexecutive.common.exception;

import java.time.LocalDateTime;
import lombok.Generated;

public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String details;

    public ErrorResponse(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    @Generated
    public int getStatus() {
        return this.status;
    }

    @Generated
    public String getMessage() {
        return this.message;
    }

    @Generated
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    @Generated
    public String getDetails() {
        return this.details;
    }

    @Generated
    public void setStatus(int status) {
        this.status = status;
    }

    @Generated
    public void setMessage(String message) {
        this.message = message;
    }

    @Generated
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Generated
    public void setDetails(String details) {
        this.details = details;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ErrorResponse)) {
            return false;
        }
        ErrorResponse other = (ErrorResponse)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        String this$message = this.getMessage();
        String other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) {
            return false;
        }
        LocalDateTime this$timestamp = this.getTimestamp();
        LocalDateTime other$timestamp = other.getTimestamp();
        if (this$timestamp == null ? other$timestamp != null : !(this$timestamp).equals(other$timestamp)) {
            return false;
        }
        String this$details = this.getDetails();
        String other$details = other.getDetails();
        return !(this$details == null ? other$details != null : !this$details.equals(other$details));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof ErrorResponse;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getStatus();
        String $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        LocalDateTime $timestamp = this.getTimestamp();
        result = result * 59 + ($timestamp == null ? 43 : ((Object)$timestamp).hashCode());
        String $details = this.getDetails();
        result = result * 59 + ($details == null ? 43 : $details.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "ErrorResponse(status=" + this.getStatus() + ", message=" + this.getMessage() + ", timestamp=" + String.valueOf(this.getTimestamp()) + ", details=" + this.getDetails() + ")";
    }
}
