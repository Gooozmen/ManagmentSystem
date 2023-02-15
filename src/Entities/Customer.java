package Entities;

public class Customer
{
    private int dni;
    private String name;
    private String password;
    private int accessType;

    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getAccessType() { return accessType; }
    public void setAccessType(int accessType) { this.accessType = accessType; }

    public Customer(int dni, String name, String password, int accessType)
    {
        setDni(dni);
        setName(name);
        setPassword(password);
        setAccessType(accessType);
    }
}
