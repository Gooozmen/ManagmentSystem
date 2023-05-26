package Components;


import javax.swing.*;

public class Button extends JButton {

    public Button(String text, int x, int y, int width, int height)
    {
        this.setText(text);
        this.setBounds(x,y,width,height);
    }
}
