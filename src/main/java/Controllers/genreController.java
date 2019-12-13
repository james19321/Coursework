package Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("genre/")
public class genreController {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listThings(){

        return "";
    }
}
