package Phidget; 
//Add Phidgets Library 
import com.phidget22.*; 
import java.util.Random; 
import java.util.Timer; 
import java.util.concurrent.TimeUnit;
 public class Phidget { 
private static final Random RAND = new Random(); 
private static final Timer TIME = new Timer(); 
private static long startTime = System.currentTimeMillis(); 
private static int x = RAND.nextInt(2) + 1; 
private static int on = RAND.nextInt(200) + 750; 
//Handle Exceptions 
public static void main(String[] args) throws Exception { 
int score = 0; //Create DigitalInput redButton = new DigitalInput(); 
DigitalOutput redLED = new DigitalOutput(); DigitalInput greenButton = new DigitalInput(); DigitalOutput greenLED = new DigitalOutput(); //Address redButton.setHubPort(0); redButton.setIsHubPortDevice(true); redLED.setHubPort(1); redLED.setIsHubPortDevice(true); greenButton.setHubPort(5); greenButton.setIsHubPortDevice(true); greenLED.setHubPort(4); greenLED.setIsHubPortDevice(true); //Open redButton.open(1000); redLED.open(1000); greenButton.open(1000); greenLED.open(1000); System.out.println("Welcome to Whack A Mole!"); System.out.println("Make sure to hold down the button until the light turns off!"); System.currentTimeMillis(); //Use your Phidgets while (true) { Randomizer(); Thread.sleep(3000); switch(x) { case 1: greenLED.setState(true); Thread.sleep(on); greenLED.setState(false); if(greenButton.getState()){ score++; System.out.println("Score: " + score); } else if (redButton.getState()) { score = 0; System.out.println("Oh no! Wrong button! Score: " + score); } else { score = 0; System.out.println("Too slow! Score: " + score); } break; case 2: redLED.setState(true); Thread.sleep(on); redLED.setState(false); if(redButton.getState()){ score++; System.out.println("Score: " + score); } else if(greenButton.getState()){ score = 0; System.out.println("Oh no! Wrong button! Score: " + score); } else { score = 0; System.out.println("Too slow! Score: " + score); } break; } } } public static void Randomizer() { if(startTime >= 10000 && startTime < 20000) { on = RAND.nextInt(200) + 750; } else if(startTime >= 20000 && startTime < 30000) { on = RAND.nextInt(150) + 500; } else if(startTime >= 30000) { on = RAND.nextInt(100) + 250; } } }
