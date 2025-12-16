package com.foodapp.deliveryexecutive.order.service;

import com.foodapp.deliveryexecutive.order.entity.Order;

static class OrderSortingService.1 {
    static final /* synthetic */ int[] $SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderPriority;

    static {
        $SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderPriority = new int[Order.OrderPriority.values().length];
        try {
            OrderSortingService.1.$SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderPriority[Order.OrderPriority.HIGH.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OrderSortingService.1.$SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderPriority[Order.OrderPriority.MEDIUM.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OrderSortingService.1.$SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderPriority[Order.OrderPriority.LOW.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
