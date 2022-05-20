package phidget;



//Add Phidgets Library
import com.phidget22.*;
import java.util.Random;
import java.util.Timer;



public class WhackAMole {

    private static Random RAND = new Random();
    private static Timer TIME = new Timer();
    private static long startTime = System.currentTimeMillis();

    private static int lightDecider;
    private static long timeOn;

    private static long number = 4000;
    private static int value;
    private static int y;
    
    //used to check if the game has ended
    private static boolean continuePlaying = true;
    private static int roundCounter = 0;



    //Handle Exceptions
    public static void main(String[] args) throws Exception {

        int score = 0;


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
        while (continuePlaying) {
            //Randomizer();
            Thread.sleep(timeOn);
            
            roundCounter++;

            //decides which light will turn on
            lightDecider = RAND.nextInt(2) + 1;


            //decrements the time the light is on for
            timeDecrementer();
            timeOn = number;

            if (timeOn > 0){
            switch(lightDecider) {
                case 1:
                    greenLED.setState(true);

                    boolean reset = false;
                    
                    for (long stop = System.currentTimeMillis() + 1000; stop > System.currentTimeMillis();) {
                        if(greenButton.getState()){
                            score++;
                            System.out.println("Score: " + score);
                            reset = false;
                            break;
                            
                        } else if (redButton.getState()) {
                            score = 0;
                            System.out.println("Oh no! Wrong button! Score: " + score);
                            break;
                        } else{
                            reset = true;
                        }
                        
                    }

                    if(reset == true){
                        score = 0;
                        System.out.println("Score Reset: " + score);
                    }
                    
                    greenLED.setState(false);


                break;

                case 2:
                    redLED.setState(true);
                    
                    boolean reset2 = false;
                    
                    
                    for (long stop = System.currentTimeMillis() + 1000; stop > System.currentTimeMillis();) {
                        if(redButton.getState()){
                            score++;
                            System.out.println("Score: " + score);
                            reset2 = false;
                            break;
                            
                        } else if (greenButton.getState()) {
                            score = 0;
                            System.out.println("Oh no! Wrong button! Score: " + score);
                            break;
                        } else{
                            reset2 = true;
                        }
                        
                    }

                    if(reset2 == true){
                        score = 0;
                        System.out.println("Score Reset: " + score);
                    }
                    


                    redLED.setState(false);

                break;
                }

            }
        }
    }

    //method for decreasing the time
    public static long timeDecrementer(){

        //a non linear time function that decreases the time all the way to 0.2 seconds and steadies there
        number = (long) (((4) * Math.pow(1.3, -roundCounter) + 0.2) * 1000);
        System.out.println(number);
                          
        return number;
    }
}
