
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
        //this.motorLifetime = ?;
    }
    
    public Point getCurrentLocation()
    {
        return currentLocation;
    }
    
    public FacingDirection getDirectionFacing()
    {
        return directionFacing;
    }
    
    public FloorMap getMap()
    {
        return map;
    }
    
    public boolean isCarryingPod()
    {
        return carryingPod;
    }
    
    public boolean isSuccessfullyDropped()
    {
        return successfullyDropped;
    }
    
    public void move(KivaCommand command)
    {
        switch(command)
        {
            case FORWARD:
                moveForward();
                break;
            case TURN_RIGHT:
                moveTurnRight();
                break;
            case TURN_LEFT:
                moveTurnLeft();
                break;
            case TAKE:
                moveTakePod();
                break;
            case DROP:
                moveDropPod();
                break;
            default:
                throw new InvalidKivaCommandException("Invalid command received");
        }
    }
    
    private void moveForward()
    {
        if(!moveIsOnMap())
            throw new IllegalMoveException("Illegal move: off the map.");
        if(!moveIsNotObstacle())
            throw new IllegalMoveException("Illegal move: obstacle at destination.");
        
        int currentX = currentLocation.getX();
        int currentY = currentLocation.getY();
        
        switch(directionFacing)
        {
            case UP:
                //decrement currentLocation Y value
                currentY--;
                break;
            case DOWN:
                //increment currentLocation Y value
                currentY++;
                break;
            case LEFT:
                //decrement currentLocation X value
                currentX--;
                break;
            case RIGHT:
                //increment currentLocation X value
                currentX++;
                break;
        }
        
        //set currentLocation to new value
        currentLocation = new Point(currentX, currentY);
    }
    
    private boolean moveIsOnMap()
    {
        int currentX = currentLocation.getX();
        int currentY = currentLocation.getY();
        int maxX = map.getMaxRowNum();
        int maxY = map.getMaxColNum();
        
        switch(directionFacing)
        {
            case UP: 
                if(currentY-- <= 0)
                    return false;
                break;
            case DOWN:
                if(currentY++ > maxY)
                    return false;
                break;
            case LEFT:
                if(currentX-- <= 0)
                    return false;
                break;
            case RIGHT:
                if(currentX++ > maxX)
                    return false;
                break;
        }
        return true;
    }
    
    private boolean moveIsNotObstacle()
    {
        
        int currentX = currentLocation.getX();
        int currentY = currentLocation.getY();
        
        switch(directionFacing)
        {
            case UP: 
                if(map.getObjectAtLocation(new Point(currentX, currentY--)) == FloorMapObject.OBSTACLE)
                    return false;
                break;
            case DOWN:
                if(map.getObjectAtLocation(new Point(currentX, currentY++)) == FloorMapObject.OBSTACLE)
                    return false;
                break;
            case LEFT:
                if(map.getObjectAtLocation(new Point(currentX--, currentY)) == FloorMapObject.OBSTACLE)
                    return false;
                break;
            case RIGHT:
                if(map.getObjectAtLocation(new Point(currentX++, currentY)) == FloorMapObject.OBSTACLE)
                    return false;
                break;
        }
        return true;
    }
    
    private void moveTurnRight()
    {
        switch(directionFacing)
        {
            case UP:
                directionFacing = FacingDirection.RIGHT;
                break;
            case RIGHT:
                directionFacing = FacingDirection.DOWN;
                break;
            case DOWN:
                directionFacing = FacingDirection.LEFT;
                break;
            case LEFT:
                directionFacing = FacingDirection.UP;
                break;
            //We shouldn't need a default option, because these are the only values a FacingDirection should be able to hold....
        }
    }
    
    private void moveTurnLeft()
    {
        switch(directionFacing)
        {
            case UP: 
                directionFacing = FacingDirection.LEFT;
                break;
            case LEFT:
                directionFacing = FacingDirection.DOWN;
                break;
            case DOWN:
                directionFacing = FacingDirection.RIGHT;
                break;
            case RIGHT:
                directionFacing = FacingDirection.UP;
                break;
            //We shouldn't need a default option, because these are the only values a FacingDirection should be able to hold....
        }
    }
    
    private void moveTakePod()
    {
        carryingPod = true;
    }
    
    private void moveDropPod()
    {
        carryingPod = false;
        successfullyDropped = true;
    }
}
