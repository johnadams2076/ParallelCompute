package com.ztech;

import com.ztech.model.Player;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/zt/pc")
public class RestyTest {

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/pl")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getPlayerList() {

        ParallelCompute.process();
        return ParallelCompute.getPlayersList();

    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/world")
    public String getMessageDetails() {
        return "Hello Jeff!";

    }


}