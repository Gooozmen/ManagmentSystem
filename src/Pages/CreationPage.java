package Pages;

import Components.*;
import DB.DatabaseManager;
import Entities.Employee;
import Entities.Event;
import Repositories.EmployeeRepository;
import Repositories.EventRepository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.util.Date;

public class CreationPage {

    private Frame frame;
    private NavBar navBar;
    private Panel containerPanel;
    private Label dniLabel;
    private Label passwordLabel;
    private Label creationLabel;
    private Label accessLabel;
    private TextField dniText;
    private TextField passwordText;
    private TextField accessText;
    private Button userSubmitButton;
    private Label eventNameLabel;
    private TextField eventNameText;
    private Label eventDateLabel;
    private TextField eventDateText;
    private Button eventSubmitButton;
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private EventRepository eventRepository = new EventRepository();

    public CreationPage()
    {
        containerPanel = new Panel();
        frame = new Frame(550, 400, containerPanel);

        setNavBar();
        setUserFields();

        containerPanel.repaint();
    }

    private void removeUserFields()
    {
        containerPanel.remove(creationLabel);
        containerPanel.remove(dniLabel);
        containerPanel.remove(dniText);
        containerPanel.remove(passwordLabel);
        containerPanel.remove(passwordText);
        containerPanel.remove(accessLabel);
        containerPanel.remove(accessText);
        containerPanel.remove(accessLabel);
        containerPanel.remove(userSubmitButton);
    }

    public void removeEventFields()
    {
        containerPanel.remove(creationLabel);
        containerPanel.remove(eventNameLabel);
        containerPanel.remove(eventDateText);
        containerPanel.remove(eventDateLabel);
        containerPanel.remove(eventDateText);
        containerPanel.remove(eventSubmitButton);
    }

    private void setUserFields()
    {
        creationLabel = new Label("EMPLOYEE INFORMATION",50,60, 400, 25);
        containerPanel.add(creationLabel);

        dniLabel = new Label("DNI", 50, 100, 80,25);
        containerPanel.add(dniLabel);

        dniText = new TextField(50, 125, 210, 25);
        containerPanel.add(dniText);

        passwordLabel = new Label("PASSWORD", 50, 150, 210, 25);
        containerPanel.add(passwordLabel);

        passwordText = new TextField(50, 175,210, 25);
        containerPanel.add(passwordText);

        accessLabel = new Label("ACCESS ID", 50, 200, 80,25);
        containerPanel.add(accessLabel);

        accessText = new TextField(50, 225, 210, 25);
        containerPanel.add(accessText);

        userSubmitButton = new Button("SUBMIT USER", 50, 275,120,25);
        containerPanel.add(userSubmitButton);

        userSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String DNI = dniText.getText();
                String password = passwordText.getText();
                int accessId = Integer.parseInt(accessText.getText());

                Employee employee = new Employee(DNI,password,accessId);

                Connection connection = DatabaseManager.Connect();

                Employee response = employeeRepository.Create(employee,connection);

                if(response != null)
                {
                    dniText.setText("");
                    passwordText.setText("");
                    accessText.setText("");

                    containerPanel.repaint();
                }
            }
        });
    }

    private void setEventFields()
    {
        creationLabel = new Label("EVENT INFORMATION",50,60, 400, 25);
        containerPanel.add(creationLabel);

        eventNameLabel = new Label("Event Name", 50, 100, 80,25);
        containerPanel.add(eventNameLabel);

        eventNameText = new TextField(50, 125, 210, 25);
        containerPanel.add(eventNameText);

        eventDateLabel = new Label("Event Date: yyyy-mm-dd", 50, 150, 210, 25);
        containerPanel.add(eventDateLabel);

        eventDateText = new TextField(50, 175,210, 25);
        containerPanel.add(eventDateText);

        eventSubmitButton = new Button("SUBMIT EVENT", 50, 205,120,25);
        containerPanel.add(eventSubmitButton);
        eventSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String eventName = eventNameText.getText();
                Date eventDate = parseStringToDate(eventDateText.getText());

                if(eventDate != null)
                {
                    Event event = new Event(eventName,eventDateText.getText());
                    Connection connection = DatabaseManager.Connect();
                    Boolean response = eventRepository.Create(event,connection);

                    if(response != null)
                    {
                        eventNameText.setText("");
                        eventDateText.setText("");

                        containerPanel.repaint();
                    }
                }
            }
        });
    }

    private Date parseStringToDate(String dateString)
    {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date = sdf.parse(dateString);
            System.out.println("Parsed date: " + date);

            return date;
        } catch (ParseException e) {
            System.out.println("Failed to parse the date.");
            e.printStackTrace();
        }
        return null;
    }

    private void setNavBar(){

        ArrayList<Button> listButtons = new ArrayList();

        int buttonHeight = 30;

        Button homeButton = new Button("HOME", 0, 10, 100, buttonHeight);
        Button createUserButton = new Button("Create User", 100, 10, 120, buttonHeight);
        Button createEventButton = new Button("Create Event", 220, 10, 120, buttonHeight);

        homeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                AdminPage adminPage = new AdminPage();
            }
        });

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEventFields();
                setUserFields();
                containerPanel.repaint();
            }
        });

        createEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUserFields();
                setEventFields();
                containerPanel.repaint();
            }
        });

        listButtons.add(homeButton);
        listButtons.add(createUserButton);
        listButtons.add(createEventButton);

        navBar = new NavBar(listButtons,400,50,20,0);
        containerPanel.add(navBar);
    }
}
