package Components;

import javax.swing.*;

public class TextField extends JTextField {

    public TextField(int x, int y, int width, int height,int columns)
    {
        this.setColumns(columns);
        this.setBounds(x,y,width,height);
    }
}
