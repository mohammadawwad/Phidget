//Add Phidgets Library
import com.phidget22.*;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;



public class WhackAMole {

private static Random RAND = new Random();
private static Timer TIME = new Timer();
private static long startTime = System.currentTimeMillis();

private static int lightDecider;
private static int timeOn;

private static int number = 10000;
private static int value;
private static int y;



//Handle Exceptions
public static void main(String[] args) throws Exception {

    int score = 0;
    int lightTime = 10000;


    //Create
    DigitalInput redButton = new DigitalInput();
    DigitalOutput redLED = new DigitalOutput();
    DigitalInput greenButton = new DigitalInput();
    DigitalOutput greenLED = new DigitalOutput();



    //Address
    redButton.setHubPort(0);
    redButton.setIsHubPortDevice(true);
    redLED.setHubPort(1);
    redLED.setIsHubPortDevice(true);
    greenButton.setHubPort(5);
    greenButton.setIsHubPortDevice(true);
    greenLED.setHubPort(4);
    greenLED.setIsHubPortDevice(true);



    //Open
    redButton.open(1000);
    redLED.open(1000);
    greenButton.open(1000);
    greenLED.open(1000);



    System.out.println("Welcome to Whack A Mole!");
    System.out.println("Make sure to hold down the button until the light turns off!");



    System.currentTimeMillis();

    //Use your Phidgets
    while (true) {
        //Randomizer();
        Thread.sleep(3000);

        //decides which light will turn on
        lightDecider = RAND.nextInt(2) + 1;
        
        
        //decrements the time the light is on for
        timeDecrementer();
        timeOn = number;
        
        switch(lightDecider) {
            case 1:
                greenLED.setState(true);
                Thread.sleep(timeOn);
                greenLED.setState(false);
                
                if(greenButton.getState()){
                    score++;
                    System.out.println("Score: " + score);
                } else if (redButton.getState()) {
                    score = 0;
                    System.out.println("Oh no! Wrong button! Score: " + score);
                } else {
                    score = 0;
                    System.out.println("Too slow! Score: " + score);
                }
            break;

            case 2:
                redLED.setState(true);
                Thread.sleep(timeOn);
                redLED.setState(false);
            if(redButton.getState()){
                score++;
                System.out.println("Score: " + score);
            } else if(greenButton.getState()){
                score = 0;
                System.out.println("Oh no! Wrong button! Score: " + score);
            } else {
                score = 0;
                System.out.println("Too slow! Score: " + score);
            }
            break;
            }

        }
    }


    //decrements the time by 500ms each time
    public static int timeDecrementer(){
        number -= 500;
        System.out.println(number);
         
        return number;
    }

//to do list for Friday
//output score onto lcd screen
//end the game once the timer for how long the leds stays on for is almost 0
//prompt to play again
//make it a touch instead of a hold to get the points
//hook up the second hub to attach more buttons and leds
}
