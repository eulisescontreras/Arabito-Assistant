/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.editbill;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import arabitogrill.model.BillsDAO;
import arabitogrill.model.ConnectionFactory;
import arabitogrill.model.Workers;
import arabitogrill.model.Bills;
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
public class BillEditController {
    
    private BillsDAO billsdao = new BillsDAO();
    
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
    
    private Bills billEdit;
    private TableView tableView;

    // Public no-args constructor
    public BillEditController() {
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
                	amount.setStyle("-fx-text-box-border: red ;\n" + 
                			"  -fx-focus-color: red ;");
                }
                else {
                	amount.setStyle("-fx-text-box-border: green ;\n" + 
                			"  -fx-focus-color: green ;");
                }
            }
        });
    	
    	/*for(Bills worker : wdao.read("")) {
    		System.out.println(worker);
    	}*/
    	
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)amount.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void save(ActionEvent event) {
<<<<<<< HEAD
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
    	bill.setSpend(billEdit.getSpend());
    	bill.setId(billEdit.getId());
    	
        bdao.update(bill);
        tableView.refresh();
        tableView.setItems(billsdao.getObservableBills());

        
        Stage stage = (Stage)amount.getScene().getWindow();
        stage.close();
=======
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
	    	bill.setSpend(billEdit.getSpend());
	    	bill.setId(billEdit.getId());
	    	
	        bdao.update(bill);
	        tableView.refresh();
	        tableView.setItems(billsdao.getObservableBills());
	
	        Stage stage = (Stage)amount.getScene().getWindow();
	        stage.close();
    	}
>>>>>>> 93792553443868b3e66e6dd2ca4296e22c9439fd
    }
    
    public void initData(Bills bill, TableView tableView) {
        billEdit = bill;

        this.tableView = tableView;
        amount.setText(bill.getAmount().toString());
        createdAt.setValue(bill.getCreatedAt().toLocalDate());
	    expirationAt.setValue(bill.getExpirationAt().toLocalDate());
	}

}
