package com.foodapp.deliveryexecutive.admin.dto;

import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomemakerAnalyticsDTO {
    private long totalHomemakers;
    private long approved;
    private long pending;
    private long rejected;
    private List<HomeMaker> homemakerList;
}
