package Pages;

import Components.*;
public class Register
{
    private Frame frame;
    private Label createAccountLabel;
    private Label nameLabel;
    private Label usernameLabel;
    private Label passwordLabel;
    private Panel panel;
    private TextField nameText;
    private TextField usernameText;
    private PasswordField passwordText;
    private Button backToLoginButton;
    private Button submitButton;
    private Label outputMessage;
    public Register()
    {
        panel = new Panel();
        frame = new Frame(350, 450,panel);

        createAccountLabel = new Label("INSERT ACCOUNT INFORMATION",130,10, 400, 25);
        panel.add(createAccountLabel);

        usernameLabel = new Label("USERNAME", 50, 55, 80,25);
        panel.add(usernameLabel);

        usernameText = new TextField(140, 55, 210, 25);
        panel.add(usernameText);

        nameLabel = new Label("FULL NAME", 50, 100, 80, 25);
        panel.add(nameLabel);

        nameText = new TextField(140, 100, 210, 25);
        panel.add(nameText);

        passwordLabel = new Label("PASSWORD", 50, 150, 210, 25);
        panel.add(passwordLabel);

        passwordText = new PasswordField(140, 150,210, 25);
        panel.add(passwordText);

        outputMessage = new Label("MESSAGE", 140, 250, 210, 25);
        panel.add(outputMessage);

        submitButton = new Button("SUBMIT", 140, 200,95,25);
        panel.add(submitButton);

        backToLoginButton = new Button("BACK",255, 200, 95, 25);
        panel.add(backToLoginButton);
    }
}
