package com.foodapp.deliveryexecutive.security;

import com.foodapp.deliveryexecutive.common.entity.Actor;

static class CustomUserDetailsService.1 {
    static final /* synthetic */ int[] $SwitchMap$com$foodapp$deliveryexecutive$common$entity$Actor$Role;

    static {
        $SwitchMap$com$foodapp$deliveryexecutive$common$entity$Actor$Role = new int[Actor.Role.values().length];
        try {
            CustomUserDetailsService.1.$SwitchMap$com$foodapp$deliveryexecutive$common$entity$Actor$Role[Actor.Role.USER.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            CustomUserDetailsService.1.$SwitchMap$com$foodapp$deliveryexecutive$common$entity$Actor$Role[Actor.Role.HOMEMAKER.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            CustomUserDetailsService.1.$SwitchMap$com$foodapp$deliveryexecutive$common$entity$Actor$Role[Actor.Role.DELIVERYEXECUTIVE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            CustomUserDetailsService.1.$SwitchMap$com$foodapp$deliveryexecutive$common$entity$Actor$Role[Actor.Role.ADMIN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
