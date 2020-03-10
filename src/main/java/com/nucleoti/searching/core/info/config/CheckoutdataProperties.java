package com.nucleoti.searching.core.info.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("all")
@ConfigurationProperties(prefix = "checkoutdata.properties.urlorigen",ignoreInvalidFields = true)
public class CheckoutdataProperties {
    private String sunatUrl;
    private String reniecUrl;

    public String getSunatUrl() {
        return sunatUrl;
    }

    public void setSunatUrl(String sunatUrl) {this.sunatUrl = sunatUrl;
    }

    public String getReniecUrl() {
        return reniecUrl;
    }

    public void setReniecUrl(String reniecUrl) {
        this.reniecUrl = reniecUrl;
    }
}
