package Start;

import java.sql.Connection;
import DB.DatabaseManager;
import Pages.*;
import Repositories.*;

public class Main {
    public static void main(String[] args)
    {
        //database
        DatabaseManager DBManager = new DatabaseManager();
        Connection connection = DBManager.Connect();

        //page
        Login login = new Login();
    }
}