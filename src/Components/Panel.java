package Components;

import javax.swing.*;

public class Panel extends JPanel
{
    public Panel()
    {
        this.setLayout(null);
    }

    public Panel(int x, int y, int width, int height)
    {
        this.setLayout(null);
        this.setBounds(x,y,width,height);
    }
}
