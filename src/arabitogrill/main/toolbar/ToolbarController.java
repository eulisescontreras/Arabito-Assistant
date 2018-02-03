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

import arabitogrill.addmember.MemberAddController;
import static arabitogrill.main.MainController.toolbar;
import static arabitogrill.main.MainController.toolbarFXML;
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

                doc.save("bill_" + cal.getTime().getMonth() +"_"+  cal.getTime().getYear() +".pdf" );
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } 
    }
    
    public static void drawTable(PDPage page, PDPageContentStream contentStream,
            float y, float margin,
            List<Workers> workerList) throws IOException {
		final int rows = workerList.size()+1;
		final int cols = 1;
		final float rowHeight = 20f;
		final float tableWidth = page.getCropBox().getWidth() - margin - margin;
		final float tableHeight = rowHeight * rows;
		final float colWidth = tableWidth/(float)cols;
		final float cellMargin=5f;
		
		//draw the rows
		float nexty = y ;
		for (int i = 0; i <= rows; i++) {
                    contentStream.drawLine(margin, nexty, margin+tableWidth, nexty);
                    nexty-= rowHeight;
		}
		
		//draw the columns
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
		contentStream.drawLine(nextx, y, nextx, y-tableHeight);
		nextx += colWidth;
		}
		
		//now add the text
		contentStream.setFont( PDType1Font.HELVETICA_BOLD , 12 );
		
		float textx = margin+cellMargin;
		float texty = y-15;
		
		String text = "Name";
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(textx,texty);
		contentStream.drawString(text);
		contentStream.endText();
		textx += colWidth;
		
		text = "Tips";
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(textx,texty);
		contentStream.drawString(text);
		contentStream.endText();
		textx += colWidth;
		
		texty-=rowHeight;
		textx = margin+cellMargin;
		
		for(Workers worker : workerList) 
		{
			text = worker.getFirstName() + " " + worker.getSecondName();
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(textx,texty);
			contentStream.drawString(text);
			contentStream.endText();
			textx += colWidth;
			
			text = worker.getDailyS().toString();
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(textx,texty);
			contentStream.drawString(text);
			contentStream.endText();
			textx += colWidth;
			
			texty-=rowHeight;
			textx = margin+cellMargin;
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Successful Printing!");

		alert.showAndWait();
	}
}
