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
    private String varoperator; 
    private int num1;
    private int num2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    //fungsi untuk tombol angka, dsini get text dari button
    @FXML
    private void angka(ActionEvent event)
    {  
        Button button = (Button)event.getSource();
        String buttonText = button.getText();
        
        addtotext(buttonText);
    }  
    
    //jika input pertama 0 maka kosong dan tidak ada tambahan 03113(tidak bisa), jika bukan maka tampilkan dan disambung tambahan 121313 
    private void addtotext(String text)
    { 
        values.add(text);
        if("0".equals(texthasil.getText()) )
        {
            texthasil.setText("");
        }
        texthasil.setText(texthasil.getText() + text);
    }
    
    //untuk hapus semua
    @FXML
    private void resetaction(ActionEvent event)
    { 
        values.clear();
        texthasil.setText("");
    }
    
    //function untuk operator
     @FXML
    private void operatorbtn(ActionEvent event)
    { 
        Button button = (Button)event.getSource();
        String operatorText = button.getText();
        pressOperator(operatorText);       
    }
    
    //jika bukan = 
     private void pressOperator(String operator)
    {  
            if(isOperator(values.get(values.size()-1).toString()))
            {
                return;
            }
            
            num1 = Integer.parseInt(texthasil.getText());
            values.add(operator); 
            texthasil.setText(""); 
//            System.out.println("operator"+operator); 
            varoperator = operator;
    }
    
    @FXML
    private void btnhasil(ActionEvent event)
    { 
        Integer hasil = 0;
        num2 = Integer.parseInt(texthasil.getText());
//        System.out.println("num1" + num1+"num2"+num2+"operator"+varoperator); 
        hasil = hitung(num1, num2, varoperator);
        
        texthasil.setText(hasil + "");
    }
    
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
                if(num2 == 0)
                {
                    return 0;
                }
                return num1 / num2;
            case "%":
                return num1 % num2;
            default:
                System.out.println("Undefined operator pressed: " + operator);
                return 0;
        }
    }
    
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
