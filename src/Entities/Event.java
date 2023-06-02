package Entities;

import java.util.Date;

public class Event
{
    private int eventId;
    private String name;
    private String eventDate;

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public Event(int id, String name ,String date)
    {
        setEventId(id);
        setName(name);
        setEventDate(date);
    }

    public Event(String name ,String date)
    {
        setName(name);
        setEventDate(date);
    }
}
