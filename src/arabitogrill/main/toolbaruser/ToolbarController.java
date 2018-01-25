/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.main.toolbaruser;

import arabitogrill.util.ArabitoGrillUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import arabitogrill.model.*;

/**
 *
 * @author eulis
 */
public class ToolbarController  implements Initializable {

    @FXML
    private TableView<Workers> tableView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*List<String> list = new ArrayList<String>();
        list.add("String one");
        list.add("String two");
        list.add("String three");
                list.add("String one");
        list.add("String two");
        list.add("String three");
                list.add("String one");
        list.add("String two");
        list.add("String three");
                list.add("String one");
        list.add("String two");
        list.add("String three");
                list.add("String one");
        list.add("String two");
        list.add("String three");
                list.add("String one");
        list.add("String two");
        list.add("String three");
                list.add("String one");
        list.add("String two");
        list.add("String three");
                list.add("String one");
        list.add("String two");
        list.add("String three");
        tableViewUser.getItems().setAll(list);*/
    }
    
    public void addWorkers() {
        
    }
   
}

