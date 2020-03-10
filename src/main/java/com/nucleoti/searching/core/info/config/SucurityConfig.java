package com.nucleoti.searching.core.info.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
//@ConditionalOnMissingBean
@SuppressWarnings("All")
public class SucurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SecurityProperties properties;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS)
        //      .hasRole("BASIC").and().formLogin();
        //super.configure(http);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        List<String> list = new ArrayList();
        list.addAll(properties.getDisabledFor());
        web.ignoring().antMatchers(HttpMethod.OPTIONS)
                .and().ignoring().antMatchers(list.toArray(new String[0]));
        // super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
}
