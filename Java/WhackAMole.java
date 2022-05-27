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
    //used to check if the game has ended
    private static boolean continuePlaying = true;
    private static int roundCounter = 0;
    private static int missedCounter = 0;



    //Handle Exceptions
    public static void main(String[] args) throws Exception {

        //player score
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
        
        
        //Creates and opens lcd screen
        LCD lcd = new LCD();
        lcd.open(3000);
        
        //intro message
        lcd.clear();
        lcd.writeText(LCDFont.DIMENSIONS_6X12, 28, 0, "Whack-A-Mole");
        lcd.drawLine(27, 10, 99, 10);
        lcd.writeText(LCDFont.DIMENSIONS_6X10, 45, 25, "Begin!");
        lcd.flush();
        
        //allowing enough time for user reaction before game starts
        Thread.sleep(3000);
        
        
        System.currentTimeMillis();

        //UKeeps playing untill you get 3 wronge 
        while (continuePlaying) {
            
            //Welcome Message on LCD Screen
            lcd.clear();
            lcd.writeText(LCDFont.DIMENSIONS_6X12, 28, 0, "Whack-A-Mole");
            lcd.drawLine(27, 10, 99, 10);
            
            //outputting the score on the LCD screen            
            lcd.writeText(LCDFont.DIMENSIONS_6X12, 32, 25, "Score: " + score);
            lcd.writeText(LCDFont.DIMENSIONS_6X12, 32, 35, "Missed: " + missedCounter + "/3");
            lcd.flush();

            //allows enough time for user reaction in the first round
            if(roundCounter == 0){
                Thread.sleep(1000);
            }
            
            //ends game if you missed 3 rounds
            if(missedCounter >= 3){
                continuePlaying = false;
                System.out.println("Game Over!");
                lcd.writeText(LCDFont.DIMENSIONS_6X12, 32, 45, "Game Over!");
                lcd.flush();
            }
            
            Thread.sleep(timeOn);
            roundCounter++;

            //decides which light will turn on
            lightDecider = RAND.nextInt(2) + 1;

            //decrements the time the light is on for
            timeDecrementer();
            timeOn = number;
         

            //turns on and off the leds
            if (timeOn > 0){
            switch(lightDecider) {
                
                //green lights ON
                case 1:
                    greenLED.setState(true);

                    boolean reset = false;
                    
                    //keeps led on for certain time based on math function
                    for (long stop = System.currentTimeMillis() + 1000; stop > System.currentTimeMillis();) {
                        if(greenButton.getState()){
                            score++;
                            System.out.println("Score: " + score);
                            reset = false;
                            break;
                            
                        } else if (redButton.getState()) {
                            reset = true;
                            System.out.println("Oh no! Wrong button!");
                            break;
                        } else{
                            reset = true;
                        }
                        
                    }

                    //setting that you have missed a LED
                    if(reset == true){
                        System.out.println("Score Reset: " + score);
                        missedCounter++;
                    }
                    
                    //turns off led
                    greenLED.setState(false);
                    break;

                //red light tunrs ON
                case 2:
                    redLED.setState(true);
                    
                    boolean reset2 = false;
                    
                    //keeps led on for certain time based on math function
                    for (long stop = System.currentTimeMillis() + 1000; stop > System.currentTimeMillis();) {
                        if(redButton.getState()){
                            score++;
                            System.out.println("Score: " + score);
                            reset2 = false;
                            break;
                            
                        } else if (greenButton.getState()) {
                            reset2 = true;
                            System.out.println("Oh no! Wrong button!");
                            break;
                        } else{
                            reset2 = true;
                        }
                        
                    }

                    //setting that you have missed a LED
                    if(reset2 == true){
                        System.out.println("Score Reset: " + score);
                        missedCounter++;
                    }
                    
                    //turns off led
                    redLED.setState(false);

                break;
                }
            }
        }
    }

    //method for decreasing the time
    public static long timeDecrementer(){

        //a non linear time function that decreases the time all the way to 0.2 seconds and steadies there
        number = (long) (((4) * Math.pow(1.3, -roundCounter) + 0.15) * 1000);
        System.out.println(number);
                          
        return number;
    }
    
    
}
