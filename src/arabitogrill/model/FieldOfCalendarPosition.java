/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.model;

/**
 *
 * @author eulis
 */
public class FieldOfCalendarPosition {
    private String amount;
    private String tips;
    private String day;
    private String time;
    
    FieldOfCalendarPosition(){
        this.tips = "0";
        this.amount = "0";
        this.time = "00:00:00";
    }
    
    public String getAmount(){
        return amount;
    }
    
    public void setAmount(String amount){
        this.amount = amount;
    }
    
    public String getTips(){
        return tips;
    }
    
    public void setTips(String tips){
        this.tips = tips;
    }
    
    public String getDay(){
        return day;
    }
    
    public void setDay(String day){
        this.day = day;
    }
    
    public String getTime(){
        return time;
    }
    
    public void setTime(String time){
        this.time = time;
    }
}
