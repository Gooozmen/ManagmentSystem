package Components;

import javax.swing.*;

public class Label extends JLabel {

    private String text;

    public Label(String message, int x, int y, int width, int height)
    {
        this.setText(message);
        this.setBounds(x,y,width,height);
    }

    public String text() {
        return text;
    }
}
