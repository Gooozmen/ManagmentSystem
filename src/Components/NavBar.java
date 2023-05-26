package Components;

import java.awt.Color;
import java.util.ArrayList;

public class NavBar extends Panel{

    public NavBar(ArrayList<Button> botones) {
        this.setBounds(0,0, 600, 50);

        for(int i = 0; i < botones.size(); i++){
            this.add(botones.get(i));
        }
        this.setBackground(Color.WHITE);
    }
}
