
/**
 * Write a description of TesterUtilities here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.Point;

public class TesterUtilities {

    public static boolean sameLocation(Point a, Point b)
    {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }
    
    public static void verifyKivaState(
        String testName,
        Kiva actualState,
        Point expectedLocation,
        FacingDirection expectedDirection,
        boolean expectedCarry,
        boolean expectedDropped)
    {
        //Testing Location
        Point actualLocation = actualState.getCurrentLocation();
        if(sameLocation(actualLocation, expectedLocation))
            System.out.println(String.format("%s: SUCCESS! Current Location: %s", testName, actualLocation));
        else
        {
            System.out.println(String.format("%s: FAIL! Current Location", testName));
            System.out.println(String.format("Expected %s, got %s", expectedLocation, actualLocation));
        }
        
        //Testing FacingDirection
        FacingDirection actualDirection = actualState.getDirectionFacing();
        if(actualDirection == expectedDirection)
            System.out.println(String.format("%s: SUCCESS! Facing Direction: %s", testName, actualDirection));
        else
        {
            System.out.println(String.format("%s: FAIL! Facing Direction", testName));
            System.out.println(String.format("Expected %s, got %s", expectedDirection, actualDirection));
        }
        
        //Testing Carrying Pod
        boolean actualCarry = actualState.isCarryingPod();
        if (actualCarry == expectedCarry)
            System.out.println(String.format("%s: SUCCESS! Carrying Pod: %s", testName, actualCarry));
        else
        {
            System.out.println(String.format("%s: FAIL! Carrying Pod", testName));
            System.out.println(String.format("Expected %s, got %s", expectedCarry, actualCarry));
        }
        
        //Testing Dropped Pod
        boolean actualDropped = actualState.isSuccessfullyDropped();
        if(actualDropped == expectedDropped)
            System.out.println(String.format("%s: SUCCESS! Successfully Dropped Pod: %s", testName, actualDropped));
        else
        {
            System.out.println(String.format("%s: FAIL! Successfully Dropped Pod", testName));
            System.out.println(String.format("Expected %s, got %s", expectedDropped, actualDropped));
        }
        
        System.out.println("");
    }
    
    public static void moveByString(String pathInput, Kiva kiva)
    {
        for(char c : pathInput.toUpperCase().toCharArray())
        {
            switch(c)
            {
                case 'F':
                    kiva.move(KivaCommand.FORWARD);
                    break;
                case 'L':
                    kiva.move(KivaCommand.TURN_LEFT);
                    break;
                case 'R':
                    kiva.move(KivaCommand.TURN_RIGHT);
                    break;
                case 'T':
                    kiva.move(KivaCommand.TAKE);
                    break;
                case 'D':
                    kiva.move(KivaCommand.DROP);
                    break;
            }
        }
    }
}
