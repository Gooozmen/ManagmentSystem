package Entities;

public class AccessType
{
    private int accessId;
    private String Decription;

    public int getAccessId() { return accessId; }
    public void setAccessId(int accessId) { this.accessId = accessId; }
    public String getDecription() { return Decription; }
    public void setDecription(String decription) { Decription = decription; }

    public AccessType(int accessId, String description)
    {
        setAccessId(accessId);
        setDecription(description);
    }
}
