package Components;

public class NavBar extends Panel{

    private Button ventasButton;
    private Button adminPanelButton;
    private Integer buttonWidth = 110;
    private Integer buttonHeight = 30;

    private Integer[] buttonsX = {10, 20 + buttonWidth};
    public NavBar( ) {
        this.setBounds(0,0, 500, 50);
        adminPanelButton = new Button("Admin Panel", buttonsX[0], 10, buttonWidth, buttonHeight);
        ventasButton = new Button("Ventas", buttonsX[1], 10, buttonWidth, buttonHeight);
        this.add(adminPanelButton);
        this.add(ventasButton);
    }
}
