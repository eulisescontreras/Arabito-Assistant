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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import arabitogrill.main.MainController;
import static arabitogrill.main.MainController.toolbarFXML;
import java.io.InputStream;
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
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/settings/settings.fxml"), "Settings", null,false,false);
    }
}
