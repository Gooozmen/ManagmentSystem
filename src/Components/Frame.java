package Components;

import javax.swing.*;
import javax.swing.Icon;
import java.awt.*;

public class Frame extends JFrame
{
    public Frame(int height, int width, Color color,String imagePath, Panel panel)
    {
        this.setTitle("Ticket Sales System");
        this.setSize(width,height);
        this.setVisible(true);
        this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new FlowLayout());

    }
}
