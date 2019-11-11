package Controllers;

import Server.Main;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class authorsController {
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
}
