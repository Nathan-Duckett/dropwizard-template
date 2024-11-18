package com.companyname.template;

import com.companyname.template.resources.RootResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;

public class App extends Application<Config> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void run(Config config, Environment environment) throws Exception {
        environment.jersey().register(new RootResource());
    }
}
