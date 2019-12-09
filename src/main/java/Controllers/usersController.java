package Controllers;

import Server.Main;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

@Path("users/")
public class usersController {
    @POST
    @Path("login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@FormDataParam("username") String username, @FormDataParam("password") String password){

        try{
            PreparedStatement ps1 = Main.db.prepareStatement("SELECT password FROM Users WHERE username = ?");
            ps1.setString(1, username);
            ResultSet loginResults = ps1.executeQuery();

            if (loginResults.next()){

                String correctPassword = loginResults.getString(1);

                if (password.equals(correctPassword)){
                    String token = UUID.randomUUID().toString();

                    PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET token = ? WHERE username = ?");
                    ps2.setString(1,token);
                    ps2.setString(2,username);
                    ps2.executeUpdate();

                    System.out.println(token);
                    return "{\"token\":"+token+"\"}";
                }else{
                    System.out.println("\n correct password: "+correctPassword+"\n password: "+password);
                    return "{\"error\": \"Incorrect password\"}";
                }
            }else{
                System.out.println("username error");
                return "{\"error\": \"User does not exist\"}";
            }
        }catch(Exception e){
            System.out.println("Server side error");
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }
}
