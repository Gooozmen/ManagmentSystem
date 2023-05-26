package Entities;

import java.util.Date;

public class Event
{
    private int eventId;
    private String name;
    private Date eventDate;

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getEventDate() { return eventDate; }
    public void setEventDate(Date eventDate) { this.eventDate = eventDate; }

    public Event(int id, String name ,Date date)
    {
        setEventId(id);
        setName(name);
        setEventDate(date);
    }
}
