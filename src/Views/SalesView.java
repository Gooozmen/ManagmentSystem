package Views;

import Components.Label;
import Components.Panel;
import Components.Table;
import DB.DatabaseManager;
import Entities.Event;
import Entities.Sale;
import Repositories.EventRepository;
import Repositories.SaleRepository;

import java.util.List;

public class SalesView extends Panel{

    private SaleRepository saleRepository = new SaleRepository();
    private EventRepository eventRepository = new EventRepository();
    private String[] columns = {"SALE ID", "EVENT NAME", "PRICE"};

    public SalesView(int x, int y, int width, int height) {

        this.setSize(width, height);
        this.setBounds(x,y,width,height);

        List<Sale> sales = saleRepository.GetAll(DatabaseManager.Connect());

        Object[][] data = parsearData(sales, columns.length);

        Table table = new Table(data, columns);

        Label labelTitle = new Label("Sales", 10, 60, 150, 25);
        this.add(labelTitle);
        this.add(table);
    }

    private Object[][] parsearData(List<Sale> sales, int columns){

        Object[][] data = new Object[sales.size()][columns];

        for(int i = 0; i < sales.size(); i++){

            Sale saleData = sales.get(i);
            Event soldEvent = eventRepository.GetBy(saleData.getEventId(),DatabaseManager.Connect());

            if(soldEvent == null)
                return null;

            else {
                Object[] saleReport = {saleData.getSaleId(), soldEvent.getName(), saleData.getPrice()};
                data[i] = saleReport;
            }
        }

        return data;
    }
}
