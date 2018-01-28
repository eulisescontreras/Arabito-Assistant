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
import java.io.IOException;
import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;


/**
 *
 * @author eulis
 */
public class ArabitoGrill extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/arabitogrill/login/login.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        ArabitoGrillUtil.setStageIcon(primaryStage);
        primaryStage.show();
        primaryStage.setTitle("Library Assistant Login");
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
    
}
