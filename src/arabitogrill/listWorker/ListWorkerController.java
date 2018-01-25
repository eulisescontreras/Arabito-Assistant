package arabitogrill.listWorker;

import java.math.BigDecimal;
import java.sql.Date;

import arabitogrill.model.Workers;
import arabitogrill.model.WorkersDAO;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListWorkerController {
    
    public TableView<Workers> tableView = new TableView<>();
    private ObservableList<Workers> workersList = FXCollections.observableArrayList();
    private WorkersDAO workerdao = new WorkersDAO();

    @FXML
    private TableColumn<Workers,String> nameColumn = new TableColumn<Workers,String>("Name");
    @FXML
    private TableColumn<Workers,String> emailColumn = new TableColumn<Workers,String>("Email");
    @FXML
    private TableColumn<Workers,String> chargeColumn = new TableColumn<Workers,String>("Charge");
    @FXML
    private TableColumn<Workers,String> mobileColumn = new TableColumn<Workers,String>("Mobile");
    @FXML
    private TableColumn<Workers,BigDecimal> dailyColumn = new TableColumn<Workers,BigDecimal>("Daily Salary");
    @FXML
    private TableColumn<Workers,Date> birthColumn = new TableColumn<Workers,Date>("Date Birth");
        
    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label chargeLabel;
    @FXML
    private Label dailySLabel;
    @FXML
    private Label mobileLabel;
    @FXML
    private Label birthLabel;
	
    public ListWorkerController() {}

    @FXML
    public void initialize() {
        this.workersList = workerdao.getObservableWorker();
        this.tableView.setItems(workerdao.getObservableWorker());
        
        nameColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyStringWrapper(p.getValue().getName());
            }
         });
        
        emailColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyStringWrapper(p.getValue().getEmail());
            }
         });
        
        chargeColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyStringWrapper(p.getValue().getCharge());
            }
         });
        
        mobileColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyStringWrapper(p.getValue().getMobile());
            }
         });
        
        dailyColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, BigDecimal>, ObservableValue<BigDecimal>>() {
            public ObservableValue<BigDecimal> call(CellDataFeatures<Workers, BigDecimal> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new SimpleObjectProperty<BigDecimal>(p.getValue().getDailyS());
            }
         });
        
        birthColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(CellDataFeatures<Workers, Date> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new SimpleObjectProperty<Date>(p.getValue().getBirth());
            }
         });
        
        nameColumn.setId("nameColumn");
        emailColumn.setId("emailColumn");
        mobileColumn.setId("mobileColumn");
        chargeColumn.setId("chargeColumn");
        dailyColumn.setId("dailyColumn");
        birthColumn.setId("birthColumn");
        
        nameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(6));
        emailColumn.prefWidthProperty().bind(tableView.widthProperty().divide(6));
        mobileColumn.prefWidthProperty().bind(tableView.widthProperty().divide(6));
        chargeColumn.prefWidthProperty().bind(tableView.widthProperty().divide(6));
        dailyColumn.prefWidthProperty().bind(tableView.widthProperty().divide(6));
        birthColumn.prefWidthProperty().bind(tableView.widthProperty().divide(6));
        
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(emailColumn);
        tableView.getColumns().add(mobileColumn);
        tableView.getColumns().add(birthColumn);
        tableView.getColumns().add(chargeColumn);
        tableView.getColumns().add(dailyColumn);

        
     // Initialize the person tableView with the two columns.
        //nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        //emailColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));
        //chargeColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCharge()));
    }
    
    public ObservableList<Workers> getWorkersList() {
            return workersList;
    }

    public void setWorkersList(ObservableList<Workers> workersList) {
            this.workersList = workersList;
    }
}
