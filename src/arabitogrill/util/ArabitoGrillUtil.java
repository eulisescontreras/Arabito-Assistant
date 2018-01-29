/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.util;

import arabitogrill.main.MainController;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import arabitogrill.addmember.MemberAddController;
import arabitogrill.model.InformationUserCalendar;
import arabitogrill.paymentforusers.PaymentForWorkers;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/**
 *
 * @author eulis
 */
public class ArabitoGrillUtil {
    
    private static final String IMAGE_LOC = "/resources/ArabitoGrillIco.jpg";
    static public FXMLLoader addmember;
    static private MemberAddController controllerMember;
    static private PaymentForWorkers controllerPayment;
    
    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(IMAGE_LOC));
    }
    
    public static void loadWindow(URL loc, String title, MainController main, boolean isWorkers, boolean isCalendar, InformationUserCalendar iuc) {
        try {
            addmember = new FXMLLoader(loc);
            Parent parent = (Parent)addmember.load();
            if(isWorkers)
            {
                controllerMember = (MemberAddController)addmember.getController();
                controllerMember.setMainController(main);
            }
            
            if(isCalendar)
            {
                controllerPayment = (PaymentForWorkers)addmember.getController();   
                controllerPayment.setAmount((""+iuc.getAmount()).toString());
                controllerPayment.setTips((""+iuc.getTips()).toString());
                controllerPayment.setUserId(iuc.getUserId());
                controllerPayment.setTime((""+iuc.getHour()).toString().replace(".0","")+":"+iuc.getMinutes()+":00");
                controllerPayment.setDate(iuc.getDate());
                controllerPayment.setMain(main);
                controllerPayment.setDayId(iuc.getDayId());
                controllerPayment.setDaily_s(iuc.getDaily_s());
            }
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            setStageIcon(stage);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public static String dayName(String inputDate, String format) throws ParseException{
        String dayOfWeek = null;
        try {
            dayOfWeek = new SimpleDateFormat(format).parse(inputDate).toString().split(" ")[0];
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return getDayOfWeeks(dayOfWeek);
    }
    
    public static Integer monthNumber(String month)
    {
        Integer result = null;
        switch(month)
        {
            case "January":
                result = 1;
                break;
            case "Febraury":
                result = 2;
                break;
            case "March":
                result = 3;
                break;                                                         
            case "April":
                result = 4;
                break;
            case "May":
                result = 5;
                break;
            case "June":
                result = 6;
                break;
            case "July":
                result = 7;
                break;
            case "August":
                result = 8;
                break;
            case "September":
                result = 9;
                break;
            case "October":
                result = 10;
                break;
            case "November":
                result = 11;
                break;
            case "December":
                result = 12;
                break;
        }
        return result;
    }
    
    public static String getDayOfWeeks(String day)
    {
        String result = null;
        switch(day)
        {
            case "Mon":
                result = "Monday";
                break;
            case "Tue":
                result = "Tuesday";
                break;
            case "Wed":
                result = "Wednesday";
                break;                                                         
            case "Thu":
                result = "Thursday";
                break;
            case "Fri":
                result = "Friday";
                break;
            case "Sat":
                result = "Saturday";
                break;
            case "Sun":
                result = "Sunday";
                break;
        }
        return result;
    }
}
