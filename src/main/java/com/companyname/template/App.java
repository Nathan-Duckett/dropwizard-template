package com.companyname.template;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.google.inject.Injector;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.pyroscope.http.Format;
import io.pyroscope.javaagent.EventType;
import io.pyroscope.javaagent.PyroscopeAgent;
import io.sentry.Sentry;
import ru.vyarus.dropwizard.guice.GuiceBundle;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class App extends Application<Config> {

    private Injector injector;

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false)));

        // Guice DI support for Dropwizard
        var guiceBundle = GuiceBundle.builder()
                .enableAutoConfig()
                .build();
        bootstrap.addBundle(guiceBundle);

        // Swagger configuration
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(Config configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }


    @Override
    public void run(Config config, Environment environment) throws Exception {
        Sentry.init(options -> {
            options.setEnabled(config.getSentryDsn() != null);
            options.setDsn(config.getSentryDsn());
            options.setEnvironment(config.getEnvironment());
        });

        PyroscopeAgent.start(
                new io.pyroscope.javaagent.config.Config.Builder()
                        .setApplicationName(config.getApplicationName())
                        .setProfilingEvent(EventType.ITIMER)
                        .setFormat(Format.JFR)
                        .setServerAddress(config.getPyroscopeUrl())
                        .setBasicAuthUser(config.getPyroscopeUser())
                        .setBasicAuthPassword(config.getPyroscopePass())
                        .build()
        );
    }
}
