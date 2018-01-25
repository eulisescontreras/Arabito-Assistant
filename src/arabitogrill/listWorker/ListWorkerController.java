package arabitogrill.listWorker;

import java.math.BigDecimal;
import java.sql.Date;

import arabitogrill.model.Workers;
import arabitogrill.model.WorkersDAO;
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
	public TableView<Workers> table = new TableView<>();
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
	
	public ListWorkerController() {
    }

    @FXML
    public void initialize() {
        this.workersList = workerdao.getObservableWorker();
        this.table.setItems(workerdao.getObservableWorker());
        
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
        
        table.getColumns().add(nameColumn);
        table.getColumns().add(emailColumn);
        table.getColumns().add(mobileColumn);
        table.getColumns().add(chargeColumn);

        
     // Initialize the person table with the two columns.
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
