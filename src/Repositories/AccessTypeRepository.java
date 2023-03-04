package Repositories;

import Entities.AccessType;
import ExceptionHandlers.SQLHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AccessTypeRepository
{
    public void Print(AccessType accessType)
    {
        System.out.println("ACCESSID: " + accessType.getAccessId());
        System.out.println("DESCRIPTION: " + accessType.getDecription());
    }

    public void PrintAll(List<AccessType> accessTypes)
    {
        for (AccessType accessType: accessTypes)
        {
            Print(accessType);
        }
    }

    public List<AccessType> GetAll(Connection connection)
    {
        List<AccessType> accessTypes = new ArrayList<>();

        try (connection)
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM ACCESSTYPE";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                int accessID = Integer.parseInt(resultSet.getString("AccessID"));
                String description = resultSet.getString("Description");

                AccessType accessType = new AccessType(accessID, description);

                accessTypes.add(accessType);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return accessTypes;
    }

    public AccessType GetBy(int identityNumber, Connection connection)
    {
        AccessType accessType = null;

        try (connection)
        {
            String query = "SELECT * FROM ACCESSTYPE WHERE ACCESSID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,identityNumber);
            ResultSet result = statement.executeQuery();

            while(result.next())
            {
                int accessID = Integer.parseInt(result.getString("AccessID"));
                String description = result.getString("Description");

                accessType = new AccessType(accessID, description);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return accessType;
    }

    public Boolean Create(AccessType accessType,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "SET IDENTITY_INSERT ACCESSTYPE ON " +
                    "INSERT INTO ACCESSTYPE (ACCESSID,DESCRIPTION) " +
                    "VALUES(?,?) " +
                    "SET IDENTITY_INSERT ACCESSTYPE OFF";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1,accessType.getAccessId());
            statement.setString(2,accessType.getDecription());

            row = statement.executeUpdate();
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        if(row > 0) { return true; }
        else { return false; }
    }

    public Boolean Delete(int accessid,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            EmployeeRepository employeeRepository = new EmployeeRepository();

            String query = "DELETE ACCESSTYPE " +
                    "WHERE ACCESSID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,accessid);
            row = statement.executeUpdate();
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        if(row > 0) { return true; }
        else { return false; }
    }

    public Boolean UpdateAccessId(int currentId, int desiredId, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            EmployeeRepository employeeRepository = new EmployeeRepository();

            String query = "UPDATE ACCESSTYPE " +
                    "SET ACCESSID = ? " +
                    "WHERE ACCESSID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,desiredId);
            statement.setInt(2,currentId);
            row = statement.executeUpdate();
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        if(row > 0) { return true; }
        else { return false; }
    }

    public Boolean UpdateDescription(int id, String description, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE ACCESSTYPE " +
                    "SET DESCRIPTION = ? " +
                    "WHERE ACCESSID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,description);
            statement.setInt(2,id);
            row = statement.executeUpdate();
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        if(row > 0) { return true; }
        else { return false; }
    }
}
