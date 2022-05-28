
/**
 * Write a description of KivaExceptionTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.Point;

public class KivaExceptionTester {

    Kiva testKiva = new Kiva(TesterMap.getTesterMap());
    
    public void testOffMap()
    {
        //Kiva testKiva = new Kiva(TesterMap.getTesterMap());
        
        //Test off map LEFT
        //TesterUtilities.moveByString("LFFF", testKiva);
        
        //Test off map RIGHT
        //System.out.println("Max X: " + TesterMap.getTesterMap().getMaxColNum());
        //TesterUtilities.moveByString("RFFFFFFFFFFF", testKiva);
        
        //Test off map TOP
        //TesterUtilities.moveByString("FFFFF", testKiva);
        
        //Test of map BOTTOM
        //System.out.println("Max Y: " + TesterMap.getTesterMap().getMaxRowNum());
        //TesterUtilities.moveByString("RRFFF", testKiva);
    }
    
    public void testObstacleCollision()
    {
        //Kiva testKiva = new Kiva(TesterMap.getTesterMap());
        TesterUtilities.moveByString("RRFF", testKiva);
    }
    
    public void testPodCollision()
    {
        //Kiva testKiva = new Kiva(TesterMap.getTesterMap());
        testKiva.move(KivaCommand.TAKE);
        TesterUtilities.moveByString("FFFRFFFFFF", testKiva);
    }
    
    public void testIllegalTake()
    {
        //TesterUtilities.moveByString("FFFRFFFFFF", testKiva);
        testKiva.move(KivaCommand.TAKE);
    }
    
    public void testIllegalDrop()
    {
        TesterUtilities.moveByString("RFFFFFFFF", testKiva);
        testKiva.move(KivaCommand.DROP);
    }
}
