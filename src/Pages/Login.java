package Pages;

import Repositories.CustomerRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import Components.*;

public class Login
{
    String frameTitle = "Ticket Sales System";
    Label userLabel;
    Label passwordLabel;
    Panel panel;
    TextField userText;
    PasswordField passwordText;
    Button loginButton;
    Button registerButton;

    public Login(Connection connection, CustomerRepository customerRepository)
    {
        panel = new Panel();
        new Frame(frameTitle, 450, 200,panel);

        userLabel = new Label("Username",40,45,80,25);
        panel.add(userLabel);

        userText = new TextField(120,45,210,25,20);
        panel.add(userText);

        passwordLabel = new Label("Password",40,85,80,25);
        panel.add(passwordLabel);

        passwordText = new PasswordField(120,85,210,25);
        panel.add(passwordText);


        loginButton = new Button("LOGIN",120,120,100,25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int DNI = Integer.parseInt(userText.getText());
                String password = passwordText.getText();

                int response = customerRepository.Login(DNI, password, connection);
                if(response != 1)
                {
                    panel.repaint();
                }
            }
        });


        registerButton = new Button("Register",230,120,100,25);
        panel.add(registerButton);

        panel.repaint();
    }
}
