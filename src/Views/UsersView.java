package Views;

import java.util.List;
import Components.Label;
import Components.Panel;
import Components.Table;
import DB.DatabaseManager;
import Entities.Employee;
import Repositories.EmployeeRepository;

public class UsersView extends Panel{
    
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private String[] columns = {"DNI", "PASSWORD", "ACCESS TYPE"};
    private String[] accessTypes = {"undefined","Admin","Employee","Blocked"};

    public UsersView(int x, int y, int width, int height) {
        
        this.setSize(width, height);
        this.setBounds(x,y,width,height);

        List<Employee> employees = employeeRepository.GetAll(DatabaseManager.Connect());

        Object[][] data = parsearData(employees, columns.length);

        Table table = new Table(data, columns);
        
        Label labelTitle = new Label("Users", 10, 60, 150, 25);
        this.add(labelTitle);
        this.add(table);
    }

    private Object[][] parsearData(List<Employee> employees, int columns){

        Object[][] data = new Object[employees.size()][columns];
        
        for(int i = 0; i < employees.size(); i++){

            Employee userData = employees.get(i);

            Object[] user = {userData.getDni(), userData.getPassword(), accessTypes[userData.getAccessType()]};

            data[i] = user;
        }

        return data;
    }
    
}
