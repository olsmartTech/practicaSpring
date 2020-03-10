package com.nucleoti.searching.core.info.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.inretailpharma.digital.ordertracker.firebase.exception.FirebaseException;
//import com.inretailpharma.digital.ordertracker.firebase.service.FirebaseService;
import com.nucleoti.searching.core.info.firebase.FirebaseService;
import com.nucleoti.searching.core.info.firebase.exception.FirebaseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

@Configuration
@SuppressWarnings("all")
//@ConditionalOnMissingBean(FirebaseConfig.class)
@ConditionalOnProperty("firebase.system.config.service-account")
@EnableConfigurationProperties(FirebaseProperties.class)
public class FirebaseConfig {

    private final ApplicationContext context;
    private final FirebaseProperties properties;

    public FirebaseConfig(final ApplicationContext context, final FirebaseProperties properties) {
        this.context = context;
        this.properties = properties;

        final String ln = System.getProperty("line.separator");
        Logger logger = LoggerFactory.getLogger(getClass());
    }

    @PostConstruct
    public void initializedFirebase() {
        try {
            Resource resource = context.getResource(properties.getServiceAccount());
            FirebaseOptions.Builder firebaseBuilder = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(resource.getInputStream()));
            if (StringUtils.isNotBlank(properties.getDatabaseUrl())) {
                firebaseBuilder = firebaseBuilder.setDatabaseUrl(properties.getDatabaseUrl());
            }
            FirebaseOptions options = firebaseBuilder.build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new FirebaseException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Bean
    public FirebaseAuth firebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Bean
    @ConditionalOnProperty("firebase.system.config.database-url")
    public DatabaseReference firebaseDatabase() {
        return FirebaseDatabase.getInstance().getReference();
    }

    /*@Bean
    @ConditionalOnBean(DatabaseReference.class)
    public FirebaseService firebaseService() {
        return new FirebaseService(firebaseDatabase(), firebaseAuthToken());
    }*/

    @Bean
    @ConditionalOnBean(DatabaseReference.class)
    public FirebaseService firebaseService() {
        return new FirebaseService();
    }

    @Bean
    public GoogleCredential firebaseAuthToken() {
        GoogleCredential scoped = null;
        try {
            Resource resource = context.getResource(properties.getServiceAccount());
            scoped = GoogleCredential.fromStream(resource.getInputStream())
                    .createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.database",
                            "https://www.googleapis.com/auth/userinfo.email"));

        } catch (IOException e) {
            throw new FirebaseException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return scoped;
    }

}