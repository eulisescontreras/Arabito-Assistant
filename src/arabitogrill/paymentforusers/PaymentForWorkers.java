/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.paymentforusers;

import arabitogrill.model.Workers;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;

/**
 *
 * @author eulis
 */
public class PaymentForWorkers {
    
    @FXML
    private JFXTextField amount;
    @FXML
    private JFXTextField tips;
    
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)amount.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void save(ActionEvent event) {
        
    }
}
