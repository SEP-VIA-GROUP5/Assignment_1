package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

public class LogViewModel {
    private ObservableList<TemperatureLog> temperaturesLog;
    private Model model;

    public LogViewModel(Model model)
    {
        this.model = model;
        temperaturesLog = FXCollections.observableArrayList();
        update();
    }

    public void update() {
        temperaturesLog.clear();
        if(model.getTemperatures().size() > 20)
        for(int i = model.getTemperatures().size()-1; i >= model.getTemperatures().size()-20; i--)
        {
            temperaturesLog.add(new TemperatureLog(model.getTemperatures().get(i)));
        }
        else {
            for(int i = 0; i < model.getTemperatures().size(); i++)
            {
                temperaturesLog.add(new TemperatureLog(model.getTemperatures().get(i)));
            }
        }
    }

    public ObservableList<TemperatureLog> getTemperaturesLog() {
        return temperaturesLog;
    }
}
