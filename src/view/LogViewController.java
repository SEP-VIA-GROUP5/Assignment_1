package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Region;
import model.Heater;
import viewmodel.LogViewModel;
import viewmodel.TemperatureLog;
import viewmodel.ViewModelFactory;

import java.awt.geom.RectangularShape;


public class LogViewController {
    @FXML private TableView<TemperatureLog> temperatureTableView;
    @FXML private TableColumn<TemperatureLog, String> nameColumn;
    @FXML private TableColumn<TemperatureLog, String> temperatureColumn;
    private ViewHandler viewHandler;
    private Region root;
    private LogViewModel logViewModel;

    public LogViewController()
    {

    }

    public void init(ViewHandler viewHandler, LogViewModel logViewModel, Region root)
    {
        this.viewHandler = viewHandler;
        this.logViewModel = logViewModel;
        this.root = root;
        nameColumn.setCellValueFactory(cellData ->
            cellData.getValue().getNameProperty());
        temperatureColumn.setCellValueFactory(cellData ->
            cellData.getValue().getTemperatureProperty());
        reset();
    }

    public void reset()
    {
        temperatureTableView.setItems(logViewModel.getTemperaturesLog());
        logViewModel.update();

    }

    public Region getRoot() {
        return root;
    }

    @FXML private void onQuit()
    {
        viewHandler.stop();
    }

    @FXML private void onBack()
    {
        viewHandler.switchView("Heater");
    }


}