
/**
 * Write a description of KivaMoveTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.Point;

public class KivaMoveTester {

    public void testIllegalMove()
    {
        Kiva testKiva = new Kiva(TesterMap.getTesterMap());
        
        //move off the map left
        testOffMapTop(testKiva);
        //testOffMapLeft(testKiva);
        
    }
    
    private void testOffMapTop(Kiva testKiva)
    {
        for(int i=0; i<4; i++)
        {
            testKiva.move(KivaCommand.FORWARD);
            TesterUtilities.verifyKivaState("Up Off Map", testKiva, new Point(2,3-i), FacingDirection.UP, false, false);
        }
    }
    
    private void testOffMapLeft(Kiva testKiva)
    {
        //move off the map left
        testKiva.move(KivaCommand.TURN_LEFT);
        for(int i=0; i<3; i++)
        {
            testKiva.move(KivaCommand.FORWARD);
        }
    }
    
    public void testMoveAndTurn()
    {
        Kiva testKiva = new Kiva(TesterMap.getTesterMap());
        boolean f = false;
        
        //MOVE FORWARD FROM UP
        testKiva.move(KivaCommand.FORWARD);
        TesterUtilities.verifyKivaState("Move Forward Up", testKiva, new Point(2,3), FacingDirection.UP, f, f);
        
        //TURN RIGHT FROM UP
        testKiva.move(KivaCommand.TURN_RIGHT);
        TesterUtilities.verifyKivaState("Turn Right From Up", testKiva, new Point(2,3), FacingDirection.RIGHT, f, f);
        
        //MOVE FORWARD FROM RIGHT
        testKiva.move(KivaCommand.FORWARD);
        TesterUtilities.verifyKivaState("Move Forward From Right", testKiva, new Point(3,3), FacingDirection.RIGHT, f, f);
        
        //TURN RIGHT FROM RIGHT
        testKiva.move(KivaCommand.TURN_RIGHT);
        TesterUtilities.verifyKivaState("Turn Right From Right", testKiva, new Point(3,3), FacingDirection.DOWN, f, f);
        
        //MOVE FORWARD FROM DOWN
        testKiva.move(KivaCommand.FORWARD);
        TesterUtilities.verifyKivaState("Forward From Down", testKiva, new Point(3,4), FacingDirection.DOWN, f, f);
        
        //TURN RIGHT FROM DOWN
        testKiva.move(KivaCommand.TURN_RIGHT);
        TesterUtilities.verifyKivaState("Right From Down", testKiva, new Point(3,4), FacingDirection.LEFT, f, f);
        
        //MOVE FORWARD FROM LEFT
        testKiva.move(KivaCommand.FORWARD);
        TesterUtilities.verifyKivaState("Forward From Left", testKiva, new Point(2,4), FacingDirection.LEFT, f, f);
        
        //TURN RIGHT FROM LEFT
        testKiva.move(KivaCommand.TURN_RIGHT);
        TesterUtilities.verifyKivaState("Right From Left", testKiva, new Point(2,4), FacingDirection.UP, f, f);
        
        /*TESTING LEFT TURNING ONLY*/
        Point leftPoint = new Point (2,4);
        
        //TURN LEFT FROM UP
        testKiva.move(KivaCommand.TURN_LEFT);
        TesterUtilities.verifyKivaState("Left From Up", testKiva, leftPoint, FacingDirection.LEFT, f, f);
        
        //TURN LEFT FROM LEFT
        testKiva.move(KivaCommand.TURN_LEFT);
        TesterUtilities.verifyKivaState("Left From Left", testKiva, leftPoint, FacingDirection.DOWN, f, f);
        
        //TURN LEFT FROM DOWN
        testKiva.move(KivaCommand.TURN_LEFT);
        TesterUtilities.verifyKivaState("Left From Down", testKiva, leftPoint, FacingDirection.RIGHT, f, f);
        
        //TURN LEFT FROM RIGHT
        testKiva.move(KivaCommand.TURN_LEFT);
        TesterUtilities.verifyKivaState("Left From Right", testKiva, leftPoint, FacingDirection.UP, f, f);
    }
    
    public void testTakeAndDrop()
    {
        //create Kiva object and move to correct position
        Kiva testKiva = moveToPod(new Kiva(TesterMap.getTesterMap()));
        //take the pod
        testKiva.move(KivaCommand.TAKE);
        //verify state
        TesterUtilities.verifyKivaState("Take Pod", testKiva, new Point(8,1), FacingDirection.RIGHT, true, false);
        
        //create new Kiva to drop pod
        Kiva testKiva2 = moveToDropZone(testKiva);
        //drop the pod and verify state
        testKiva2.move(KivaCommand.DROP);
        TesterUtilities.verifyKivaState("Drop Pod", testKiva2, new Point(9,4), FacingDirection.DOWN, false, true);
    }
    
    private Kiva moveToPod(Kiva testKiva)
    {
        //move up 3 from y=4
        for(int i=0; i<3; i++)
        {
            testKiva.move(KivaCommand.FORWARD);
        }//at (2,1)
        //turn right
        testKiva.move(KivaCommand.TURN_RIGHT);
        //move forward 6
        for(int j=0; j<6; j++)
        {
            testKiva.move(KivaCommand.FORWARD);
        }//at (8,1)
        return testKiva;
    }
    
    private Kiva moveToDropZone(Kiva testKiva)
    {
        //move right 1
        testKiva.move(KivaCommand.FORWARD);//at (9,1)
        //turn right
        testKiva.move(KivaCommand.TURN_RIGHT);
        //move forward 3
        for(int i=0; i<3; i++)
        {
            testKiva.move(KivaCommand.FORWARD);
        }//at (9,4)
        return testKiva;
    }
}
