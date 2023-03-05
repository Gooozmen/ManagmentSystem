package Components;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.*;

public class Table extends JPanel {

    JTable table;

    public Table(Object[][] data, Object[] colums){
        
        this.setBounds(10, 100, 400, 300);
        table = new JTable(data, colums);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);

    }

    public void refreshData(Object[][] data){

        for(int row = 0; row < data.length; row++){

            Object[] user = data[row];

            for(int column = 0; column < user.length; column++){
                Object value = user[column];
                table.setValueAt(value, row, column);
            }
        }

        this.repaint();

    }

}
