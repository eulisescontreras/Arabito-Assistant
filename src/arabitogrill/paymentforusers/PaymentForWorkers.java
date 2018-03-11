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
import javafx.fxml.Initializable;
import arabitogrill.model.DaysDAO;
import arabitogrill.model.Days;
import java.sql.Time;
import arabitogrill.main.MainController;
/**
 *
 * @author eulis
 */
public class PaymentForWorkers implements Initializable  {
    
    @FXML
    private JFXTextField amount;
    @FXML
    private JFXTextField tips;
    @FXML
    private JFXTextField time;
    private String userid;
    private Date date;
    private int dayid;
    private double daily_s;
    private boolean isExecute = false;
    
    private DaysDAO daysdao = new DaysDAO();
    private MainController mc;
    
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        amount.setDisable(true);
        time.textProperty().addListener((observable, oldValue, newValue) -> {
            String value = null;
            int firstIntValue;
            int secondIntValue;
            
            try{
                if(isExecute && newValue.toString().split(":").length == 3 && newValue.toString().split(":")[0].length() == 2 && newValue.toString().split(":")[1].length() == 2 && newValue.toString().split(":")[2].length() == 2)    
                {
                    double firstValue = Double.parseDouble(newValue.toString().split(":")[0])*this.daily_s;
                    double secondValue = (Double.parseDouble(newValue.toString().split(":")[1])*this.daily_s)/60;
                    /*firstIntValue = (int)firstValue;
                    secondIntValue = (int)secondValue;
                    value = firstIntValue+"."+secondIntValue;*/
                    amount.setText((""+(firstValue+secondValue)).toString());
                }
                isExecute = true;
            }catch(Exception ex){}
        });
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)amount.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void save(ActionEvent event) {
        Days days = new Days();
        days.setTips(Double.parseDouble(this.tips.getText()));
        days.setWorker(Integer.parseInt(this.userid));
        days.setDate(this.date); 
        days.setHours(Time.valueOf(this.time.getText().toString()));
        days.setId(this.dayid);
        if(dayid == -1){    
            daysdao.create(days);
            mc.initCalendar();
        }else{
            daysdao.update(days);
            mc.initCalendar();
        }
        Stage stage = (Stage)amount.getScene().getWindow();
        stage.close();
    }
    
    public void setAmount(String amount)
    {
        this.amount.setText(amount);
    }
    
    public void setTips(String tips)
    {
        this.tips.setText(tips);
    }
    
    public void setTime(String time)
    {
        this.time.setText(time);
    }
    
    public void setMain(MainController main)
    {
        this.mc = main;
    }
    
    public void setUserId(String userid)
    {
        this.userid = userid;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public void setDayId(int dayid)
    {
        this.dayid = dayid;
    }
    
    public void setDaily_s(double daily_s)
    {
        this.daily_s = daily_s;
    }
    
}
