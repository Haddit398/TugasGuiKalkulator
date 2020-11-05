/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcalculator.fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class KalkulatorController implements Initializable {
    
     @FXML
    TextArea texthasil;
           
    private ArrayList values = new ArrayList(); 
    private boolean hasOperator = false; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    @FXML
    private void angka(ActionEvent event)
    {  
        Button button = (Button)event.getSource();
        String buttonText = button.getText();
        
        addtotext(buttonText);
    }  
    
    private void addtotext(String text)
    { 
        values.add(text);
        if("0".equals(texthasil.getText()) )
        {
            texthasil.setText("");
        }
        texthasil.setText(texthasil.getText() + text);
    }
    
    @FXML
    private void resetaction(ActionEvent event)
    { 
        values.clear();
        texthasil.setText("");
        hasOperator = false; 
    }
    
     @FXML
    private void operatorbtn(ActionEvent event)
    { 
        Button button = (Button)event.getSource();
        String operatorText = button.getText();
        pressOperator(operatorText);       
    }
 
     private void pressOperator(String operator)
    { 
        if(!"=".equals(operator))
        { 
            if(isOperator(values.get(values.size()-1).toString()))
            {
                return;
            }
            
            // Store operator in values
            values.add(operator);
            
            hasOperator = true;
            
            // Display in output
            addtotext(operator);
        } 
        else 
        {
             hitung_total();
        }
    }
    
    private void hitung_total()
    {
        // Variables for computational tasks during this part alone
        int result = 0;
        String stringOperator = "";
        String stringValue1 = "";
        String stringValue2 = "";

        try
        {
            // If an operator has previously been assigned, ignore key press
            if(values.size() == 0 || isOperator(values.get(values.size()-1).toString()) || !hasOperator)
            {
                return;
            }
            

            // Calculate the total value
            for(Iterator<String> i = values.iterator(); i.hasNext();)
            {
                // Get text
                String item = i.next();
                
                // If it is an operator
                if(isOperator(item))
                {
                    // Calculate previous values and add to value1 - then set latest operator
                    if(!"".equals(stringOperator))
                    {
                        // Calculate previously stored
                        result = hitung(Integer.parseInt(stringValue1), Integer.parseInt(stringValue2), stringOperator);
                        stringValue1 = result + ""; // Add calculated value as first result
                        stringValue2 = result + "";
                    }
                    stringOperator = item;
                }
                else
                {
                    // If no operator has previously been assigned, just append values to first value
                    if("".equals(stringOperator))
                    {
                        // Append values on eachother
                        stringValue1 = stringValue1 + item;
                    }
                    else
                    {
                        // Operator have been assigned, which means we already have a value1 - add to string value 2 instead
                        stringValue2 = stringValue2 + item;
                    }
                }
                
                // If this is our last loop, calculate total and add into result
                if(!i.hasNext())
                {
                     result = hitung(Integer.parseInt(stringValue1), Integer.parseInt(stringValue2), stringOperator);
                }        
            }
                           

            // Output results
            texthasil.setText(result + "");
            
            // Clear stored values
            values.clear();
             
            hasOperator = false;
            
        }
        catch(Exception ex)
        { System.out.println("bil 1: " + stringValue1+"bil 2:"+stringValue2);
            // Output results
            texthasil.setText(0 + "");
            
            // Clear stored values
            values.clear();
             
            hasOperator = false;
        } 
    }
    
     // Calculate two numbers and return the result
    public Integer hitung(int num1, int num2, String operator)
    {

        switch(operator)
        {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                // This if statement prevents the creation of black holes. You are welcome, earth.
                if(num2 == 0)
                {
                    return 0;
                }
                return num1 / num2;
            case "%":
                return num1 % num2;
            default:
                // Error
                System.out.println("Undefined operator pressed: " + operator);
                return 0;
        }
    }
    
      // Returns true if the string is an operator
    private boolean isOperator(String operator)
    {
        switch(operator)
        {
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                return true;
            default:
                return false;
        }
    }

}
