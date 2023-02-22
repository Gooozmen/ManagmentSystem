package Repositories;

import Entities.Location;
import ExceptionHandlers.SQLHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository
{
    public void Print(Location location)
    {
        System.out.println("ID: " + location.getLocationId());
        System.out.println("EVENT ID: " + location.getEventId());
        System.out.println("PRICE: " + location.getPrice());
        System.out.println("DESCRIPTION: " + location.getDescription());
        System.out.println("CAPACITY: " + location.getCapacity());
    }

    public void PrintAll(List<Location> locations)
    {
        for (Location location: locations)
        {
            Print(location);
        }
    }

    public List<Location> GetAll(Connection connection)
    {
        List<Location> locations = new ArrayList<>();

        try (connection)
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM LOCATION";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                int locationId = Integer.parseInt(resultSet.getString("LOCATIONID"));
                int eventId = Integer.parseInt(resultSet.getString("EVENTID"));
                float price = Float.parseFloat(resultSet.getString("PRICE"));
                String description = resultSet.getString("DESCRIPTION");
                int capacity = Integer.parseInt(resultSet.getString("CAPACITY"));

                Location location = new Location(locationId,eventId,price,description,capacity);

                locations.add(location);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return locations;
    }

    public Location GetBy(int identityNumber, Connection connection)
    {
        Location location = null;

        try (connection)
        {
            String query = "SELECT * FROM LOCATION WHERE LOCATIONID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,identityNumber);
            ResultSet result = statement.executeQuery();

            while(result.next())
            {
                int locationId = Integer.parseInt(result.getString("LOCATIONID"));
                int eventId = Integer.parseInt(result.getString("EVENTID"));
                float price = Float.parseFloat(result.getString("PRICE"));
                String description = result.getString("DESCRIPTION");
                int capacity = Integer.parseInt(result.getString("CAPACITY"));

                location = new Location(locationId,eventId,price,description,capacity);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return location;
    }

    public Boolean Create(Location location, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "SET IDENTITY_INSERT LOCATION ON " +
                    "INSERT INTO LOCATION (LOCATIONID, EVENTID, PRICE, DESCRIPTION, CAPACITY) " +
                    "VALUES(?,?,?,?,?) " +
                    "SET IDENTITY_INSERT LOCATION OFF";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1,location.getLocationId());
            statement.setInt(2, location.getEventId());
            statement.setFloat(3,location.getPrice());
            statement.setString(4,location.getDescription());
            statement.setInt(5,location.getCapacity());

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

    public Boolean Delete(int locationId,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            SaleRepository saleRepository = new SaleRepository();
            saleRepository.DeleteByLocation(locationId,connection);

            String query = "DELETE LOCATION " +
                    "WHERE LOCATIONID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,locationId);
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
