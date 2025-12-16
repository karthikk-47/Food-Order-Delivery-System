/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.map.dto;

import com.foodapp.deliveryexecutive.map.dto.Row;
import java.util.List;
import lombok.Generated;

public class DistanceMatrixResponseDTO {
    List<Row> rows;
    String status;

    public List<Row> getRows() {
        return this.rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @Generated
    public String getStatus() {
        return this.status;
    }

    @Generated
    public void setStatus(String status) {
        this.status = status;
    }
}
