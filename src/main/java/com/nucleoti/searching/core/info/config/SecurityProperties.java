package com.nucleoti.searching.core.info.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@ConfigurationProperties(prefix = "aprendiendo.security",ignoreInvalidFields = true)
public class SecurityProperties {

    private List<String> disabledFor = new ArrayList<>(0);
    //private List<String> adminEndpoints = new ArrayList<>(0);
    //private List<Class<? extends HeaderProvider>> validateHeader = new ArrayList<>(0);


    public List<String> getDisabledFor() {
        return disabledFor;
    }

    public void setDisabledFor(List<String> disabledFor) {
        this.disabledFor = disabledFor;
    }
}
