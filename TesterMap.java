
/**
 * Write a description of TesterMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TesterMap {

    private static String defaultLayout = ""
    + "-------------\n"
    + "        P   *\n"
    + "   **       *\n"
    + "   **       *\n"
    + "  K       D *\n"
    + " * * * * * **\n"
    + "-------------\n";
    
    private static FloorMap defaultMap = new FloorMap(defaultLayout);
    
    public static FloorMap getTesterMap()
    {
        return defaultMap;
    }
}
