/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.addmember;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import arabitogrill.model.ConnectionFactory;
import arabitogrill.model.Workers;
import arabitogrill.model.WorkersDAO;
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
public class MemberAddController {
    
    @FXML
    private JFXTextField first_name;
    @FXML
    private JFXTextField second_name;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField second_surname;
    @FXML
    private JFXTextField charge;
    @FXML
    private JFXDatePicker birth;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField dailyS;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton; 
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
    private MainController mainController;
    private WorkersDAO wdao;

    // Public no-args constructor
    public MemberAddController() {
    }
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    
    @FXML
    private void initialize() {
    	wdao = new WorkersDAO();
    	
    	first_name.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
                if(first_name.getText().trim().length()==0){
                	first_name.getStyleClass().add("wrong-credentials");
                }
            }
        });
    	
    	surname.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
                if(surname.getText().trim().length()==0){
                	surname.getStyleClass().add("wrong-credentials");
                }
            }
        });
    	
    	dailyS.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!dailyS.getText().matches("[0-9]+(\\.[0-9]+)*")){
                	dailyS.setText("");
                }
                
                if(dailyS.getText().trim().length()==0){
                	dailyS.getStyleClass().add("wrong-credentials");
                }
            }
        });
        
        charge.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(charge.getText().trim().length()==0){
                	charge.getStyleClass().add("wrong-credentials");
                }
            }
        });
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)first_name.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void save(ActionEvent event) {
    	if(first_name.getText().trim().length()!=0 
    			&& surname.getText().trim().length()!=0 
    			&& dailyS.getText().trim().length()!=0
                        && charge.getText().trim().length()!=0 ) {
            Workers worker = new Workers();
            String [] dateS = birth.getEditor().getText().split("/");

            Calendar cal = Calendar.getInstance();
            Date date = new Date(cal.getTimeInMillis());
            if(dateS.length > 1)
            {
                cal.set(Integer.parseInt(dateS[2]), 
                                Integer.parseInt(dateS[0])-1, 
                                Integer.parseInt(dateS[1]));
                date = new Date(cal.getTimeInMillis());
            }
            
            worker.setFirstName(first_name.getText().toString());
            worker.setSecondName(second_name.getText().toString() != "" ? "-----" : second_name.getText().toString());
            worker.setSurname(surname.getText().toString());
            worker.setSecondSurname(second_surname.getText().toString() != "" ? "-----" : second_surname.getText().toString());
            worker.setEmail(email.getText().toString() != "" ? "-----" : email.getText().toString());
            worker.setCharge(charge.getText().toString());
            worker.setMobile(mobile.getText().toString() != "" ? "-----" : mobile.getText().toString());
            worker.setDailyS(new BigDecimal(dailyS.getText()));
            worker.setBirth(date);

            wdao.create(worker);
            this.mainController.getToolbarController().refreshTableView();
            Stage stage = (Stage)first_name.getScene().getWindow();
            stage.close();
    	}
    }

}
