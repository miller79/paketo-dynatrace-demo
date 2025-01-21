package project;

import lombok.extern.slf4j.Slf4j;
import project.dto.FailurePercentagesDTO;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import jakarta.ws.rs.core.Response;
import java.util.Random;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/load")
@Slf4j
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class FlakyResource {

    @ConfigProperty(name = "failure.percentage", defaultValue = "0")
    private int failurePercentage;

    @ConfigProperty(name = "unavailable.percentage", defaultValue = "0")
    private int unavailablePercentage;

    @GET
    @Path("/{loadNumber}")
    public Response generateResponse(@PathParam String loadNumber) {
        var rng = new Random();
        var failureRoll =  rng.nextInt(100);

        if (failureRoll < unavailablePercentage){
            log.error("Service is unavailable!");
            return Response.status(503).build();
        }

        if (failureRoll < failurePercentage){
            log.error("Something bad happened");
            return Response.status(500).build();
        }

        return Response.ok(true).build();
    }


    @POST
    public Response setVariables(FailurePercentagesDTO failurePercentagesDTO){
        this.failurePercentage = failurePercentagesDTO.getFailurePercentage();
        this.unavailablePercentage = failurePercentagesDTO.getUnavailablePercentage();
        return Response.ok("Updated variables").build();
    }
}
