package com.companyname.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class Config extends Configuration {
    @JsonProperty
    private String environment;

    @JsonProperty
    private String sentryDsn;

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty
    private String graphiteUrl;

    @JsonProperty
    private String applicationName;

    @JsonProperty
    private String pyroscopeUrl;
    @JsonProperty
    private String pyroscopeUser;
    @JsonProperty
    private String pyroscopePass;

    public String getEnvironment() {
        return environment;
    }

    public String getSentryDsn() {
        return sentryDsn;
    }

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    public String getGraphiteUrl() {
        return graphiteUrl;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getPyroscopeUrl() {
        return pyroscopeUrl;
    }

    public String getPyroscopeUser() {
        return pyroscopeUser;
    }

    public String getPyroscopePass() {
        return pyroscopePass;
    }

}
