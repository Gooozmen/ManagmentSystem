package Components;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Frame(int height, int width, ArrayList<Panel> panels)
    {
        this.setTitle("TICKET SALES SYSTEM");
        this.setSize(width,height);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        for (Panel panel : panels) {
            this.add(panel);
        }
    }
}
