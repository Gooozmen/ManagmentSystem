package Pages;

import DB.DatabaseManager;
import Repositories.EmployeeRepository;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import Components.*;

public class LoginPage
{
    private Label userLabel;
    private Label passwordLabel;
    private Panel panel;
    private TextField userText;
    private TextField passwordText;
    private Button loginButton;
    private Label outputMessage;
    private Integer fieldWidth = 210;
    private Integer fieldHeight = 25;
    private boolean visible = true;
    EmployeeRepository employeeRepository;



    public LoginPage()
    {
        employeeRepository = new EmployeeRepository();
        
        panel = new Panel();

        Frame frame = new Frame(300, 450, panel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        userLabel = new Label("Username",40,45,80,25);
        panel.add(userLabel);
        
        userText = new TextField(120,45,210,25);
        panel.add(userText);
        
        passwordLabel = new Label("Password",40,85,80,25);
        panel.add(passwordLabel);
        
        passwordText = new TextField(120,85, fieldWidth, fieldHeight);
        panel.add(passwordText);
        
        outputMessage = new Label("MESSAGE",190,160, fieldWidth, fieldHeight);
        panel.add(outputMessage);
        
        loginButton = new Button("LOGIN",120,120, fieldWidth, fieldHeight);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String DNI = userText.getText();
                String password = passwordText.getText();
                
                Connection connection = DatabaseManager.Connect();
                
                int response = employeeRepository.Login(DNI, password, connection);
                String message = OutputResponseAssignation(response);
                outputMessage.setText(message);

                if(response == 1)
                {
                    visible = false;
                    frame.dispose();
                    AdminPage adminPage = new AdminPage();
                }
                if(response == 2)
                {
                    visible = false;
                    frame.dispose();
                }
            }
        });

        panel.add(loginButton);
        panel.repaint();
    }

    public String OutputResponseAssignation(int responseNumber)
    {
        if(responseNumber == 1)
        {
            return "LOGIN SUCCESSFUL";
        }
        else if (responseNumber == -1)
        {
            return "INVALID CREDENTIALS";
        }
        else if (responseNumber == 0)
        {
            return "CREATE AN ACCOUNT";
        }
        else
        {
            return "SOMETHING TERRIBLE HAPPENED";
        }
    }
}
