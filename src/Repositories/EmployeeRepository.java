package Repositories;

import DB.DatabaseManager;
import Entities.Employee;
import ExceptionHandlers.SQLHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository
{
    DatabaseManager db;
    public EmployeeRepository()
    {
        this.db = new DatabaseManager();
    }
    public EmployeeRepository(DatabaseManager db)
    {
        this.db = db;
    }

    public void Print(Employee employee)
    {
        System.out.println("DNI: " + employee.getDni());
        System.out.println("PASSWORD: " + employee.getPassword());
        System.out.println("ACCESSID: " + employee.getAccessType());
    }

    public void PrintAll(List<Employee> employees)
    {
        for (Employee employee : employees)
        {
            Print(employee);
        }
    }
    public List<Employee> GetAll(Connection connection)
    {
        List<Employee> employees = new ArrayList<>();

        try (connection)
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM EMPLOYEE";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                String dni = resultSet.getString("DNI");
                String password = resultSet.getString("PASSWORD");
                int accessType = Integer.parseInt(resultSet.getString("ACCESSID"));

                Employee employee = new Employee(dni,password,accessType);

                employees.add(employee);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return employees;
    }

    public Employee GetBy(String identityNumber, Connection connection)
    {
        Employee employee = null;

        try (connection)
        {
            String query = "SELECT * FROM EMPLOYEE WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,identityNumber);
            ResultSet result = statement.executeQuery();

            while(result.next())
            {
                String dni = result.getString("DNI");
                String password = result.getString("Password");
                int accessType = Integer.parseInt(result.getString("AccessID"));

                employee = new Employee(dni,password,accessType);
            }
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        return employee;
    }

    public Employee Create(Employee employee, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "INSERT INTO EMPLOYEE (DNI,PASSWORD,ACCESSID) " +
                           "VALUES(?,?,?)";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, employee.getDni());
            statement.setString(2, employee.getPassword());
            statement.setInt(3, employee.getAccessType());

            row = statement.executeUpdate();
        }
        catch (SQLException e)
        {
            SQLHandler sqlHandler = new SQLHandler();
            sqlHandler.ShowSQLException(e);
        }
        if(row > 0) { return employee; }
        else { return null; }
    }

    public boolean Delete(String dni,Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "DELETE EMPLOYEE " +
                           "WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,dni);
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

    public Boolean UpdateDNI(String currentDNI, String desiredDNI, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE EMPLOYEE " +
                           "SET DNI = ? " +
                           "WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,desiredDNI);
            statement.setString(2,currentDNI);
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

    public Boolean UpdatePassword(String dni, String password, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE EMPLOYEE " +
                    "SET PASSWORD = ? " +
                    "WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,password);
            statement.setString(2,dni);
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

    public Boolean UpdateAccessId(String dni, int accessID, Connection connection)
    {
        int row = 0;

        try (connection)
        {
            String query = "UPDATE EMPLOYEE " +
                    "SET ACCESSID = ? " +
                    "WHERE DNI = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,accessID);
            statement.setString(2,dni);
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

    public int Login(String dni, String password, Connection connection)
    {
        Employee employee = GetBy(dni, connection);
        if(employee != null)
        {
            if(ValidateCredentials(employee.getPassword(),password))
            {
                //SUCCESSFUL
                return 1;
            }
            else
            {
                //INVALID CREDENTIALS
                return -1;
            }
        }
        else
        {
            //CREATE AN ACCOUNT
            return 0;
        }
    }

    public boolean ValidateCredentials(String validPassword,String actualPassword)
    {
        if(validPassword.equals(actualPassword))
        {
            return true;
        }
        else {
            return false;
        }
    }
}
