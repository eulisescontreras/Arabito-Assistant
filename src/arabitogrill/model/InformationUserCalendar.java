/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.model;

import java.sql.Date;

/**
 *
 * @author eulis
 */
public class InformationUserCalendar {
    private int year;
    private int month;
    private int day;
    private double hour;
    private double tips;
    private int minutes;
    private Date date;
    private double amount;
    private String userid;
    private int dayid;
    private double daily_s;
    
    public InformationUserCalendar(){
    
    }
    InformationUserCalendar(double amount, double tips, int year, int month, int day, int minutes, double hour, Date date, int dayid, double daily_s){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
        this.date = date;
        this.tips = tips;
        this.amount = amount;
        this.dayid = dayid;
        this.daily_s = daily_s;
    }

    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }

    public int getDayId(){
        return dayid;
    }
    
    public void setDayId(int dayid){
        this.dayid = dayid;
    }    

    public int getYear(){
        return year;
    }
    
    public void setYear(int year){
        this.year = year;
    }
    
    public int getMonth(){
        return month;
    }
    
    public void setMonth(int month){
        this.month = month;
    }
    
    public int getDay(){
        return day;
    }
    
    public void setDay(int day){
        this.day = day;
    }
    
    public double getHour(){
        return hour;
    }
    
    public void setHour(double hour){
        this.hour = hour;
    }
    
    public int getMinutes(){
        return minutes;
    }
    
    public void setMinutes(int minutes){
        this.minutes = minutes;
    }

    public double getAmount(){
        return amount;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    public double getTips(){
        return tips;
    }
    
    public void setTips(double tips){
        this.tips = tips;
    }
    
    public String getUserId(){
        return userid;
    }
    
    public void setUserId(String userid){
        this.userid = userid;
    }
    
    public double getDaily_s(){
        return daily_s;
    }
    
    public void setDaily_s(double daily_s){
        this.daily_s = daily_s;
    }
}
