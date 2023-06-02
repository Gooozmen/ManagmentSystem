package Repositories;

import Entities.Sale;
import ExceptionHandlers.SQLHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleRepository
{
    public void Print(Sale sale)
    {
        System.out.println("ID: " + sale.getSaleId());
        System.out.println("EVENT ID: " + sale.getEventId());
        System.out.println("LOCATION ID: " + sale.getLocationId());
        System.out.println("PRICE: " + sale.getPrice());
        System.out.println("SALE DATE: " + sale.getSaleDate());
        System.out.println("CUSTOMER DNI: " + sale.getCustomerDni());
    }

    public void PrintAll(List<Sale> sales)
    {
        for (Sale sale: sales)
        {
            Print(sale);
        }
    }

    public List<Sale> GetAll(Connection connection)
    {
        List<Sale> sales = new ArrayList<>();

        try (connection)
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM SALE ORDER BY SALEDATE DESC";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                int saleId = Integer.parseInt(resultSet.getString("SALEID"));
                int eventId = Integer.parseInt(resultSet.getString("EVENTID"));
                int locationId = Integer.parseInt(resultSet.getString("LOCATIONID"));
                int price = Integer.parseInt(resultSet.getString("PRICE"));
                String customerDni = resultSet.getString("BUYERID");
                Date saleDate = resultSet.getDate("SALEDATE");

                Sale sale = new Sale(saleId,eventId,locationId,price,customerDni,saleDate);
                sales.add(sale);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return sales;
    }

    public Sale GetBy(int identityNumber, Connection connection)
    {
        Sale sale = null;

        try (connection)
        {
            String query = "SELECT * FROM SALE WHERE SALEID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,identityNumber);
            ResultSet result = statement.executeQuery();

            while(result.next())
            {
                int saleId = Integer.parseInt(result.getString("SALEID"));
                int eventId = Integer.parseInt(result.getString("EVENTID"));
                int locationId = Integer.parseInt(result.getString("LOCATIONID"));
                int price = Integer.parseInt(result.getString("PRICE"));
                String customerDni = result.getString("BUYERID");
                Date saleDate = result.getDate("SALEDATE");

                sale = new Sale(saleId,eventId,locationId,price,customerDni,saleDate);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return sale;
    }

    public int GetSalesQuantityFrom(int locationId, Connection connection)
    {
        int saleQuantity = 0;

        try (connection)
        {
            String query = "SELECT COUNT(SALEID) AS QUANTITY FROM SALE WHERE LOCATIONID = ? ";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,locationId);
            ResultSet result = statement.executeQuery();

            while(result.next())
            {
                saleQuantity = Integer.parseInt(result.getString("QUANTITY"));
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return saleQuantity;
    }

    public int GetEarningsFrom(String minimunDate, String maximunDate, Connection connection)
    {
        int earnings = 0;

        try (connection)
        {
            String query = "SELECT SUM(PRICE) AS AMOUNT FROM SALE " +
                           "WHERE SALEDATE BETWEEN ? AND ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,minimunDate);
            statement.setString(2,maximunDate);
            ResultSet result = statement.executeQuery();
            while(result.next())
            {
                earnings = Integer.parseInt(result.getString("AMOUNT"));
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return earnings;
    }

    public Boolean Create(Sale sale,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "SET IDENTITY_INSERT SALE ON " +
                    "INSERT INTO SALE (SALEID,EVENTID,LOCATIONID,PRICE,CUSTOMERDNI,EVENTDATE) " +
                    "VALUES(?,?,?,?,?,?) " +
                    "SET IDENTITY_INSERT SALE OFF";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1,sale.getSaleId());
            statement.setInt(2,sale.getEventId());
            statement.setInt(3,sale.getLocationId());
            statement.setFloat(4,sale.getPrice());
            statement.setString(5,sale.getCustomerDni());
            statement.setDate(6, (java.sql.Date) sale.getSaleDate());

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

    public Boolean Delete(int saleId,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "DELETE SALE " +
                    "WHERE SALEID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,saleId);
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

    public Boolean DeleteBy(int eventId,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "DELETE SALE " +
                    "WHERE EVENTID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,eventId);
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

    public Boolean DeleteByLocation(int id,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "DELETE SALE " +
                    "WHERE LCATIONID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
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

    public Boolean UpdateSaleId(int currentId, int desiredId, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE SALE " +
                    "SET SALEID = ? " +
                    "WHERE SALEID = ?";

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

    public Boolean UpdateEventId(int saleId, int eventId, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE SALE " +
                    "SET EVENTID = ? " +
                    "WHERE SALEID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,eventId);
            statement.setInt(2,saleId);
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

    public Boolean UpdateLocationId(int saleId, int locationId, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE SALE " +
                    "SET LOCATIONID = ? " +
                    "WHERE SALEID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,locationId);
            statement.setInt(2,saleId);
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

    public Boolean UpdatePrice(int saleId, int price, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE SALE " +
                    "SET PRICE = ? " +
                    "WHERE SALEID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,price);
            statement.setInt(2,saleId);
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

    public Boolean UpdateCustomerDNI(int saleId, int dni, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE SALE " +
                    "SET CUSTOMERDNI = ? " +
                    "WHERE SALEID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,dni);
            statement.setInt(2,saleId);
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

    public Boolean UpdateSaleDate(int saleId, Date date, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE SALE " +
                    "SET CUSTOMERDNI = ? " +
                    "WHERE SALEID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, (java.sql.Date) date);
            statement.setInt(2,saleId);
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
