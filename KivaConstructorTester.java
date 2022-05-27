
/**
 * Write a description of KivaConstructorTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.Point;

public class KivaConstructorTester {
    
    private FloorMap defaultMap = TesterMap.getTesterMap();
    private Point testPoint = new Point(5,6);
    
    public void testSingleArgumentConstructor()
    {
        Kiva kiva = new Kiva(defaultMap);
        
        Point initialLocation = kiva.getCurrentLocation();
        Point expectedLocation = testPoint;
        
        if(TesterUtilities.sameLocation(initialLocation, expectedLocation))
        {
            System.out.println("testSingleArgumentConstructor SUCCESS!!!!!!!!!!!!!!!11!!!!!!1111!!1!1!");
        }
        else
        {
            System.out.println(String.format("testSingleArgumentConstructor FAIL: %s != %s!", initialLocation, expectedLocation));
        }
    }
    
    public void testTwoArgumentConstructor()
    {
        Point initialMapLocation = defaultMap.getInitialKivaLocation();
        Kiva kiva = new Kiva(defaultMap, initialMapLocation);
        
        Point initialLocation = kiva.getCurrentLocation();
        Point expectedLocation = testPoint;
        
        if(TesterUtilities.sameLocation(initialLocation, expectedLocation))
        {
            System.out.println("testSingleArgumentConstructor SUCCESS!!!!!!!!!!!!!!!11!!!!!!1111!!1!1!");
        }
        else
        {
            System.out.println(String.format("testSingleArgumentConstructor FAIL: %s != %s!", initialLocation, expectedLocation));
        }
    }
}
