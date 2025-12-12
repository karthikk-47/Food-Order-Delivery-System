/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.core.convert.converter.Converter
 *  org.springframework.security.authentication.AbstractAuthenticationToken
 *  org.springframework.security.core.GrantedAuthority
 *  org.springframework.security.core.authority.SimpleGrantedAuthority
 *  org.springframework.security.oauth2.jwt.Jwt
 *  org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
 *  org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
 */
package com.foodapp.deliveryexecutive.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

public class CustomJwtAuthenticationConverter
implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection authorities = Stream.concat(this.defaultGrantedAuthoritiesConverter.convert(jwt).stream(), this.extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities, this.getPrincipalClaimName(jwt));
    }

    private String getPrincipalClaimName(Jwt jwt) {
        return (String)jwt.getClaim("preferred_username");
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map realmAccess = (Map)jwt.getClaim("realm_access");
        if (realmAccess == null || realmAccess.isEmpty()) {
            return Collections.emptyList();
        }
        Collection resourceRoles = (Collection)realmAccess.get("roles");
        if (resourceRoles == null) {
            return Collections.emptyList();
        }
        return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())).collect(Collectors.toList());
    }
}
