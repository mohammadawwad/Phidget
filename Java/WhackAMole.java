//Add Phidgets Library
package phidget;

import com.phidget22.*;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class WhackAMole { 
    
    private static Random RAND = new Random();
    private static Timer TIME = new Timer();
    private static long startTime = System.currentTimeMillis(); private static int lightDecider;
    private static long timeOn; private static long number = 5000;
    private static int value;
    private static int y; 
    
    //Handle Exceptions
    public static void main(String[] args) throws Exception { 

        int score = 0;
        int lightTime = 5;

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
        greenLED.open(1000); System.out.println("Welcome to Whack A Mole!");
        System.out.println("Make sure to hold down the button until the light turns off!"); System.currentTimeMillis(); 

        //Use your Phidgets
        while (true) {
            
            //Randomizer();
            Thread.sleep(3000); 
            
            //decides which light will turn on
            lightDecider = RAND.nextInt(2) + 1;
            
            //decrements the time the light is on for
            timeDecrementer();
            timeOn = number; 
            
            if (timeOn > 0){
                switch(lightDecider) {
                    case 1:
                        greenLED.setState(true); boolean reset = false;
                        
                        for (long stop=System.nanoTime()+TimeUnit.SECONDS.toMillis(timeOn);stop>System.nanoTime();) {
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
                        
                        for (long stop=System.nanoTime()+TimeUnit.SECONDS.toMillis(timeOn);stop>System.nanoTime();) {
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
    
        //decrements the time by 500ms each time
        public static long timeDecrementer(){
            number -= 500;
            System.out.println(number);
            return number;
    }
}

