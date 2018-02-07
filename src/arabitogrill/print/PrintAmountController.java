/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.print;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.jfoenix.controls.*;

import arabitogrill.model.BillsDAO;
import arabitogrill.model.ConnectionFactory;
import arabitogrill.model.DaysDAO;
import arabitogrill.model.InformationUserCalendar;
import arabitogrill.model.Workers;
import arabitogrill.model.WorkersDAO;
import arabitogrill.model.Bills;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author eulis
 */
public class PrintAmountController {
    @FXML
    private JFXTextField initD;
    @FXML
    private JFXTextField endD;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    
    // location and resources will be automatically injected by the FXML loader
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    // Public no-args constructor
    public PrintAmountController() {
    }
    
    @FXML
    private void initialize() {
    	initD.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!initD.getText().matches("[0-9]{4}")){
                	initD.setText("");
                }
                
                if(initD.getText().trim().length()==0){
                	initD.getStyleClass().add("wrong-credentials");
                }
            }
        }); 
    	
    	endD.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!endD.getText().matches("[0-9]{4}")){
                	endD.setText("");
                }
                
                if(endD.getText().trim().length()==0){
                	endD.getStyleClass().add("wrong-credentials");
                }
            }
        }); 
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)initD.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void save(ActionEvent event) {
    	PDDocument doc = new PDDocument();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        Boolean band = false;
        
        if(initD.getText().trim().length()==0
        		|| endD.getText().trim().length()==0) {
        	initD.setText(currentYear + "");
        	endD.setText(currentYear + "");
        }
        
        try {
             for(int y=Integer.parseInt(initD.getText());y<=Integer.parseInt(endD.getText());y++) 
             {
            	 
            	 for(int m=1;m<=12;m++) 
            	 {
            		 int initY=650;
                     WorkersDAO wdao = new WorkersDAO();
                     DaysDAO ddao = new DaysDAO();
            		 List<Workers> workerList = new ArrayList<Workers>();
            		 
            		 for(Workers worker : wdao.read(null)) 
                     {
                    	 Double tot = new Double(0);
                    	 
                    	 for(InformationUserCalendar inf : ddao.read(worker.getId())) 
                    	 {
                    		 if(inf.getYear()==y && inf.getMonth()==m)
                    			 tot = tot + inf.getAmount();
                    	 }
                    	
                    	 worker.setDailyS(new BigDecimal(tot));
                    	 workerList.add(worker);
                     }
                    
            		 if(workerList.size() > 0) 
            		 {
            			 /* PRINT */
            			 PDPage page = new PDPage();
                	     doc.addPage( page );
                		 PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            			 drawTable(page, contentStream, initY, 100, workerList, getMonthForInt(m-1), y);
            			 contentStream.close();
            			 band=true;
            		 }
            		 
            	 }
             }
             
            if(band) 
            {
            	Calendar cal = Calendar.getInstance();
                doc.save("C:\\Users\\eulis\\Desktop\\bill_" + currentMonth +"_"+  currentYear +".pdf" );
                
            	Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Information Dialog");
        		alert.setHeaderText(null);
        		alert.setContentText("Successful Printing!");
        		
                try{
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/resources/myDialogs.css").toExternalForm());
                    dialogPane.getStyleClass().add("myDialog");
        		}catch(Exception e)
                {
                    System.out.println("Error");
                }
                
                alert.showAndWait();
            }
            else
            {
            	Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Information Dialog");
        		alert.setHeaderText(null);
        		alert.setContentText("No Data to Print");
        		
                try{
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/resources/myDialogs.css").toExternalForm());
                    dialogPane.getStyleClass().add("myDialog");
        		}catch(Exception e)
                {
                    System.out.println("Error");
                }
                
                alert.showAndWait();
            }
            
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }

    public void drawTable(PDPage page, PDPageContentStream contentStream,
            float y, float margin,
            List<Workers> workerList, String month, int year) throws IOException {
		final int rows = workerList.size()+1;
		final int cols = 2;
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
		
		// Year
		contentStream.drawLine(margin, 700, margin+tableWidth, 700);
		String text = year + "";
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(margin,700);
		contentStream.drawString(text);
		contentStream.endText(); 
		
		float textx = margin+cellMargin;
		float texty = y-15;
		
		text = "Names:";
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(textx,texty);
		contentStream.drawString(text);
		contentStream.endText();
		textx += colWidth;
		
		text = "Amounts:";
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(textx,texty);
		contentStream.drawString(text);
		contentStream.endText();
		textx += colWidth;
		
		text = month;
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
			
			text = worker.getDailyS().setScale(2, RoundingMode.DOWN).toString();
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(textx,texty);
			contentStream.drawString(text);
			contentStream.endText();
			textx += colWidth;
			
			texty-=rowHeight;
			textx = margin+cellMargin;
		}
	}
    
    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
}
