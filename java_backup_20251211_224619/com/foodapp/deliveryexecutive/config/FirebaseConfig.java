/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.auth.oauth2.GoogleCredentials
 *  com.google.firebase.FirebaseApp
 *  com.google.firebase.FirebaseOptions
 *  com.google.firebase.auth.FirebaseAuth
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 */
package com.foodapp.deliveryexecutive.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {
    @Value(value="${firebase.api.key}")
    private String firebaseApiKey;
    @Value(value="${firebase.project.id}")
    private String firebaseProjectId;
    @Value(value="${firebase.private.key}")
    private String firebasePrivateKey;
    @Value(value="${firebase.client.email}")
    private String firebaseClientEmail;

    @Bean
    @ConditionalOnProperty(name={"firebase.enabled"}, havingValue="true", matchIfMissing=false)
    public FirebaseAuth firebaseAuth() throws IOException {
        FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.getApplicationDefault()).setProjectId(this.firebaseProjectId).build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp((FirebaseOptions)options);
        }
        return FirebaseAuth.getInstance();
    }
}
