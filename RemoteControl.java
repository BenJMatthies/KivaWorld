import edu.duke.FileResource;
import java.util.Arrays;
import java.lang.*;

/**
 * This is the class that controls Kiva's actions. Implement the <code>run()</code>
 * method to deliver the pod and avoid the obstacles.
 *
 * This is starter code that may or may not work. You will need to update the code to
 * complete the project.
 */
public class RemoteControl {
    KeyboardResource keyboardResource;

    /**
     * Build a new RemoteControl.
     */
    public RemoteControl() {
        this.keyboardResource = new KeyboardResource();
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
     * [Here's the method you'll execute from within BlueJ. It may or may not run successfully
     * as-is, but you'll definitely need to add more to complete the project.]
     */
    public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource();
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);
        System.out.println("01234567890123456789");

        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        
        /*Kiva kiva = new Kiva(floorMap);
        TesterUtilities.moveByString(directions, kiva);//*/
        KivaCommand[] commands = convertToKivaCommands(directions);
        
        Kiva kiva = new Kiva(floorMap);
        for(KivaCommand command : commands)
        {
            kiva.move(command);
        }//*/
        
        System.out.println("Pod successfully dropped: " + kiva.isSuccessfullyDropped());
        
        
        
        
        //System.out.println("Directions that you typed in: " + directions);
    }
    
    /**
     * Converts a string into an array of KivaCommands, or throws an exception if there is invalid input.
     */
    private KivaCommand[] convertToKivaCommands(String input)
    {
        KivaCommand[] availableCommands = KivaCommand.values();
        if(isValidInput(input, availableCommands))
        {
            KivaCommand[] returnedCommands = new KivaCommand[input.length()];
            int charCounter = 0;
            
            for(char c : input.toUpperCase().toCharArray())
            {
                for(KivaCommand command : availableCommands)
                {
                    if(Character.toUpperCase(command.getDirectionKey()) == c)
                        returnedCommands[charCounter] = command;
                }
                charCounter++;
            }
            return returnedCommands;
        }
        else
            throw new IllegalArgumentException("Input contains illegal characters!");
    }
    
    /**
     * Checks if there are any characters in the input string that are illegal.
     */
    private boolean isValidInput(String input, KivaCommand[] availableCommands)
    {
        String legalInput = new String(getDirectionKeys(availableCommands));
        
        
        
        for(char c : input.toUpperCase().toCharArray())
        {
            if(!legalInput.contains(Character.toString(c)))
                return false;
        }
        return true;
        /*String legalInputString = legalInput.toString();
        
        for(char c : input.toUpperCase().toCharArray())
        {
            if(legalInputString.indexOf(c) != -1)
                return true;
        }
        return false;*/
    }
    
    public void test()
    {
        System.out.println(isValidInput("FFLR;BDT", KivaCommand.values()));
    }
    
    /**
     * Returns an array of chars corresponding to each KivaCommand.
     */
    private char[] getDirectionKeys(KivaCommand[] availableCommands)
    {
        char[] directionKeys = new char[availableCommands.length];
        for(int i=0; i<availableCommands.length; i++)
        {
            directionKeys[i] = availableCommands[i].getDirectionKey();
        }
        return directionKeys;
    }
}
