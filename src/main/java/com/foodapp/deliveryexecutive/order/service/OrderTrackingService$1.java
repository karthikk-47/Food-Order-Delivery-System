package com.foodapp.deliveryexecutive.order.service;

import com.foodapp.deliveryexecutive.order.entity.Order;

static class OrderTrackingService.1 {
    static final /* synthetic */ int[] $SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderStatus;

    static {
        $SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderStatus = new int[Order.OrderStatus.values().length];
        try {
            OrderTrackingService.1.$SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderStatus[Order.OrderStatus.PENDING.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OrderTrackingService.1.$SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderStatus[Order.OrderStatus.PREPARING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OrderTrackingService.1.$SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderStatus[Order.OrderStatus.PREPARED.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OrderTrackingService.1.$SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderStatus[Order.OrderStatus.ACCEPTED.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OrderTrackingService.1.$SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderStatus[Order.OrderStatus.OUTFORDELIVERY.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OrderTrackingService.1.$SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderStatus[Order.OrderStatus.DELIVERED.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OrderTrackingService.1.$SwitchMap$com$foodapp$deliveryexecutive$order$entity$Order$OrderStatus[Order.OrderStatus.CANCELLED.ordinal()] = 7;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
