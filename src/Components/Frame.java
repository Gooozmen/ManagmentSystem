package Components;

import javax.swing.*;

public class Frame extends JFrame
{
    public Frame(String title, int height, int width, JPanel panel)
    {
        this.setTitle(title);
        this.setSize(height,width);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);
    }
}
