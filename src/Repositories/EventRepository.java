package Repositories;

import Entities.Event;
import ExceptionHandlres.SQLHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventRepository
{
    public void Print(Event event)
    {
        System.out.println("ID: " + event.getEventId());
        System.out.println("NAME: " + event.getName());
        System.out.println("DATE: " + event.getEventDate());
    }

    public void PrintAll(List<Event> events)
    {
        for (Event event: events)
        {
            Print(event);
        }
    }

    public List<Event> GetAll(Connection connection)
    {
        List<Event> events = new ArrayList<>();

        try (connection)
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM EVENT";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                int id = Integer.parseInt(resultSet.getString("EventID"));
                String name = resultSet.getString("Name");
                Date date = resultSet.getDate("EventDate");

                Event event = new Event(id,name,date);

                events.add(event);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return events;
    }

    public Event GetBy(int identityNumber, Connection connection)
    {
        Event event = null;

        try (connection)
        {
            String query = "SELECT * FROM EVENT WHERE EVENTID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,identityNumber);
            ResultSet result = statement.executeQuery();

            while(result.next())
            {

                int id = Integer.parseInt(result.getString("EventID"));
                String name = result.getString("Name");
                Date date = result.getDate("EventDate");

                event = new Event(id,name,date);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return event;
    }

    public Boolean Create(Event event,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "SET IDENTITY_INSERT EVENT ON " +
                    "INSERT INTO EVENT (EVENTID,NAME,EVENTDATE) " +
                    "VALUES(?,?,?) " +
                    "SET IDENTITY_INSERT EVENT OFF";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1,event.getEventId());
            statement.setString(2,event.getName());
            statement.setDate(3, (java.sql.Date)event.getEventDate());

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

    public Boolean Delete(int identityNumber,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            //borrar dependencias del evento en las ventas

            SaleRepository saleRepository = new SaleRepository();
            saleRepository.DeleteBy(identityNumber, connection);

            String query = "DELETE EVENT " +
                    "WHERE EVENTID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,identityNumber);
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

    public Boolean UpdateId(int currentId, int desiredId, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE EVENT " +
                    "SET  EVENTID = ? " +
                    "WHERE EVENTID = ?";

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

    public Boolean UpdateName(int id, String desiredName, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE EVENT " +
                    "SET  NAME = ? " +
                    "WHERE EVENTID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,desiredName);
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

    public Boolean UpdateDate(int id, Date desiredDate, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE EVENT " +
                    "SET  EVENTDATE = ? " +
                    "WHERE EVENTID= ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, (java.sql.Date) desiredDate);
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
