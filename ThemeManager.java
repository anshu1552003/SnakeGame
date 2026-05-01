package snake;

import java.awt.*;

public class ThemeManager {

    public static Color bg;
    public static Color head;
    public static Color body;

    public static void setDarkTheme(){
        bg = Color.BLACK;
        head = Color.GREEN;
        body = Color.YELLOW;
    }

    public static void setNeonTheme(){
        bg = new Color(10,10,30);
        head = Color.CYAN;
        body = new Color(0,150,255);
    }
}
