//Add Phidgets Library
import com.phidget22.*;
import java.util.Scanner;

public class GraphicLCD{
    public static void main(String[] args) throws Exception{
        
        //Creates lcd screen
        LCD lcd = new LCD();
        
        //declaring lcd port in relation to the hub
        lcd.open(3000);
        
        //welcome message
        System.out.println("Welcome, Type in the console to output on the screen");
        
        boolean continueText;
        
        do{        
            //creates scaner
            Scanner input = new Scanner(System.in);
            String yourText= input.nextLine();


            //Use your Phidgets
            lcd.flush();
            lcd.writeText(LCDFont.DIMENSIONS_6X12, 0, 0, yourText);
            lcd.flush();

            //stops showing output if you type exit
            if(yourText == "exit"){
                continueText = false;
            }
            else {
                continueText = true;
            }
        }while(continueText == true);

    }
}