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
public class DaysOfWeek {
    private String mondayCol;
    private String tuesdayCol;
    private String wednesdayCol;
    private String thursdayCol;
    private String fridayCol;
    private String saturdayCol;
    private String sundayCol;
        
    public String getMondayCol(){
        return mondayCol;
    }
    public String getTuesdayCol(){
        return tuesdayCol;
    }
    public String getWednesdayCol(){
        return wednesdayCol;
    }
    public String getThursdayCol(){
        return thursdayCol;
    }
    public String getFridayCol(){
        return fridayCol;
    }
    public String getSaturdayCol(){
        return saturdayCol;
    }
    public String getSundayCol(){
        return sundayCol;
    }
    
    public void setMondayCol(String mondayCol){
        this.mondayCol = mondayCol;
    }
    public void setTuesdayCol(String tuesdayCol){
        this.tuesdayCol = tuesdayCol;
    }
    public void setWednesdayCol(String wednesdayCol){
        this.wednesdayCol = wednesdayCol;
    }
    public void setThursdayCol(String ThursdayCol){
        this.thursdayCol = ThursdayCol;
    }
    public void setFridayCol(String fridayCol){
        this.fridayCol = fridayCol;
    }
    public void setSaturdayCol(String saturdayCol){
        this.saturdayCol = saturdayCol;
    }
    public void setSundayCol(String SundayCol){
        this.sundayCol = SundayCol;
    }
}
