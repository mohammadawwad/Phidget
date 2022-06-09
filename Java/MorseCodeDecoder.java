
//Add Phidgets Library
import com.phidget22.*;

public class MorseCode {
    
    //Used to store times
    static long startPress;
    static double timePressed;

    //Used to store input
    static String userInput = "";

    //Convert user input from morse code to characters
    public static char Decoder(String code) {
        String[] morseCode = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
            "Unknown Code"};
        int i = 0;
        while (morseCode[i].equals("Unknown Code") == false) {
            if (code.equals(morseCode[i])) {
                return Character.toUpperCase((char) (97 + i)); //Character 'a' starts at 97.
            }
            i++;
        }
        return '?'; //unknown code
    }

    //Handle Exceptions
    public static void main(String[] args) throws Exception {

        //Create
        DigitalInput redButton = new DigitalInput();
        DigitalOutput redLED = new DigitalOutput();
        DigitalInput greenButton = new DigitalInput();
        DigitalOutput greenLED = new DigitalOutput();

        //Address
        redLED.setHubPort(1);
        redLED.setIsHubPortDevice(true);
        redButton.setHubPort(0);
        redButton.setIsHubPortDevice(true);
        greenButton.setHubPort(5);
        greenButton.setIsHubPortDevice(true);
        greenLED.setHubPort(4);
        greenLED.setIsHubPortDevice(true);

        //Event
        redButton.addStateChangeListener(new DigitalInputStateChangeListener() {
            public void onStateChange(DigitalInputStateChangeEvent e) {
                try {
                    redLED.setState(e.getState()); //Turn on red LED

                    if (e.getState()) { //Once red button is pushed, Decoder method is called for the users input.
                        System.out.print(" = ");
                        System.out.print(Decoder(userInput));
                        System.out.println();
                        userInput = ""; //Clears userInput for next code
                    }
                } catch (PhidgetException ex) {
                    System.out.println("Failure: " + ex);
                }
            }
        });

        //Event
        greenButton.addStateChangeListener(new DigitalInputStateChangeListener() {
            public void onStateChange(DigitalInputStateChangeEvent e) {
                try {
                    greenLED.setState(e.getState()); //Turn on green LED

                    if (e.getState()) {
                        startPress = System.currentTimeMillis(); //Grabs time when the button is first pressed
                    } else {
                        timePressed = ((double) (System.currentTimeMillis() - startPress));//Grabs time when button released, subtracts start time.
                        if ((timePressed > 50) && (timePressed < 300)) {
                            userInput += ".";
                            System.out.print("."); //Prints a dot to the screen
                        } else if (timePressed > 300) {
                            userInput += "-";
                            System.out.print("-"); //Prints a dash to the screen
                        }

                    }

                } catch (PhidgetException ex) {
                    System.out.println("Failure: " + ex);
                }
            }
        });

        //Open
        redLED.open(1000);
        greenLED.open(1000);
        redButton.open(1000);
        greenButton.open(1000);

        //Use your Phidgets
        System.out.println(
                "Start entering into the decoder." + "\n" + "Press the green button for a dot, hold for a dash." + "\n"
                + "Press the red button after you enter a letter.");
        userInput = "";
        //Wait for input
        while (true) {
            Thread.sleep(10);
        }
    }
}
  
