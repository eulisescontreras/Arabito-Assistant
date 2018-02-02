/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabitogrill.main.toolbaruser;

import arabitogrill.main.MainController;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
    public TableView<Workers> tableViewCalendar;
    @FXML
    public TableColumn nameCol;
    @FXML
    public String Id = "-1";
    public MainController mainController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Workers, String> p) {
                return new ReadOnlyStringWrapper(p.getValue().getId()+"\n\n  "+p.getValue().getFirstName()+" "+p.getValue().getSecondName()+"\n  "+p.getValue().getSurname()+" "+p.getValue().getSecondSurname()+"\n\n");
            }
         });
        tableView.setItems(workerdao.getObservableWorker());
        tableView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler() {
             @Override
             public void handle(Event event) {
                 String id = event.getTarget().toString().split("text=")[1].split("\n")[0].replace(""+'"',"");
                 Id = id;
                 mainController.initCalendar();
             }
         });
    }
    
    public TableView<Workers> getTableView() {
        return this.tableView;
    }
    
    public void setTableView(TableView<Workers> tableView){
        this.tableView = tableView; 
    }
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    
    public String getIdUser(){
        return this.Id;
    }
    
    public void refreshTableView()
    {
        this.tableView.refresh();
        this.tableView.setItems(workerdao.getObservableWorker());
    }
   
}

