
/**
 * Write a description of Kiva here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.Point;

public class Kiva {

    private Point currentLocation;
    private FacingDirection directionFacing;
    private FloorMap map;
    private boolean carryingPod;
    private boolean successfullyDropped;
    //private ? motorLifetime;
    
    public Kiva(FloorMap map)
    {
        this(map, map.getInitialKivaLocation());
    }
    
    public Kiva(FloorMap map, Point currentLocation)
    {
        this.currentLocation = currentLocation;
        this.directionFacing = FacingDirection.UP;
        this.map = map;
        this.carryingPod = false;
        this.successfullyDropped = false;
    }
    
    public boolean isCarryingPod()
    {
        return carryingPod;
    }
    
    public boolean isSuccessfullyDropped()
    {
        return successfullyDropped;
    }
}
