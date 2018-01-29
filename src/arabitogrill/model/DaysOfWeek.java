/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.model;

import arabitogrill.model.FieldOfCalendarPosition;

/**
 *
 * @author eulis
 */
public class DaysOfWeek {
    private FieldOfCalendarPosition mondayCol = new FieldOfCalendarPosition();
    private FieldOfCalendarPosition tuesdayCol = new FieldOfCalendarPosition();
    private FieldOfCalendarPosition wednesdayCol = new FieldOfCalendarPosition();
    private FieldOfCalendarPosition thursdayCol = new FieldOfCalendarPosition();
    private FieldOfCalendarPosition fridayCol = new FieldOfCalendarPosition();
    private FieldOfCalendarPosition saturdayCol = new FieldOfCalendarPosition();
    private FieldOfCalendarPosition sundayCol = new FieldOfCalendarPosition();
        
    public FieldOfCalendarPosition getMondayCol(){
        return mondayCol;
    }
    public FieldOfCalendarPosition getTuesdayCol(){
        return tuesdayCol;
    }
    public FieldOfCalendarPosition getWednesdayCol(){
        return wednesdayCol;
    }
    public FieldOfCalendarPosition getThursdayCol(){
        return thursdayCol;
    }
    public FieldOfCalendarPosition getFridayCol(){
        return fridayCol;
    }
    public FieldOfCalendarPosition getSaturdayCol(){
        return saturdayCol;
    }
    public FieldOfCalendarPosition getSundayCol(){
        return sundayCol;
    }
    
    public void setMondayCol(FieldOfCalendarPosition mondayCol){
        this.mondayCol = mondayCol;
    }
    public void setTuesdayCol(FieldOfCalendarPosition tuesdayCol){
        this.tuesdayCol = tuesdayCol;
    }
    public void setWednesdayCol(FieldOfCalendarPosition wednesdayCol){
        this.wednesdayCol = wednesdayCol;
    }
    public void setThursdayCol(FieldOfCalendarPosition ThursdayCol){
        this.thursdayCol = ThursdayCol;
    }
    public void setFridayCol(FieldOfCalendarPosition fridayCol){
        this.fridayCol = fridayCol;
    }
    public void setSaturdayCol(FieldOfCalendarPosition saturdayCol){
        this.saturdayCol = saturdayCol;
    }
    public void setSundayCol(FieldOfCalendarPosition SundayCol){
        this.sundayCol = SundayCol;
    }
}
