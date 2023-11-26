package codenamex.smc.Database;
import java.sql.*;

//# Establish Connection
public class DatabaseManager extends Configs{
    public static Connection connectDB()
    {
        Connection connect = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

//            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata","root","smc_1234");
            String connectionString  = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
            connect = DriverManager.getConnection( connectionString,  dbUser,  dbPass);
//            return connect;
        }
        catch(ClassNotFoundException | SQLException e){e.printStackTrace();}
        return connect;
    }


}

//# WRITE
