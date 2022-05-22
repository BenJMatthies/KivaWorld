
/**
 * Write a description of KivaCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
