/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.main;

import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import arabitogrill.util.ArabitoGrillUtil;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import com.jfoenix.controls.JFXTabPane;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import arabitogrill.main.toolbaruser.ToolbarController;
import arabitogrill.consts.Consts;
import arabitogrill.model.Workers;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
/**
 *
 * @author eulis
 */
public class MainController  implements Initializable {

    private final Number NUMBER_CALENDAR = 1;
    
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
    private JFXDrawer drawer;
    @FXML
    private JFXDrawer drawerUsers;
    @FXML
    private JFXTabPane mainTabPane;
    
    FXMLLoader toolbarUsersFXML;
    ToolbarController controller;
    
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        for(int i = Consts.startYears; i <= Consts.endYears; i++)
            footerTabPane.getTabs().add(new Tab(""+i+""));
        
        mondayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        tuesdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        wednesdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        thursdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        fridayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        saturdayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        sundayCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));

        try {
            VBox toolbar = FXMLLoader.load(getClass().getResource("/arabitogrill/main/toolbar/toolbar.fxml"));
            toolbarUsersFXML  = new FXMLLoader(getClass().getResource("/arabitogrill/main/toolbaruser/toolbar.fxml"));
            VBox toolbarUsers  =  (VBox)toolbarUsersFXML.load();
            drawer.setSidePane(toolbar);
            drawer.open();
            drawerUsers.setSidePane(toolbarUsers);
            drawerUsers.open();
             
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
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
        ArabitoGrillUtil.loadWindow(getClass().getResource("/arabitogrill/addmember/member_add.fxml"), "Add New Member", null);
    }
}
