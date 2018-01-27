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
/**
 *
 * @author eulis
 */
public class MemberAddController {
    
    @FXML
    private JFXTextField name;
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
    
    // location and resources will be automatically injected by the FXML loader
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
    
    private WorkersDAO wdao;

    // Public no-args constructor
    public MemberAddController() {
    }
    
    @FXML
    private void initialize() {
    	wdao = new WorkersDAO();
    	
    	/*for(Workers worker : wdao.read("")) {
    		System.out.println(worker);
    	}*/
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)name.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void save(ActionEvent event) {
    	Workers worker = new Workers();
    	String [] dateS = birth.getEditor().getText().split("/");
    	
    	Calendar cal = Calendar.getInstance();
    	cal.set(Integer.parseInt(dateS[2]), 
    			Integer.parseInt(dateS[0])-1, 
    			Integer.parseInt(dateS[1]));
    	
    	Date date = new Date(cal.getTimeInMillis());
    	
    	worker.setName(name.getText().toString());
    	worker.setEmail(email.getText().toString());
    	worker.setCharge(charge.getText().toString());
    	worker.setMobile(mobile.getText().toString());
    	worker.setDailyS(new BigDecimal(dailyS.getText()));
    	worker.setBirth(date);
    	
        wdao.create(worker);
        
        Stage stage = (Stage)name.getScene().getWindow();
        stage.close();
    }

}
