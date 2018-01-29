/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.settings;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import arabitogrill.main.toolbaruser.ToolbarController;
import arabitogrill.model.*;
import arabitogrill.ArabitoGrill;
import arabitogrill.main.MainController;
import static arabitogrill.main.MainController.toolbarUsersFXML;
import javafx.fxml.FXMLLoader;
import arabitogrill.consts.Consts;
/**
 *
 * @author eulis
 */
public class SettingsController {
    
    @FXML
    private JFXTextField housePercent;
    @FXML
    private JFXTextField workerPercent;
    @FXML
    private JFXTextField initYear;
    @FXML
    private JFXTextField endYear;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton; 
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
    private MainController mainController;

    // Public no-args constructor
    public SettingsController() {
    }
    
    @FXML
    private void initialize() {
    	ArabitoGrill arabito = new ArabitoGrill();
    	
    	arabito.getSetting();
    	
    	housePercent.setText(arabito.getPerH().toString());
    	workerPercent.setText(arabito.getPerW().toString());
    	initYear.setText(arabito.getIniY().toString());
    	endYear.setText(arabito.getEndY().toString());
    	
    	housePercent.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!housePercent.getText().matches("[0-9]{1,2}(\\.[0-9]{0,2}){0,1}")){
                	housePercent.setText("");
                }
                
                if(housePercent.getText().trim().length()==0){
                	housePercent.getStyleClass().add("wrong-credentials");
                }
            }
        });
    	
    	workerPercent.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!workerPercent.getText().matches("[0-9]{1,2}(\\.[0-9]{0,2}){0,1}")){
                	workerPercent.setText("");
                }
                
                if(workerPercent.getText().trim().length()==0){
                	workerPercent.getStyleClass().add("wrong-credentials");
                }
            }
        });
    	
    	initYear.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!initYear.getText().matches("[0-9]*")){
                	initYear.setText("");
                }
                
                if(initYear.getText().trim().length()==0){
                	initYear.getStyleClass().add("wrong-credentials");
                }
            }
        });
    	
    	endYear.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!endYear.getText().matches("[0-9]*")){
                	endYear.setText("");
                }
                
                if(endYear.getText().trim().length()==0){
                	endYear.getStyleClass().add("wrong-credentials");
                }
            }
        });
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)housePercent.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void save(ActionEvent event) {
    	if(endYear.getText().trim().length()!=0 &&
    			initYear.getText().trim().length()!=0 &&
    					workerPercent.getText().trim().length()!=0 &&
    							housePercent.getText().trim().length()!=0) {
		 try (BufferedWriter bw = new BufferedWriter(new FileWriter("settings_arabitogrill.txt", false))) {
		        bw.write(housePercent.getText());
		        bw.newLine();
		        bw.write(workerPercent.getText());
		        bw.newLine();
		        bw.write(initYear.getText());
		        bw.newLine();
		        bw.write(endYear.getText());
		        bw.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	        Consts.endYears = Integer.parseInt(endYear.getText());
                Consts.startYears = Integer.parseInt(initYear.getText());
	        /*this.mainController.getToolbarController().refreshTableView();*/
	        Stage stage = (Stage)housePercent.getScene().getWindow();
	        stage.close();
    	}
    }

}
