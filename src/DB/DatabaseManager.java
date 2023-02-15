package DB;
import Data.Global;
import java.sql.*;

public class DatabaseManager
{
    public Connection Connect()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(Global.connectionString);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        return connection;
    }
}
