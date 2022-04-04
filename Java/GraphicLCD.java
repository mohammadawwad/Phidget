package phidget;

//Add Phidgets Library
import com.phidget22.*;
import java.util.Scanner;

public class GraphicLCD{
    public static void main(String[] args) throws Exception{
        
        //Creates lcd screen
        LCD lcd = new LCD();
        
        //Open
        lcd.open(3000);
        
        //welcome message
        System.out.println("Welcome, Type in the console to output on the screen");
        
        boolean continueText;
        
        do{        
            //creates scaner
            Scanner input = new Scanner(System.in);
            String yourText= input.nextLine();


            //Use your Phidgets
            lcd.clear();
            lcd.writeText(LCDFont.DIMENSIONS_6X12, 0, 0, yourText);
            lcd.flush();
            
            if(yourText == "exit"){
                //Use your Phidgets
                lcd.clear();
                lcd.writeText(LCDFont.DIMENSIONS_6X12, 0, 0, "Closing Program");
                lcd.flush();
                continueText = false;
            }
            else {
                continueText = true;
            }
        }while(continueText == true);

    }
}