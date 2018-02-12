/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill;

import arabitogrill.editbill.BillEditController;
import arabitogrill.editmember.MemberEditController;
import arabitogrill.model.Bills;
import arabitogrill.model.Workers;
import arabitogrill.util.ArabitoGrillUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import arabitogrill.consts.Consts;
import arabitogrill.login.LoginController;

/**
 *
 * @author eulis
 */
public class ArabitoGrill extends Application {
	
    private BigDecimal perH;
    private BigDecimal perW;
    private Integer iniY;
    private Integer endY;
    private LoginController login;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxlogin = new FXMLLoader(getClass().getResource("/arabitogrill/login/login.fxml"));
        Parent root = (Parent)fxlogin.load();
        login = (LoginController)fxlogin.getController();
        login.arabitoGrill = this;
        getSetting();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        ArabitoGrillUtil.setStageIcon(primaryStage);
        primaryStage.show();
        primaryStage.setTitle("Arabito Assistant Login");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void showEditWorker(Workers worker, TableView tableView) {
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(ArabitoGrill.class.getResource("/arabitogrill/editmember/member_edit.fxml"));

		try {
			Parent root;
			root = (Parent)fxmlLoader.load();
			MemberEditController controller = fxmlLoader.<MemberEditController>getController();
                        controller.initData(worker,tableView);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void showEditBill(Bills bill, TableView tableView) {
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(ArabitoGrill.class.getResource("/arabitogrill/editbill/bill_edit.fxml"));

		try {
			Parent root;
			root = (Parent)fxmlLoader.load();
			BillEditController controller = fxmlLoader.<BillEditController>getController();
                        controller.initData(bill,tableView);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void getSetting() throws FileNotFoundException, IOException {
    	Path currentRelativePath = Paths.get("");
    	String s = currentRelativePath.toAbsolutePath().toString();
    	File file = new File(s + "/settings_arabitogrill.txt");
        this.perH = BigDecimal.TEN;
        this.perW = BigDecimal.TEN;
        this.iniY = 2018;
        this.endY = 2030;
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            
            st = new String(br.readLine());
            if ((st) != null)
                this.perH = new BigDecimal(st);
            
            st =  new String(br.readLine());
            if ((st) != null)
                this.perW = new BigDecimal(st);

            st =  new String(br.readLine());
            if ((st) != null)
                this.iniY = Consts.startYears = Integer.valueOf(st);

            st =  new String(br.readLine());
            if ((st) != null)
                this.endY = Consts.endYears = Integer.valueOf(st);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }/* finally {
                this.perH = BigDecimal.TEN;
                this.perW = BigDecimal.TEN;
                this.iniY = 2018;
                this.endY = 2030;
            }*/
        }

	public BigDecimal getPerH() {
		return perH;
	}

	public void setPerH(BigDecimal perH) {
		this.perH = perH;
	}

	public BigDecimal getPerW() {
		return perW;
	}

	public void setPerW(BigDecimal perW) {
		this.perW = perW;
	}

	public Integer getIniY() {
		return iniY;
	}

	public void setIniY(Integer iniY) {
		this.iniY = iniY;
	}

	public Integer getEndY() {
		return endY;
	}

	public void setEndY(Integer endY) {
		this.endY = endY;
	}
    
}
