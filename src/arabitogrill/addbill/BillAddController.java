/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.addbill;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import arabitogrill.model.BillsDAO;
import arabitogrill.model.ConnectionFactory;
import arabitogrill.model.Bills;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author eulis
 */
public class BillAddController {
    
    @FXML
    private JFXTextField amount;
    @FXML
    private JFXDatePicker createdAt;
    @FXML
    private JFXDatePicker expirationAt;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    
    // location and resources will be automatically injected by the FXML loader
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
    
    private BillsDAO bdao;

    // Public no-args constructor
    public BillAddController() {
    }
    
    @FXML
    private void initialize() {
    	bdao = new BillsDAO();
    	
    	amount.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!amount.getText().matches("[0-9]+(\\.[0-9]+)*")){
                	amount.setText("");
                }
                
                if(amount.getText().trim().length()==0){
                	amount.getStyleClass().add("wrong-credentials");
                }
            }
        });   	
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)amount.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void save(ActionEvent event) {
    	 if(amount.getText().trim().length()!=0) {
	    	Bills bill = new Bills();
	    	String [] dateC = createdAt.getEditor().getText().split("/");
	    	String [] dateE = expirationAt.getEditor().getText().split("/");
	    	
	    	Calendar cal = Calendar.getInstance();
	    	
	    	cal.set(Integer.parseInt(dateC[2]), 
	    			Integer.parseInt(dateC[0])-1, 
	    			Integer.parseInt(dateC[1]));
	    	
	    	Date dc = new Date(cal.getTimeInMillis());
	    	
	    	cal.set(Integer.parseInt(dateE[2]), 
	    			Integer.parseInt(dateE[0])-1, 
	    			Integer.parseInt(dateE[1]));
	    	
	    	Date de = new Date(cal.getTimeInMillis());
	    	
	    	bill.setAmount(new BigDecimal(amount.getText()));
	    	bill.setCreatedAt(dc);
	    	bill.setExpirationAt(de);
	    	bill.setSpend("WAITING");
	    	
	        bdao.create(bill);
	        
	        Stage stage = (Stage)amount.getScene().getWindow();
	        stage.close();
    	 }
    }

}
