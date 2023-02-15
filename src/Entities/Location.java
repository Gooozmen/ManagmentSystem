package Entities;

public class Location
{
    private int locationId;
    private int eventId;
    private float price;
    private String description;
    private int capacity;

    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) { this.locationId = locationId; }
    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public Location(int locationId,int eventId,float price,String description,int capacity)
    {
        setLocationId(locationId);
        setEventId(eventId);
        setPrice(price);
        setDescription(description);
        setCapacity(capacity);
    }
}
