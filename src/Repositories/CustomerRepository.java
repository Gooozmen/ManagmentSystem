package Repositories;

import Entities.Customer;
import ExceptionHandlres.SQLHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository
{
    public void Print(Customer customer)
    {
        System.out.println("DNI: " + customer.getDni());
        System.out.println("NAME: " + customer.getName());
        System.out.println("PASSWORD: " + customer.getPassword());
        System.out.println("ACCESSID: " + customer.getAccessType());
    }

    public void PrintAll(List<Customer> customers)
    {
        for (Customer customer: customers)
        {
            Print(customer);
        }
    }
    public List<Customer> GetAll(Connection connection)
    {
        List<Customer> customers = new ArrayList<>();

        try (connection)
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM CUSTOMER";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                int dni = Integer.parseInt(resultSet.getString("DNI"));
                String name = resultSet.getString("Name");
                String password = resultSet.getString("Password");
                int accessType = Integer.parseInt(resultSet.getString("AccessID"));

                Customer customer = new Customer(dni,name,password,accessType);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return customers;
    }

    public Customer GetBy(int identityNumber, Connection connection)
    {
        Customer customer = null;

        try (connection)
        {
            String query = "SELECT * FROM CUSTOMER WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,identityNumber);
            ResultSet result = statement.executeQuery();

            while(result.next())
            {
                int dni = Integer.parseInt(result.getString("DNI"));
                String name = result.getString("Name");
                String password = result.getString("Password");
                int accessType = Integer.parseInt(result.getString("AccessID"));

                customer = new Customer(dni,name,password,accessType);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return customer;
    }

    public Boolean Create(Customer customer,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "INSERT INTO CUSTOMER (DNI,NAME,PASSWORD,ACCESSID) " +
                           "VALUES(?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1,customer.getDni());
            statement.setString(2,customer.getName());
            statement.setString(3,customer.getPassword());
            statement.setInt(4,customer.getAccessType());

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

    public Boolean Delete(int dni,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "DELETE CUSTOMER " +
                           "WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,dni);
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

    public Boolean UpdateDNI(int currentDNI, int desiredDNI, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE CUSTOMER " +
                           "SET DNI = ? " +
                           "WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,desiredDNI);
            statement.setInt(2,currentDNI);
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

    public Boolean UpdateName(int currentName, int desiredName, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE CUSTOMER " +
                    "SET DNI = ? " +
                    "WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,desiredName);
            statement.setInt(2,currentName);
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

    public Boolean UpdatePassword(int currentPassword, int desiredPassword, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE CUSTOMER " +
                    "SET DNI = ? " +
                    "WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,desiredPassword);
            statement.setInt(2,currentPassword);
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
            String query = "UPDATE CUSTOMER " +
                    "SET DNI = ? " +
                    "WHERE DNI = ?";

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

    public Boolean UpdatePermission(int accessid,int desiredid, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE CUSTOMER " +
                    "SET ACCESSID = ? " +
                    "WHERE  ACCESSID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,accessid);
            statement.setInt(2,desiredid);

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

    public boolean Login(int dni, String password, Connection connection)
    {
        int validDNI = 0;
        String validPassword = "";

        try(connection)
        {
            String query = "SELECT PASSWORD FROM CUSTOMER WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,dni);
            ResultSet result = statement.executeQuery();

            while(result.next())
            {
                validDNI = Integer.parseInt(result.getString("DNI"));
                validPassword = result.getString("Password");
            }

        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }

        if(ValidateCredentials(validDNI,validPassword,dni,password))
        {
            System.out.println("WELCOME");
            return true;
        }
        else
        {
            System.out.println("INVALID CREDENTIALS");
            return false;
        }
    }

    public boolean ValidateCredentials(int validDNI, String validPassword, int actualDNI, String actualPassword)
    {
        if(validDNI==actualDNI && validPassword == actualPassword)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
