
/**
 * Enumeration that defines the commands that are processed by <code>Kiva.move()</code>.
 * 
 * @author BENJMATT
 * @version 05/2022
 */
public enum KivaCommand {

    FORWARD('F'),
    TURN_LEFT('L'),
    TURN_RIGHT('R'),
    TAKE('T'),
    DROP('D');
    
    private char directionKey;
    
    private KivaCommand(char directionKey)
    {
        this.directionKey = directionKey;
    }
    
    public char getDirectionKey()
    {
        return this.directionKey;
    }
}
