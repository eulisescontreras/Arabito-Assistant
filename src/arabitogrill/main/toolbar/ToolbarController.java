/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.main.toolbar;

import arabitogrill.listWorker.ListWorkerController;
import arabitogrill.util.ArabitoGrillUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import arabitogrill.main.MainController;
import arabitogrill.model.DaysDAO;
import arabitogrill.model.InformationUserCalendar;
import arabitogrill.model.Workers;
import arabitogrill.model.WorkersDAO;

import static arabitogrill.main.MainController.toolbarFXML;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import arabitogrill.addmember.MemberAddController;
import static arabitogrill.main.MainController.toolbar;
import static arabitogrill.main.MainController.toolbarFXML;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.VBox;
/**
 *
 * @author eulis
 */
public class ToolbarController  implements Initializable {

    private MainController mainController;
    private MemberAddController controllerAddWorker;
    private AnchorPane toolbarAnchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    // Worker
    @FXML
    private void loadAddMember(ActionEvent event) {
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/addmember/member_add.fxml"), "Add New Worker",this.mainController,true,false,null);
    }
    @FXML
    private void loadListWorker(ActionEvent event) {
    	ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/listWorker/list_worker.fxml"), "Worker List",null,false,false,null);
    }
    
    // Bill
    @FXML
    private void loadAddBill(ActionEvent event) {
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/addbill/bill_add.fxml"), "Add New Bill", null,false,false,null);
    }
    @FXML
    private void loadListBill(ActionEvent event) {
    	ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/listBill/list_bill.fxml"), "Bill List", null,false,false,null);
    }
    
    // Setting
    @FXML
    private void loadSetting(ActionEvent event) {
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/settings/settings.fxml"), "Settings", null,false,false,null);
    }
    
    @FXML
    private void print(ActionEvent event) {
<<<<<<< HEAD
    	PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage( page );
        
        try {
             PDPageContentStream contentStream = new PDPageContentStream(doc, page);
             
             WorkersDAO wdao = new WorkersDAO();
             DaysDAO ddao = new DaysDAO();
             List<Workers> workerList = new ArrayList<Workers>();
             
             for(Workers worker : wdao.read(null)) 
             {
            	 Double tot = new Double(0);
            	 
            	 for(InformationUserCalendar inf : ddao.read(worker.getId()))
            		tot = tot + inf.getAmount();
            	 
            	 worker.setDailyS(new BigDecimal(tot));
            	 workerList.add(worker);
             }
             
        	drawTable(page, contentStream, 700, 100, workerList);
                contentStream.close();

                Calendar cal = Calendar.getInstance();

                doc.save("C:\\Users\\eulis\\Desktop\\bill_" + cal.getTime().getMonth() +"_"+  cal.getTime().getYear() +".pdf" );
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } 
=======
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/print/print_amount.fxml"), "Print workers Amount", null,false,false,null);
>>>>>>> 690c464bdab43ddaac5593caea5c38a6839652e6
    }
}
