package com.companyname.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;

public class Config extends Configuration {
    @JsonProperty
    private String environment;

    public String getEnvironment() {
        return environment;
    }

}
