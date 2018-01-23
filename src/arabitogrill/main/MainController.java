/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import arabitogrill.util.ArabitoGrillUtil;
import javafx.scene.control.ScrollPane;
/**
 *
 * @author eulis
 */
public class MainController  implements Initializable {

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScrollPane scrollPane = new ScrollPane();
        footerTabPane.getTabs().add(new Tab("2018"));
        footerTabPane.getTabs().add(new Tab("2018"));
        footerTabPane.getTabs().add(new Tab("2018"));
        footerTabPane.getTabs().add(new Tab("2018"));
        try {
            VBox toolbar = FXMLLoader.load(getClass().getResource("/arabitogrill/main/toolbar/toolbar.fxml"));
            VBox toolbarUsers  = FXMLLoader.load(getClass().getResource("/arabitogrill/main/toolbaruser/toolbar.fxml"));
            scrollPane.setContent(toolbarUsers);
            drawer.setSidePane(toolbar);
            drawer.open();
            drawerUsers.setSidePane(toolbarUsers);
            drawerUsers.open();
        } catch (IOException ex) {
            //Logger.getLogger(library.assistant.ui.main.MainController.class.getName()).log(Level.SEVERE, null, ex);
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
