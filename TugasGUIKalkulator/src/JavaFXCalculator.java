/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafxcalculator.fxml.KalkulatorController;

/**
 *
 * @author tybobobo
 */
public class JavaFXCalculator extends Application
{
    String appName = "Kalkulator - Pintar";
    String version = "v1.0.0";
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("kalkulator.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle(appName + " - " + version);
        primaryStage.getIcons().add(new Image("https://www.freeiconspng.com/uploads/calculator-icon-4.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
