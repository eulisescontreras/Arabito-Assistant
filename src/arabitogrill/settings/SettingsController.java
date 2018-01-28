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

import arabitogrill.model.ConnectionFactory;
import arabitogrill.model.Workers;
import arabitogrill.model.WorkersDAO;

import java.io.BufferedWriter;
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
import arabitogrill.main.MainController;
import static arabitogrill.main.MainController.toolbarUsersFXML;
import javafx.fxml.FXMLLoader;
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
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)housePercent.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void save(ActionEvent event) {
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
        
        /*this.mainController.getToolbarController().refreshTableView();*/
        Stage stage = (Stage)housePercent.getScene().getWindow();
        stage.close();
    }

}
