package Entities;

public class Employee
{
    private String dni;
    private String password;
    private int accessType;

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getAccessType() { return accessType; }
    public void setAccessType(int accessType) { this.accessType = accessType; }

    public Employee(String dni, String password, int accessType)
    {
        setDni(dni);
        setPassword(password);
        setAccessType(accessType);
    }
}
