package Pages;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Components.Button;
import Components.Frame;
import Components.NavBar;
import Components.Panel;
import Views.Users;


public class AdminPanel {

    private Frame frame;
    private Panel panel;
    private NavBar navBar;
    private Panel usersView;

    public AdminPanel(){

        panel = new Panel();
        frame = new Frame(500, 600, panel);
        
        setNavBar(); // se manda true si se hizo login como admin o false si no.
        panel.add(navBar);
        
        addViews(); // se llama siempre despues de añadir el navbar porque hace algo raro si no.
        setInitialView(); // se manda true si se hizo login como admin o false si no.
        panel.repaint();
        panel.revalidate();
    }

    // Routing

    private void hideAllViews(){
        this.usersView.setVisible(false);
    }

    private void setInitialView(){

        this.goToNamed("users");
        
    }

    private void goToNamed(String route){

        hideAllViews();
        
        switch (route) {
            case "users":
                usersView.setVisible(true);
                break;
            default:
                usersView.setVisible(true);
        }

    }

    // Constuctors

    private void addViews(){

        usersView = new Users(frame.getWidth(), frame.getHeight() - navBar.getHeight());

        panel.add(usersView);
    }

    private void setNavBar(){
        ArrayList<Button> buttons = new ArrayList<Button>();

        Button usersButton;
        int buttonWidth = 110;
        int buttonHeight = 30;
        
        usersButton = new Button("Users", 10, 10, buttonWidth, buttonHeight);
        usersButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                goToNamed("users");
            }
        });
        
        buttons.add(usersButton);

        navBar = new NavBar(buttons);
    }




}
