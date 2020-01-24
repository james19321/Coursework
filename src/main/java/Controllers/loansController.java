package Controllers;

import Server.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;

@Path("loans/")
public class loansController {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listThings(){
        try{
            PreparedStatement ps1 = Main.db.prepareStatement("SELECT * FROM Loans WHERE token = ?");
            ps1.setString(1, "cookie");

        }catch(Exception e){
            return "{\"error\": \""+e+"\"}";
        }
        return "";
    }
}

