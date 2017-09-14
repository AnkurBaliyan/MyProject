package com.sparktg;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LocalBean
@Singleton
@Startup
@Path("/")
@Produces({"application/json"})
public class MainClass {
    private static Logger log = LoggerFactory.getLogger(MainClass.class);

    @GET
    @Path("/ping")
    public String ping() {
        log.debug("ping method called");
        return "pong pong pong";
    }
}
