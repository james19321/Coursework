package Controllers;

import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("authors/")
public class authorsController{
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listThings(){
        JSONArray list = new JSONArray();
        try{
            PreparedStatement ps = Main.db.prepareStatement("SELECT AuthorId,Name,DoB,Country FROM Authors");
            ResultSet results = ps.executeQuery();
            while(results.next()) {
                JSONObject item = new JSONObject();
                int id = results.getInt(1);
                String name = results.getString(2);
                Date DoB = results.getDate(3);
                String country = results.getString(4);
                item.put("Id: ", id);
                item.put("Name: ", name);
                item.put("Date of birth: ", DoB);
                item.put("Country: ", country);
                list.add(item);
            }
            return(list.toString());
        } catch(Exception exception){
            System.out.println("Database error: "+exception.getMessage());
            return("{\"error\": \"Unable to get item, please see server console for more info.\"}");
        }
    }
}
/*public class authorsController {
    @Path("authors/list")
    public static void listThings() {
        try{
            PreparedStatement ps = Main.db.prepareStatement("SELECT AuthorId,Name,DoB,Country FROM Authors");

            ResultSet results = ps.executeQuery();
            while(results.next()){
                int id = results.getInt(1);
                String name = results.getString(2);
                Date DoB = results.getDate(3);
                String country = results.getString(4);
                System.out.println("Id: "+id);
                System.out.println("Name: "+name);
                System.out.println("Date of birth: "+DoB);
                System.out.println("Country: "+country);
            }
        } catch(Exception exception){
            System.out.println("Database error: "+exception.getMessage());
        }
    }

    public static void insertThing() {
        try{
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Authors (AuthorId, Name, DoB, Country) VALUES (?,?,?,?)");

        }catch(Exception exception){
            System.out.println("Database error: "+exception.getMessage());
        }
    }

    public static void updateThing() {
        try{
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Authors SET Name = ?, DoB = ?, Country = ? WHERE AuthorId= ?");

        }catch (Exception exception){
            System.out.println("Database error: "+exception.getMessage());
        }
    }
}*/
