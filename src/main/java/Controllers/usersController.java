package Controllers;

import Server.Main;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

@Path("users/")
public class usersController {

    //login api
    @POST
    @Path("login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@FormDataParam("username") String username, @FormDataParam("password") String password) {

        try {
            PreparedStatement ps1 = Main.db.prepareStatement("SELECT password FROM Users WHERE username = ?");
            ps1.setString(1, username);
            ResultSet loginResults = ps1.executeQuery();

            //checks if user exists
            if (loginResults.next()) {

                String correctPassword = loginResults.getString(1);
                //checks if password sent by the HTTP request is correct and generates a random token
                if (password.equals(correctPassword)) {
                    String token = UUID.randomUUID().toString();

                    PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET token = ? WHERE username = ?");
                    ps2.setString(1, token);
                    ps2.setString(2, username);
                    ps2.executeUpdate();

                    JSONObject userDetails = new JSONObject();
                    userDetails.put("username", username);
                    userDetails.put("token", token);
                    return userDetails.toString();

                } else {
                    return "{\"error\": \"Incorrect password\"}";
                }
            } else {
                System.out.println("username error");
                return "{\"error\": \"User does not exist\"}";
            }
        } catch (Exception e) {
            System.out.println("Server side error");
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }


    @POST
    @Path("logout")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String logoutUser(@CookieParam("token") String token) {
        try {
            System.out.println("user/logout");

            PreparedStatement ps1 = Main.db.prepareStatement("SELECT userid FROM Users WHERE token = ?");
            ps1.setString(1, token);
            ResultSet logoutResults = ps1.executeQuery();
            if (logoutResults.next()) {

                int id = logoutResults.getInt(1);

                PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET Token = NULL WHERE UserID = ?");
                ps2.setInt(1, id);
                ps2.executeUpdate();
                System.out.println("status: ok");
                return "{\"status\": \"OK\"}";

            } else {
                return "{\"error\": \"Invalid token!\"}";
            }
        } catch (Exception exception) {
            System.out.println("Database error during /user/logout: " + exception.getMessage());
            return "{\"error\": \"Server side error!\"}";

        }
    }

    public static boolean validToken(String token){
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID FROM Users WHERE Token = ?");
            ps.setString(1, token);
            ResultSet logoutResults = ps.executeQuery();
            return logoutResults.next();
        } catch (Exception exception) {
            System.out.println("Database error during /user/logout: " + exception.getMessage());
            return false;
        }
    }

}