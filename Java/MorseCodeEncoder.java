//Add Phidgets Library
import com.phidget22.*;
import java.util.Scanner;

public class MorseEncoder {
  
    //Define
    static DigitalOutput greenLED;

    //Define delay
    static int delay = 250;

    //Handle Exceptions
    public static void main(String[] args) throws Exception {
        
        //Create
        Scanner scan = new Scanner(System.in);
        greenLED = new DigitalOutput();

        //Address
        greenLED.setHubPort(4);
        greenLED.setIsHubPortDevice(true);

        //Open
        greenLED.open(1000);

        //Use your Phidgets
        System.out.println("Enter the word you would like to encode:");
        String userInput = scan.nextLine();
        String word = userInput.toLowerCase();
        
        System.out.print("Translating... Look at the green LED." + "\n");
        Thread.sleep(2000);
        
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'a') {
                A();
                System.out.print(" ");
            } else if (word.charAt(i) == 'b') {
                B();
                System.out.print(" ");
            }

            if (word.charAt(i) == ' ')//7 units of delay between words
            {
                Thread.sleep(delay * 7);
                System.out.print("/ ");
            } else {
                Thread.sleep(delay * 3);//3 units between letters
            }
        }

    }

    //Flash LED corresponding to dot
    public static void dot() throws Exception {
        System.out.print(".");
        greenLED.setState(true);
        Thread.sleep(delay);
        greenLED.setState(false);
        Thread.sleep(delay);
    }

    //Flash LED corresponding to dash
    public static void dash() throws Exception {
        System.out.print("-");
        greenLED.setState(true);
        Thread.sleep(delay * 3);
        greenLED.setState(false);
        Thread.sleep(delay);
    }

    //Assigns Morse code to letters with appropriate delay
    public static void A() throws Exception {
        dot();
        Thread.sleep(delay);
        dash();
        Thread.sleep(delay);
    }

    public static void B() throws Exception {
        dash();
        Thread.sleep(delay);
        dot();
        Thread.sleep(delay);
        dot();
        Thread.sleep(delay);
        dot();
        Thread.sleep(delay);
    }
}
