/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import arabitogrill.util.ArabitoGrillUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import com.jfoenix.controls.JFXTabPane;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import arabitogrill.main.toolbaruser.ToolbarController;
import arabitogrill.consts.Consts;
import arabitogrill.model.Bills;
import arabitogrill.model.BillsDAO;
import arabitogrill.model.Days;
import arabitogrill.model.DaysDAO;
import arabitogrill.model.Workers;
import arabitogrill.model.WorkersDAO;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import arabitogrill.util.ArabitoGrillUtil;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import arabitogrill.model.DaysOfWeek;
import arabitogrill.model.FieldOfCalendarPosition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import arabitogrill.paymentforusers.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import arabitogrill.model.InformationUserCalendar;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.derby.iapi.store.raw.Page;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.printing.PDFPageable;
/**
 *
 * @author eulis
 */
public class MainController  implements Initializable {

    private final String CALENDAR_ID = "calendarTab";
    private final String REPORT_ID = "reportTab";
    private final int ALERT_DAY = 5;
    
    @FXML
    private TableColumn mondayCol;
    @FXML
    private TableColumn tuesdayCol;
    @FXML
    private TableColumn wednesdayCol;
    @FXML
    private TableColumn thursdayCol;
    @FXML
    private TableColumn fridayCol;
    @FXML
    private TableColumn saturdayCol;
    @FXML
    private TableColumn sundayCol;
    @FXML
    private TableView tableView;
    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTabPane footerTabPane;
    @FXML
    private JFXTabPane headerTabPane;    
    @FXML
    private JFXDrawer drawer;
    @FXML
    public JFXDrawer drawerUsers;
    @FXML
    private JFXTabPane mainTabPane;
    
    private MainController mc;
    public DaysDAO daysdao = new DaysDAO();
    static public boolean firstCharge = false;
    static public VBox toolbar;
    static public FXMLLoader toolbarUsersFXML;
    static public FXMLLoader toolbarFXML;
    static public VBox toolbarUsers;
    arabitogrill.main.toolbar.ToolbarController controllerMenu;
    private ToolbarController controller;
    
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {

          tableView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler() {
             @Override
             public void handle(Event event) {
                 if(!controller.getIdUser().equals("-1"))
                 {
                    InformationUserCalendar iuc = new InformationUserCalendar();
                    List<InformationUserCalendar> iucl = new ArrayList<>();
                    String month = ArabitoGrillUtil.monthNumber(headerTabPane.getTabs().get(headerTabPane.getSelectionModel().getSelectedIndex()).getText()).toString();
                    String year = footerTabPane.getTabs().get(footerTabPane.getSelectionModel().getSelectedIndex()).getText();
                    String day = event.getTarget().toString().split("text=")[1].split("Amount:")[0].trim().replace((""+'"'),"");
                    String amount = event.getTarget().toString().split("text=")[1].split("Amount:")[1].split("Tips:")[0].trim().replace("$ ","");
                    String tips =  event.getTarget().toString().split("text=")[1].split("Amount:")[1].split("Tips:")[1].split(""+'"')[0].trim().replace("$ ","").split("\n")[0];
                    String time =  event.getTarget().toString().split("text=")[1].split("Amount:")[1].split("Tips:")[1].split(""+'"')[0].trim().replace("$ ","").split("\n")[3].trim();

                    iucl = daysdao.read(Integer.parseInt(controller.getIdUser()));
                    iuc.setDayId(-1);
                    for(int k = 0; k < iucl.size(); k++)
                    {
                         if((""+iucl.get(k).getDay()).toString().equals(day) && (""+iucl.get(k).getMonth()).toString().equals(month) && (""+iucl.get(k).getYear()).toString().equals(year))
                         {
                             iuc.setDayId(iucl.get(k).getDayId());
                         }
                         iuc.setDaily_s(iucl.get(k).getDaily_s());
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.set(Integer.parseInt(year), 
                           Integer.parseInt(month)-1, 
                           Integer.parseInt(day));
                    Date date = new Date(cal.getTimeInMillis());

                    iuc.setDate(date);
                    iuc.setDay(Integer.parseInt(day));
                    iuc.setAmount(Double.parseDouble(amount));
                    iuc.setTips(Double.parseDouble(tips));
                    iuc.setHour(Double.parseDouble(time.split(":")[0]));
                    iuc.setMinutes(Integer.parseInt(time.split(":")[1]));
                    iuc.setUserId(controller.getIdUser());

                    ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/paymentforusers/PaymentForWorkers.fxml"), "Payment for Workers",mc,false,true,iuc);
                }
            }
         });
        
        mc = this;
        for(int i = Consts.startYears; i <= Consts.endYears; i++)
            footerTabPane.getTabs().add(new Tab(""+i+""));
        
        mondayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        tuesdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        wednesdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        thursdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        fridayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        saturdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        sundayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));

        mondayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getMondayCol().getDay()==null ? "":p.getValue().getMondayCol().getDay()+"\n\n\n   Amount: \n  $ "+p.getValue().getMondayCol().getAmount()+"\n\n   Tips: \n  $ "+p.getValue().getMondayCol().getTips()+"\n\n   Time: \n    "+p.getValue().getMondayCol().getTime()+"\n\n\n"));
            }
         });
        tuesdayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getTuesdayCol().getDay()==null ? "":p.getValue().getTuesdayCol().getDay()+"\n\n\n   Amount: \n  $ "+p.getValue().getTuesdayCol().getAmount()+"\n\n   Tips: \n  $ "+p.getValue().getTuesdayCol().getTips()+"\n\n   Time: \n    "+p.getValue().getTuesdayCol().getTime()+"\n\n\n"));
            }
         });
        wednesdayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getWednesdayCol().getDay()==null ? "":p.getValue().getWednesdayCol().getDay()+"\n\n\n   Amount: \n  $ "+p.getValue().getWednesdayCol().getAmount()+"\n\n   Tips: \n  $ "+p.getValue().getWednesdayCol().getTips()+"\n\n   Time: \n    "+p.getValue().getWednesdayCol().getTime()+"\n\n\n"));
            }
         });
        thursdayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getThursdayCol().getDay()==null ? "":p.getValue().getThursdayCol().getDay()+"\n\n\n   Amount: \n  $ "+p.getValue().getThursdayCol().getAmount()+"\n\n   Tips: \n  $ "+p.getValue().getThursdayCol().getTips()+"\n\n   Time: \n    "+p.getValue().getThursdayCol().getTime()+"\n\n\n"));
            }
         });
        fridayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getFridayCol().getDay()==null ? "":p.getValue().getFridayCol().getDay()+"\n\n\n   Amount: \n  $ "+p.getValue().getFridayCol().getAmount()+"\n\n   Tips: \n  $ "+p.getValue().getFridayCol().getTips()+"\n\n   Time: \n    "+p.getValue().getFridayCol().getTime()+"\n\n\n"));
            }
         });
        saturdayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getSaturdayCol().getDay()==null ? "":p.getValue().getSaturdayCol().getDay()+"\n\n\n   Amount: \n  $ "+p.getValue().getSaturdayCol().getAmount()+"\n\n   Tips: \n  $ "+p.getValue().getSaturdayCol().getTips()+"\n\n   Time: \n    "+p.getValue().getSaturdayCol().getTime()+"\n\n\n"));
            }
         });
        sundayCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DaysOfWeek, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DaysOfWeek, String> p) {
                return new ReadOnlyStringWrapper((p.getValue().getSundayCol().getDay()==null ? "":p.getValue().getSundayCol().getDay()+"\n\n\n   Amount: \n  $ "+p.getValue().getSundayCol().getAmount()+"\n\n   Tips: \n  $ "+p.getValue().getSundayCol().getTips()+"\n\n   Time: \n    "+p.getValue().getSundayCol().getTime()+"\n\n\n"));
            }
         });
        mainTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> old, Tab oldTab, Tab newTab) {
                //Check for Tab and call you method here
                if(newTab.getId().equals(CALENDAR_ID))
                {
                    controller.refreshTableView();
                }
            }

        });
        
        headerTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> old, Tab oldTab, Tab newTab) {
                //Check for Tab and call you method here
                ObservableList<DaysOfWeek> daysOfWeekList = null;
                List<InformationUserCalendar> iuc;
                iuc = new ArrayList<>();
                daysOfWeekList = FXCollections.observableArrayList();
                iuc = daysdao.read(Integer.parseInt(controller.getIdUser()));
               try{
                    String month = ArabitoGrillUtil.monthNumber(newTab.getText().toString()).toString();
                    String year = footerTabPane.getTabs().get(footerTabPane.getSelectionModel().getSelectedIndex()).getText();                
                    Calendar cal1 = new GregorianCalendar(Integer.parseInt(year.trim()),Integer.parseInt(month.trim())-1,1);
                    DaysOfWeek dow = new DaysOfWeek();
                    for(int i = 1; i <= cal1.getMaximum(Calendar.DAY_OF_MONTH); i++){
                        switch(ArabitoGrillUtil.dayName(year+"-"+month+"-"+i, ("yyyy-M-d").toString()).toString().toLowerCase())
                        {
                            case "monday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getMondayCol());
                                dow.getMondayCol().setDay(""+i+"");
                                break;
                            case "tuesday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getTuesdayCol());
                                dow.getTuesdayCol().setDay(""+i+"");
                                break;
                            case "wednesday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getWednesdayCol());
                                dow.getWednesdayCol().setDay(""+i+"");
                                break;                                                         
                            case "thursday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getThursdayCol());
                                dow.getThursdayCol().setDay(""+i+"");
                                break;
                            case "friday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getFridayCol());
                                dow.getFridayCol().setDay(""+i+"");
                                break;
                            case "saturday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getSaturdayCol());
                                dow.getSaturdayCol().setDay(""+i+"");
                                break;
                            case "sunday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getSundayCol());
                                dow.getSundayCol().setDay(""+i+"");
                                daysOfWeekList.add(dow);
                                dow = new DaysOfWeek();
                                break;
                        }
                    }  
                    if(dow.getMondayCol().getDay() != null && dow.getSundayCol().getDay() == null){
                        daysOfWeekList.add(dow);
                    }
                    tableView.setItems(daysOfWeekList);
                }catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }

              private void asignAmountTipsAndTime(List<InformationUserCalendar> iuc, int i, String month, String year, FieldOfCalendarPosition day) {
                  for(int k = 0; k < iuc.size(); k++)
                  {
                      if((""+iuc.get(k).getDay()).toString().equals((""+i).toString()) && (""+iuc.get(k).getMonth()).toString().equals(month) && (""+iuc.get(k).getYear()).toString().equals(year))
                      {
                          day.setTips((""+iuc.get(k).getTips()).toString());
                          day.setAmount((""+iuc.get(k).getAmount()).toString());
                          day.setTime(((""+iuc.get(k).getHour()).toString().replace(".0","")+":"+iuc.get(k).getMinutes()+":00").toString());
                      }
                  }
              }

        });
        
        footerTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> old, Tab oldTab, Tab newTab) {
                //Check for Tab and call you method here
                ObservableList<DaysOfWeek> daysOfWeekList = FXCollections.observableArrayList();
                List<InformationUserCalendar> iuc;
                iuc = new ArrayList<>();
                daysOfWeekList = FXCollections.observableArrayList();
                iuc = daysdao.read(Integer.parseInt(controller.getIdUser()));
                try{
                    String year = newTab.getText().toString();
                    String month = ArabitoGrillUtil.monthNumber(headerTabPane.getTabs().get(headerTabPane.getSelectionModel().getSelectedIndex()).getText()).toString();
                    Calendar cal1 = new GregorianCalendar(Integer.parseInt(year.trim()),Integer.parseInt(month.trim())-1,1);
                    DaysOfWeek dow = new DaysOfWeek();
                    for(int i = 1; i <= cal1.getMaximum(Calendar.DAY_OF_MONTH); i++){
                        switch(ArabitoGrillUtil.dayName(year+"-"+month+"-"+i, ("yyyy-M-d").toString()).toString().toLowerCase())
                        {
                            case "monday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getMondayCol());
                                dow.getMondayCol().setDay(""+i+"");
                                break;
                            case "tuesday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getTuesdayCol());
                                dow.getTuesdayCol().setDay(""+i+"");
                                break;
                            case "wednesday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getWednesdayCol());
                                dow.getWednesdayCol().setDay(""+i+"");
                                break;                                                         
                            case "thursday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getThursdayCol());
                                dow.getThursdayCol().setDay(""+i+"");
                                break;
                            case "friday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getFridayCol());
                                dow.getFridayCol().setDay(""+i+"");
                                break;
                            case "saturday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getSaturdayCol());
                                dow.getSaturdayCol().setDay(""+i+"");
                                break;
                            case "sunday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getSundayCol());
                                dow.getSundayCol().setDay(""+i+"");
                                daysOfWeekList.add(dow);
                                dow = new DaysOfWeek();
                                break;                        }
                    }
                    if(dow.getMondayCol().getDay() != null && dow.getSundayCol().getDay() == null){
                        daysOfWeekList.add(dow);
                    }
                    tableView.setItems(daysOfWeekList);
                    System.out.println(controller.getIdUser());
                }catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
            
            private void asignAmountTipsAndTime(List<InformationUserCalendar> iuc, int i, String month, String year, FieldOfCalendarPosition day) {
                  for(int k = 0; k < iuc.size(); k++)
                  {
                      if((""+iuc.get(k).getDay()).toString().equals((""+i).toString()) && (""+iuc.get(k).getMonth()).toString().equals(month) && (""+iuc.get(k).getYear()).toString().equals(year))
                      {
                          day.setTips((""+iuc.get(k).getTips()).toString());
                          day.setAmount((""+iuc.get(k).getAmount()).toString());
                          day.setTime(((""+iuc.get(k).getHour()).toString().replace(".0","")+":"+iuc.get(k).getMinutes()+":00").toString());
                      }
                  }
              }
        });
        try {
            toolbarFXML  = new FXMLLoader(getClass().getResource("/arabitogrill/main/toolbar/toolbar.fxml"));
            toolbar  =  (VBox)toolbarFXML.load();
            toolbarUsersFXML  = new FXMLLoader(getClass().getResource("/arabitogrill/main/toolbaruser/toolbar.fxml"));
            toolbarUsers  =  (VBox)toolbarUsersFXML.load();
            controller = toolbarUsersFXML.getController();
            controllerMenu = toolbarFXML.getController();
            controllerMenu.setMainController(this);
            controller.setMainController(this);
            drawer.setSidePane(toolbar);
            drawer.open();
            drawerUsers.setSidePane(toolbarUsers);
            drawerUsers.open();

            initCalendar();
            
             
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        
        // Alert Message
        BillsDAO bdao = new BillsDAO();
        Calendar cal = Calendar.getInstance();
        Date dateNow = new Date(cal.getTimeInMillis());
        cal.add(Calendar.DATE, -ALERT_DAY);
        Date dateBefore = new Date(cal.getTimeInMillis());
        int pl=0, up=0;
        
        for(Bills bill : bdao.read(null)) 
        {
        	if(dateNow.after(bill.getExpirationAt())) {
	        	bill.setSpend("UNPAID");
	        	bdao.update(bill);
	        	up++;
        	} else if(dateBefore.after(bill.getExpirationAt())) {
        		pl++;
        	}
        }
        
        if(up>0 || pl>0) {
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Warning Dialog");
	        alert.setHeaderText("Warning, check your bills");
	        alert.setContentText("You have (" + up + ") bill(s) UNPAID and (" + pl + ") that will expire in less than a week!");
	
	        alert.showAndWait();
        }
    }
    
    public ToolbarController getToolbarController(){
        return controller;
    }
    
    public void initCalendar()
    {
        if(Integer.parseInt(controller.getIdUser()) > 0 || !firstCharge)
        {
            ObservableList<DaysOfWeek> daysOfWeekList = null;
            List<InformationUserCalendar> iuc;
            iuc = new ArrayList<>();
            daysOfWeekList = FXCollections.observableArrayList();
            iuc = daysdao.read(Integer.parseInt(controller.getIdUser()));
            try{
                String month = ArabitoGrillUtil.monthNumber(headerTabPane.getTabs().get(headerTabPane.getSelectionModel().getSelectedIndex()).getText()).toString();
                String year = footerTabPane.getTabs().get(footerTabPane.getSelectionModel().getSelectedIndex()).getText();                
                Calendar cal1 = new GregorianCalendar(Integer.parseInt(year.trim()),Integer.parseInt(month.trim())-1,1);
                DaysOfWeek dow = new DaysOfWeek();
                for(int i = 1; i <= cal1.getMaximum(Calendar.DAY_OF_MONTH); i++){
                    switch(ArabitoGrillUtil.dayName(year+"-"+month+"-"+i, ("yyyy-M-d").toString()).toString().toLowerCase())
                    {
                            case "monday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getMondayCol());
                                dow.getMondayCol().setDay(""+i+"");
                                break;
                            case "tuesday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getTuesdayCol());
                                dow.getTuesdayCol().setDay(""+i+"");
                                break;
                            case "wednesday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getWednesdayCol());
                                dow.getWednesdayCol().setDay(""+i+"");
                                break;                                                         
                            case "thursday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getThursdayCol());
                                dow.getThursdayCol().setDay(""+i+"");
                                break;
                            case "friday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getFridayCol());
                                dow.getFridayCol().setDay(""+i+"");
                                break;
                            case "saturday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getSaturdayCol());
                                dow.getSaturdayCol().setDay(""+i+"");
                                break;
                            case "sunday":
                                asignAmountTipsAndTime(iuc, i, month, year, dow.getSundayCol());
                                dow.getSundayCol().setDay(""+i+"");
                                daysOfWeekList.add(dow);
                                dow = new DaysOfWeek();
                                break;                    }
                }  
                if(dow.getMondayCol().getDay() != null && dow.getSundayCol().getDay() == null){
                    daysOfWeekList.add(dow);
                }
                tableView.setItems(daysOfWeekList);
            }catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            firstCharge = true;
        }
    }
    
    private void asignAmountTipsAndTime(List<InformationUserCalendar> iuc, int i, String month, String year, FieldOfCalendarPosition day) {
        for(int k = 0; k < iuc.size(); k++)
        {
            if((""+iuc.get(k).getDay()).toString().equals((""+i).toString()) && (""+iuc.get(k).getMonth()).toString().equals(month) && (""+iuc.get(k).getYear()).toString().equals(year))
            {
                day.setTips((""+iuc.get(k).getTips()).toString());
                day.setAmount((""+iuc.get(k).getAmount()).toString());
                day.setTime(((""+iuc.get(k).getHour()).toString().replace(".0","")+":"+iuc.get(k).getMinutes()+":00").toString());
            }
        }
    }
    
    @FXML
    private void handleMenuFullScreen(ActionEvent event) {
        executeMenuFullScreen();
    }
    
    private void executeMenuFullScreen(){
        Stage stage = ((Stage) rootPane.getScene().getWindow());
        stage.setFullScreen(!stage.isFullScreen());
    }
    
    @FXML
    private void handleMenuClose(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }
    
    @FXML
    private void handleMenuAddMember(ActionEvent event) {
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/addmember/member_add.fxml"), "Add New Member", null, false, false, null);
    }
    
    @FXML
    private void handleMenuAddBills(ActionEvent event) {
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/addbill/bill_add.fxml"), "Add New Bill", null, false, false, null);    
    }
    
    @FXML
    private void handleMenuViewBills(ActionEvent event) {
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/listBill/list_bill.fxml"), "View Bills", null, false, false, null);    
    }
    
    @FXML
    private void handleMenuViewWorkers(ActionEvent event) {
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/listWorker/list_worker.fxml"), "View Workers", null, false, false, null);    
    }
}
