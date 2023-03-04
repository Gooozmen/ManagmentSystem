package Pages;

import java.util.ArrayList;
import java.util.function.Function;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Components.Button;
import Components.Frame;
import Components.Label;
import Components.NavBar;
import Components.Panel;


public class Home {

    private Frame frame;
    private Panel panel;
    private NavBar navBar;
    private Panel adminView;
    private Panel ventasView;


    private boolean admin;

    public Home(){

        panel = new Panel();
        frame = new Frame(500, 600, panel);
        admin = true;

        
        setNavBar(admin); // se manda true si se hizo login como admin o false si no.
        panel.add(navBar);
        
        addViews(admin); // se llama siempre despues de añadir el navbar porque hace algo raro si no.
        setInitialView(admin); // se manda true si se hizo login como admin o false si no.

    }

    // Routing

    private void hideAllViews(){
        adminView.setVisible(false);
        ventasView.setVisible(false);
    }

    private void setInitialView(boolean admin){

        if(admin){
            goToNamed("adminPanel");
        }else{
            goToNamed("ventas");
        }
        
    }

    private void goToNamed(String route){

        hideAllViews();
        
        switch (route) {
            case "adminPanel":
                adminView.setVisible(true);
                break;
            case "ventas":
                ventasView.setVisible(true);
                break;
            default:
                ventasView.setVisible(true);
        }

    }

    // Constuctors

    private void addViews(boolean admin){

        adminView = AdminPanelView();
        ventasView = VentasView();

        panel.add(adminView);
        panel.add(ventasView);
    }

    private void setNavBar(boolean admin){
        ArrayList<Button> buttons = new ArrayList<Button>();

        Button adminPanelButton;
        Button ventasButton;
        int buttonWidth = 110;
        int buttonHeight = 30;

        if(admin){
            adminPanelButton = new Button("Admin Panel", 10, 10, buttonWidth, buttonHeight);
            adminPanelButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    goToNamed("adminPanel");
                }
            });
            
            ventasButton = new Button("Ventas", 130, 10, buttonWidth, buttonHeight);
            ventasButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    goToNamed("ventas");
                }
            });

            buttons.add(adminPanelButton);
            buttons.add(ventasButton);
        }else{
            ventasButton = new Button("Ventas", 10, 10, buttonWidth, buttonHeight);

            buttons.add(ventasButton);
        }

        navBar = new NavBar(buttons);
    }

    private Panel AdminPanelView(){

        Panel adminBody = new Panel();
        adminBody.setSize(frame.getWidth(), frame.getHeight() - navBar.getHeight());

        Label labelTitulo = new Label("Panel de Administrador", 10, 60, 150, 25);
        adminBody.add(labelTitulo);
        return adminBody;

    }

    private Panel VentasView(){

        Panel ventasBody = new Panel();
        ventasBody.setSize(frame.getWidth(), frame.getHeight() - navBar.getHeight());

        Label labelTitulo = new Label("Ventas", 10, 60, 150, 25);
        ventasBody.add(labelTitulo);
        return ventasBody;

    }
}
