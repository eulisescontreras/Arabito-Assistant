/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill;

import arabitogrill.util.ArabitoGrillUtil;
import java.io.IOException;
import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


/**
 *
 * @author eulis
 */
public class ArabitoGrill extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/arabitogrill/login/login.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        ArabitoGrillUtil.setStageIcon(primaryStage);
        primaryStage.show();
        primaryStage.setTitle("Library Assistant Login");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
