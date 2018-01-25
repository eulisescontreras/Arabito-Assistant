/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.addmember;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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

    // Public no-args constructor
    public MemberAddController() {
    }
    
    private WorkersDAO wdao;
    
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
        //wdao.create(new Workers(name.getText(),email.getText().toString(),mobile.getText().toString(),charge.getText().toString(),new BigDecimal(dailyS.getText().toString()),new Date(birth.)));
    }

}
