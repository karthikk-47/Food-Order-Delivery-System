package com.foodapp.deliveryexecutive.admin.dto;

import com.foodapp.deliveryexecutive.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnalyticsDTO {
    private long totalUsers;
    private List<User> userList;
}
