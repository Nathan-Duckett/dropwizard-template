package com.companyname.template.resources;

import com.companyname.template.Config;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class RootResource {

    @GET
    public String sayHello() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 200));
        return "Hello World";
    }
}
