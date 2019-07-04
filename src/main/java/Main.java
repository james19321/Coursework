import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        openDatabase("Library.db");
    }

    public static Connection db = null;

    private static void openDatabase(String dbFile){

        try{

            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/"+dbFile, config.toProperties());
            System.out.println("Database connection successfully established");
        }catch (Exception exception){
            System.out.println("Database connection error: " + exception.getMessage());
        }
    }
}
