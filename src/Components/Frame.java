package Components;

import javax.swing.*;

public class Frame extends JFrame
{
    public Frame(int height, int width, JPanel panel)
    {
        this.setTitle("TICKET SALES SYSTEM");
        this.setSize(width,height);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);
    }
}
