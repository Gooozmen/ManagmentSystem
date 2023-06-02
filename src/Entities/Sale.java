package Entities;

import java.util.Date;

public class Sale
{
    private int saleId;
    private int locationId;
    private int eventId;
    private float price;
    private Date saleDate;
    private String customerDni;

    public int getSaleId() { return saleId; }
    public void setSaleId(int saleId) { this.saleId = saleId; }
    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) { this.locationId = locationId; }
    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
    public Date getSaleDate() { return saleDate; }
    public void setSaleDate(Date saleDate) { this.saleDate = saleDate; }
    public String getCustomerDni() { return customerDni; }
    public void setCustomerDni(String customerDni) { this.customerDni = customerDni; }

    public Sale (int saleId,int eventId,int locationId,int price,String customerDni,Date saleDate)
    {
        setCustomerDni(customerDni);
        setEventId(eventId);
        setSaleId(saleId);
        setSaleDate(saleDate);
        setLocationId(locationId);
        setPrice(price);
    }
}
