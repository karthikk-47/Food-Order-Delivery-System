package com.foodapp.deliveryexecutive.admin.dto;

import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutiveAnalyticsDTO {
    private long totalExecutives;
    private long approved;
    private long pending;
    private List<DeliveryExecutive> executiveList;
}
