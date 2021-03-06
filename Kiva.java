
/**
 * Class for moving and determining the state of a (virtual) Kiva robot.
 * 
 * @author BENJMATT 
 * @version 05/2022
 */
import edu.duke.Point;

public class Kiva {

    private Point currentLocation;
    private FacingDirection directionFacing;
    private FloorMap map;
    private boolean carryingPod;
    private boolean successfullyDropped;
    private long motorLifetime;
    private long maxMotorLifetime;
    
    ////////////////
    /*CONSTRUCTORS*/
    ////////////////
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
        this.motorLifetime = 0;
        this.maxMotorLifetime = 72000000000L;
    }
    
    ///////////
    /*GETTERS*/
    ///////////
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
    
    public long getMotorLifetime()
    {
        return motorLifetime;
    }
    
    ////////////////
    /*MOVE METHODS*/
    ////////////////
    /**
     * Moves the Kiva robot, and calls <code>incrementMotorLifetime</code> to update <code>motorLifetime</code> for required moves.
     */
    public void move(KivaCommand command)
    {
        switch(command)
        {
            case FORWARD:
                moveForward();
                incrementMotorLifetime();
                break;
            case TURN_RIGHT:
                moveTurnRight();
                incrementMotorLifetime();
                break;
            case TURN_LEFT:
                moveTurnLeft();
                incrementMotorLifetime();
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
    
    /**
     * Checks for obstacles and moves the Kiva robot forward by resetting <code>currentLocation</code> to the destination coordinates.  
     */
    private void moveForward()
    {
        if(moveIsOnMap() && moveIsNotObstacle())
        {
            //set new current location
            currentLocation = getDestination();//new Point(currentX, currentY);
        }
    }    
    
    /**
     * Turns the Kiva Robot right by changing its facing direction.
     */
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
    
    /**
     * Turns the Kiva robot left by changing its facing direction.
     */
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
    
    /**
     * Sets the Kiva robot's <code>carryingPod</code> state to <code>true</code>.  Includes exception checking.  
     */
    private void moveTakePod()
    {
        if(hasPod())
            carryingPod = true;
        else
            throw new NoPodException("Unable to take POD: No POD!");
    }
    
    /**
     * Sets the Kiva robot's <code>carryingPod</code> state to <code>false</code>.  Includes exception checking.  
     */
    private void moveDropPod()
    {
        String errorMessage = "Unable to DROP POD: ";
        
        if(!carryingPod)
            throw new IllegalMoveException(errorMessage + "not carrying a POD!");
        
        if(isDropZone())
        {
            carryingPod = false;
            successfullyDropped = true;
        }
        else
            throw new IllegalDropZoneException(errorMessage + "not a DROP_ZONE!");
    }
    
    /////////////////////////
    //MOTORLIFETIME METHODS//
    /////////////////////////
    /**
     * Increments the <code>motorLifetime</code> variable by 1000 milliseconds.
     */
    private void incrementMotorLifetime()
    {
        motorLifetime+=1000;
    }
    
    /**
     * Returns false if the motor has exceeded it's maximum lifetime.  
     */
    private boolean motorIsWithinLifetime()
    {
        if(motorLifetime >= maxMotorLifetime)
            return false;
        else 
            return true;
    }
    
    /////////////
    //UTILITIES//
    /////////////
    /**
     * Calculates and returns the intended destination point of a Kiva robot, given its facing direction, one move ahead.  
     */
    private Point getDestination()
    {
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
        
        return new Point(currentX, currentY);
    }
    
    //////////////////////
    /*EXCEPTION CHECKING*/
    //////////////////////
    /**
     * Checks if the next forward move is on the map or not.
     */
    private boolean moveIsOnMap()
    {
        Point destination = getDestination();
        int destinationX = destination.getX();
        int destinationY = destination.getY();
        
        String errorString = String.format("Unable to move to location %s: ", destination);
        
        if(destinationX < 0)
            throw new IllegalMoveException(errorString + "reached left side of the map!");
        if(destinationX > map.getMaxColNum())
            throw new IllegalMoveException(errorString + "reached right side of the map!");
        if(destinationY < 0)
            throw new IllegalMoveException(errorString + "reached top side of the map!");
        if(destinationY > map.getMaxRowNum())
            throw new IllegalMoveException(errorString + "reached bottom side of the map!");
            
        return true;
    }
    
    /**
     * Checks if the next forward move is into an obstacle or not.  
     */
    private boolean moveIsNotObstacle()
    {
        Point destination = getDestination();
        FloorMapObject objectAtDestination = map.getObjectAtLocation(destination);
        String errorString = String.format("Unable to move to location %s: %s in the way!", destination, objectAtDestination);
        if(objectAtDestination == FloorMapObject.OBSTACLE)
            throw new IllegalMoveException(errorString);
        if(objectAtDestination == FloorMapObject.POD && carryingPod)
            throw new IllegalMoveException(errorString);
            
        return true;
    }
    
    /**
     * Checks if the Kiva robot's current location contains a pod object.  
     */
    private boolean hasPod()
    {
        if(map.getObjectAtLocation(currentLocation) == FloorMapObject.POD)
            return true;
        else
            return false;
    }
    
    /**
     * Checks if the Kiva robot's current location is set as a drop zone on the map.  
     */
    private boolean isDropZone()
    {
        if(map.getObjectAtLocation(currentLocation) == FloorMapObject.DROP_ZONE)
            return true;
        else
            return false;
    }
}
