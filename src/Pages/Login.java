package Pages;

import DB.DatabaseManager;
import Repositories.CustomerRepository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import Components.*;

public class Login
{
    private String frameTitle = "Ticket Sales System";
    private Frame frame;
    private Label userLabel;
    private Label passwordLabel;
    private Panel panel;
    private TextField userText;
    private PasswordField passwordText;
    private Button loginButton;
    private Button registerButton;
    private Label outputMessage;

    public Login(DatabaseManager dbManager, CustomerRepository customerRepository)
    {
        panel = new Panel();
        frame = new Frame(300, 450,panel);

        userLabel = new Label("Username",40,45,80,25);
        panel.add(userLabel);

        userText = new TextField(120,45,210,25);
        panel.add(userText);

        passwordLabel = new Label("Password",40,85,80,25);
        panel.add(passwordLabel);

        passwordText = new PasswordField(120,85,210,25);
        panel.add(passwordText);

        outputMessage = new Label("MESSAGE",190,160,150,25);
        panel.add(outputMessage);

        loginButton = new Button("LOGIN",120,120,100,25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int DNI = Integer.parseInt(userText.getText());
                String password = passwordText.getText();

                Connection connection = dbManager.Connect();

                int response = customerRepository.Login(DNI, password, connection);
                String message = OutputResponseAssignation(response);
                outputMessage.setText(message);
            }
        });
        panel.add(loginButton);

        registerButton = new Button("Register",230,120,100,25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Register();
            }
        });
        panel.add(registerButton);
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
