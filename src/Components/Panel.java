package Components;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel
{
    public Panel(Color color, int x, int y, int width, int height)
    {
        this.setBackground(color);
        this.setBounds(x,y,width,height);
    }
}
