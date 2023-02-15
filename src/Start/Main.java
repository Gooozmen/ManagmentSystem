package Start;

import java.awt.*;
import java.sql.Connection;
import java.util.List;

import Components.Frame;
import DB.DatabaseManager;
import Entities.*;
import Pages.Login;
import Repositories.CustomerRepository;
import Repositories.EventRepository;
import Repositories.SaleRepository;

public class Main {
    public static void main(String[] args)
    {
        //database
        //DatabaseManager DBManager = new DatabaseManager();
        //Connection connection = DBManager.Connect();

        //repositories
        //CustomerRepository customerRepository = new CustomerRepository();
        //EventRepository eventRepository = new EventRepository();
        //SaleRepository saleRepository = new SaleRepository();

        //test customer CRUD
        //List<Customer> customers = customerRepository.GetAll(connection);
        //customerRepository.PrintAll(customers);

        //Customer customer = customerRepository.GetBy(9582454,connection);
        //customerRepository.Print(customer);

        //Customer _c ustomer = new Customer(95822454,"JULIANE","XSD123",2);
        //Boolean response = customerRepository.Create(_customer,connection);

        //Boolean response = customerRepository.Delete(20355465,connection);

        //List<Event> events = eventRepository.GetAll(connection);
        //eventRepository.PrintAll(events);

        //int response = saleRepository.GetEarningsFrom("2022-12-23", "2023-01-15",connection);

        //System.out.println(response);

        Login loginPage = new Login();

    }
}