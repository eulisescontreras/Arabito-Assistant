package arabitogrill.listBill;

import java.math.BigDecimal;
import java.sql.Date;

import arabitogrill.ArabitoGrill;
import arabitogrill.model.Bills;
import arabitogrill.model.BillsDAO;
import arabitogrill.model.Workers;
import arabitogrill.model.Bills;
import arabitogrill.model.BillsDAO;
import java.util.HashSet;
import java.util.Set;
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
import javafx.geometry.Insets;
import javafx.scene.Group;
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

public class ListBillController {
    public TableView<Bills> tableView = new TableView<>();
    private BillsDAO billsdao = new BillsDAO();

    @FXML
    private TableColumn<Bills,BigDecimal> amountColumn = new TableColumn<Bills,BigDecimal>("Amount");
    @FXML
    private TableColumn<Bills,Date> createdAtColumn = new TableColumn<Bills,Date>("Created At");
    @FXML
    private TableColumn<Bills,Date> expirationAtColumn = new TableColumn<Bills,Date>("Expiration");
    @FXML
    private TableColumn<Bills,String> spendColumn = new TableColumn<Bills,String>("Status");
    @FXML
    private TableColumn actionColumn = new TableColumn("Action");
    @FXML
    private TableColumn deleteColumn = new TableColumn("Delete");
    @FXML
    private TableColumn updateColumn = new TableColumn("Update");
    @FXML
    private TableColumn payColumn = new TableColumn("Pay");
    
    @FXML
    private Label amountLabel;
    @FXML
    private Label createdAtLabel;
    @FXML
    private Label expirationAtLabel;
    @FXML
    private Label spendLabel;
    
    private ArabitoGrill arabitoGrill;
	
    public ListBillController() {}

    @FXML
    public void initialize() {
    	
    	// Colmuns
    	amountColumn.setCellValueFactory(new Callback<CellDataFeatures<Bills, BigDecimal>, ObservableValue<BigDecimal>>() {
            public ObservableValue<BigDecimal> call(CellDataFeatures<Bills, BigDecimal> p) {
            	return new SimpleObjectProperty<BigDecimal>(p.getValue().getAmount());
            }
         });
        
    	spendColumn.setCellValueFactory(new Callback<CellDataFeatures<Bills, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Bills, String> p) {
                return new ReadOnlyStringWrapper(p.getValue().getSpend());
            }
         });
        
    	expirationAtColumn.setCellValueFactory(new Callback<CellDataFeatures<Bills, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(CellDataFeatures<Bills, Date> p) {
                return new SimpleObjectProperty<Date>(p.getValue().getExpirationAt());
            }
         });
        
    	createdAtColumn.setCellValueFactory(new Callback<CellDataFeatures<Bills, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(CellDataFeatures<Bills, Date> p) {
                return new SimpleObjectProperty<Date>(p.getValue().getCreatedAt());
            }
         });
        
    	amountColumn.setId("amountColumn");
    	spendColumn.setId("spendColumn");
    	expirationAtColumn.setId("expirationAtColumn");
    	createdAtColumn.setId("createdAtColumn");
        actionColumn.setId("actionColumn");
        updateColumn.setId("updateColumn");
        deleteColumn.setId("deleteColumn");
        payColumn.setId("payColumn");
        
        amountColumn.prefWidthProperty().bind(tableView.widthProperty().divide(5));
        spendColumn.prefWidthProperty().bind(tableView.widthProperty().divide(5));
        expirationAtColumn.prefWidthProperty().bind(tableView.widthProperty().divide(5));
        createdAtColumn.prefWidthProperty().bind(tableView.widthProperty().divide(5));
        
        addButtonToTable();
        
        this.tableView.setItems(billsdao.getObservableBills());
        tableView.getColumns().addAll(
        		amountColumn,
        		createdAtColumn,
        		expirationAtColumn,
        		spendColumn,
        		actionColumn);

    }
    
    private void addButtonToTable() {

        Callback<TableColumn<Bills, Void>, TableCell<Bills, Void>> cellFactory = new Callback<TableColumn<Bills, Void>, TableCell<Bills, Void>>() {
            @Override
            public TableCell<Bills, Void> call(final TableColumn<Bills, Void> param) {
                final TableCell<Bills, Void> cell = new TableCell<Bills, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	Bills data = getTableView().getItems().get(getIndex());
                        	billsdao.delete(data.getId());
                                tableView.refresh();
                                tableView.setItems(billsdao.getObservableBills());
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
        
        Callback<TableColumn<Bills, Void>, TableCell<Bills, Void>> cellFactory2 = new Callback<TableColumn<Bills, Void>, TableCell<Bills, Void>>() {
            @Override
            public TableCell<Bills, Void> call(final TableColumn<Bills, Void> param) {
                final TableCell<Bills, Void> cell = new TableCell<Bills, Void>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	Bills data = getTableView().getItems().get(getIndex());
                        	arabitoGrill.showEditBill(data,tableView);
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
        
        Callback<TableColumn<Bills, Void>, TableCell<Bills, Void>> cellFactory3 = new Callback<TableColumn<Bills, Void>, TableCell<Bills, Void>>() {
            @Override
            public TableCell<Bills, Void> call(final TableColumn<Bills, Void> param) {
                final TableCell<Bills, Void> cell = new TableCell<Bills, Void>() {

                    private final Button btn = new Button("Pay");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	Bills data = getTableView().getItems().get(getIndex());
                        	BillsDAO bdao = new BillsDAO();
                        	
                        	data.setSpend("PAID");
                        	
                        	bdao.update(data);
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
        updateColumn.setCellFactory(cellFactory2);
        payColumn.setCellFactory(cellFactory3);
        actionColumn.getColumns().addAll(updateColumn, payColumn, deleteColumn);
        updateColumn.prefWidthProperty().bind(tableView.widthProperty().divide(8));
    }
}
