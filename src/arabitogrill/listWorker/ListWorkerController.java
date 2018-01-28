package arabitogrill.listWorker;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

import arabitogrill.ArabitoGrill;
import arabitogrill.editmember.MemberEditController;
import arabitogrill.model.Workers;
import arabitogrill.model.WorkersDAO;
import java.util.HashSet;
import java.util.Set;

import com.jfoenix.controls.JFXTextField;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListWorkerController {
    
    public TableView<Workers> tableView = new TableView<>();
    private WorkersDAO workerdao = new WorkersDAO();

    @FXML
    private TableColumn<Workers,String> firstNameColumn = new TableColumn<Workers,String>("First name");
    @FXML
    private TableColumn<Workers,String> secondNameColumn = new TableColumn<Workers,String>("Second name");
    @FXML
    private TableColumn<Workers,String> surnameColumn = new TableColumn<Workers,String>("Surname");
    @FXML
    private TableColumn<Workers,String> secondSurnameColumn = new TableColumn<Workers,String>("Second surname");
    @FXML
    private TableColumn<Workers,String> emailColumn = new TableColumn<Workers,String>("Email");
    @FXML
    private TableColumn<Workers,String> chargeColumn = new TableColumn<Workers,String>("Charge");
    @FXML
    private TableColumn<Workers,String> mobileColumn = new TableColumn<Workers,String>("Mobile");
    @FXML
    private TableColumn<Workers,BigDecimal> dailyColumn = new TableColumn<Workers,BigDecimal>("Hour Salary");
    @FXML
    private TableColumn<Workers,Date> birthColumn = new TableColumn<Workers,Date>("Date Birth");
    @FXML
    private TableColumn actionColumn = new TableColumn("Action");
    @FXML
    private TableColumn deleteColumn = new TableColumn("Delete");
    @FXML
    private TableColumn editColumn = new TableColumn("Edit");
    
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
    
    private ArabitoGrill arabitoGrill;
	
    public ListWorkerController() {}

    @FXML
    public void initialize() {
    	
    	// Colmuns
        firstNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                return new ReadOnlyStringWrapper(p.getValue().getFirstName());
            }
         });
        
        secondNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                return new ReadOnlyStringWrapper(p.getValue().getSecondName());
            }
         });
        
        surnameColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                return new ReadOnlyStringWrapper(p.getValue().getSurname());
            }
         });
        
        secondSurnameColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                return new ReadOnlyStringWrapper(p.getValue().getSecondSurname());
            }
         });
        
        emailColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                return new ReadOnlyStringWrapper(p.getValue().getEmail());
            }
         });
        
        chargeColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                return new ReadOnlyStringWrapper(p.getValue().getCharge());
            }
         });
        
        mobileColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Workers, String> p) {
                return new ReadOnlyStringWrapper(p.getValue().getMobile());
            }
         });
        
        dailyColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, BigDecimal>, ObservableValue<BigDecimal>>() {
            public ObservableValue<BigDecimal> call(CellDataFeatures<Workers, BigDecimal> p) {
                return new SimpleObjectProperty<BigDecimal>(p.getValue().getDailyS());
            }
         });
        
        birthColumn.setCellValueFactory(new Callback<CellDataFeatures<Workers, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(CellDataFeatures<Workers, Date> p) {
                return new SimpleObjectProperty<Date>(p.getValue().getBirth());
            }
         });
        
        firstNameColumn.setId("firstNameColumn");
        secondNameColumn.setId("secondNameColumn");
        surnameColumn.setId("surnameColumn");
        secondSurnameColumn.setId("secondSurnameColumn");
        emailColumn.setId("emailColumn");
        mobileColumn.setId("mobileColumn");
        chargeColumn.setId("chargeColumn");
        dailyColumn.setId("dailyColumn");
        birthColumn.setId("birthColumn");
        actionColumn.setId("actionColumn");
        editColumn.setId("editColumn");
        deleteColumn.setId("deleteColumn");
        
        firstNameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
        secondNameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
        surnameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
        secondSurnameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
        emailColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
        mobileColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
        chargeColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
        dailyColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
        birthColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
        
        addButtonToTable();
        
        this.tableView.setItems(workerdao.getObservableWorker());
        tableView.getColumns().addAll(
        		firstNameColumn,
                        secondNameColumn,
                        surnameColumn,
                        secondSurnameColumn,
        		emailColumn,
        		mobileColumn,
        		birthColumn,
        		chargeColumn,
        		dailyColumn,
        		actionColumn);

    }
    
    private void addButtonToTable() {

        Callback<TableColumn<Workers, Void>, TableCell<Workers, Void>> cellFactory = new Callback<TableColumn<Workers, Void>, TableCell<Workers, Void>>() {
            @Override
            public TableCell<Workers, Void> call(final TableColumn<Workers, Void> param) {
                final TableCell<Workers, Void> cell = new TableCell<Workers, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	Workers data = getTableView().getItems().get(getIndex());
                        	workerdao.delete(data.getId());
                                tableView.refresh();
                                tableView.setItems(workerdao.getObservableWorker());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        
        Callback<TableColumn<Workers, Void>, TableCell<Workers, Void>> cellFactory2 = new Callback<TableColumn<Workers, Void>, TableCell<Workers, Void>>() {
            @Override
            public TableCell<Workers, Void> call(final TableColumn<Workers, Void> param) {
                final TableCell<Workers, Void> cell = new TableCell<Workers, Void>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	Workers data = getTableView().getItems().get(getIndex());
                        	arabitoGrill.showEditWorker(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        deleteColumn.setCellFactory(cellFactory);
<<<<<<< HEAD
        editColumn.setCellFactory(cellFactory2);
        actionColumn.getColumns().addAll(editColumn, deleteColumn);
        editColumn.prefWidthProperty().bind(tableView.widthProperty().divide(13));
        //deleteColumn.prefWidthProperty().bind(tableView.widthProperty().divide(2));
=======
        actionColumn.getColumns().addAll(updateColumn, deleteColumn);
        updateColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
        deleteColumn.prefWidthProperty().bind(tableView.widthProperty().divide(11));
>>>>>>> 806b3693ba71bbe5273de1ea4adf64081842259b
        //actionColumn.prefWidthProperty().bind(tableView.widthProperty().divide(2));
    }
}
