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
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;

/**
 *
 * @author eulis
 */
public class ToolbarController  implements Initializable {

    private WorkersDAO workerdao = new WorkersDAO();
    @FXML
    public TableView<Workers> tableView;
    @FXML
    public TableColumn nameCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Workers, String> p) {
                return new ReadOnlyStringWrapper(p.getValue().getName());
            }
         });
        tableView.setItems(workerdao.getObservableWorker());
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.print("hola katty");
            }
        });
    }
    
    public void addWorkers() {
        
    }
   
}

