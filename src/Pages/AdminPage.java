package Pages;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Components.Button;
import Components.Frame;
import Components.NavBar;
import Components.Panel;
import Views.SalesView;
import Views.UsersView;


public class AdminPage {

    private Frame frame;
    private Panel containerPanel;
    private NavBar listNavBar;
    private NavBar createNavBar;
    private UsersView usersView;
    private SalesView salesView;


    public AdminPage()
    {
        containerPanel = new Panel();

        frame = new Frame(550, 430, containerPanel);
        
        setNavBar(); // se manda true si se hizo login como admin o false si no.

        addViews(); // se llama siempre despues de añadir el navbar porque hace algo raro si no.
        setInitialView(); // se manda true si se hizo login como admin o false si no.
        containerPanel.add(listNavBar);
        containerPanel.add(createNavBar);
        containerPanel.repaint();
        containerPanel.revalidate();
    }

    // Routing

    private void hideAllViews(){
        this.usersView.setVisible(false);
    }

    private void setInitialView(){
        this.PrimaryPanelRouting("users");
    }

    private void PrimaryPanelRouting(String route){

        hideAllViews();
        
        switch (route) {
            case "users":
                usersView.setVisible(true);
                break;
            case "sales":
                salesView.setVisible(true);
                break;
            default:
                usersView.setVisible(true);
        }
    }


    // Constuctors

    private void addViews(){

        usersView = new UsersView(0,80,frame.getWidth(), frame.getHeight() - listNavBar.getHeight());
        salesView = new SalesView(0,80,frame.getWidth(), frame.getHeight() - listNavBar.getHeight());

        containerPanel.add(usersView);
        containerPanel.add(salesView);
    }

    private void setNavBar(){
        ArrayList<Button> listButtons = new ArrayList();
        ArrayList<Button> createButtons = new ArrayList();

        int buttonHeight = 30;

        Button usersButton = new Button("Users", 50, 10, 100, buttonHeight);

        Button eventsButton = new Button("Events",  150, 10, 100, buttonHeight);

        Button salesButton = new Button("Sales",  250, 10, 100, buttonHeight);


        Button createUserButton = new Button("Creation Panel",  150, 0, 100, buttonHeight);


        usersButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PrimaryPanelRouting("users");
            }
        });
        eventsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PrimaryPanelRouting("events");
            }
        });
        salesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PrimaryPanelRouting("sales");
            }
        });

        createUserButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                CreationPage creationPage = new CreationPage();
                frame.dispose();
            }
        });

        listButtons.add(usersButton);
        listButtons.add(eventsButton);
        listButtons.add(salesButton);

        createButtons.add(createUserButton);

        listNavBar = new NavBar(listButtons,430,50,0,0);
        createNavBar = new NavBar(createButtons,430,60,0,50);

    }
}
