package Components;

import java.awt.Color;
import java.util.ArrayList;

public class NavBar extends Panel{

    public NavBar(ArrayList<Button> botones,int width, int height,int x, int y) {
        this.setBounds(x,y, width, height);

        for(int i = 0; i < botones.size(); i++){
            this.add(botones.get(i));
        }
        this.setBackground(Color.WHITE);
    }
}
