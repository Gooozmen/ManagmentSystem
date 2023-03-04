package Pages;

import Components.Frame;
import Components.NavBar;
import Components.Panel;


public class Home {

    private Panel panel;
    private NavBar navBar;
    private Frame frame;

    public Home(){

        panel = new Panel();
        frame = new Frame(500, 600, panel);

        navBar = new NavBar();

        panel.add(navBar);

        panel.repaint();

    }


}
