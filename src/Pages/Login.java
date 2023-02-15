package Pages;


import javax.swing.*;

public class Login
{
    JFrame frame;
    JLabel userLabel;
    JLabel passwordLabel;
    JPanel panel;
    JTextField userText;
    JPasswordField passwordText;

    public Login()
    {
        panel = new JPanel();
        panel.setLayout(null);

        frame = new JFrame("Ticket Sales System");
        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(panel);

        userLabel = new JLabel("Username");
        userLabel.setBounds(40,45,80,25);

        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(120,45,165,25);

        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40,85,80,25);

        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(120,85,165,25);

        panel.add(passwordText);
        panel.repaint();
    }
}
