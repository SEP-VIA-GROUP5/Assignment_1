package viewmodel;

import model.Model;

public class ViewModelFactory {
    private TemperatureViewModel temperatureViewModel;
    private LogViewModel logViewModel;
    private Model model;

    public ViewModelFactory(Model model)
    {
        temperatureViewModel = new TemperatureViewModel(model);
        logViewModel = new LogViewModel(model);
    }

    public LogViewModel getLogViewModel() {
        return logViewModel;
    }

    public TemperatureViewModel getTemperatureViewModel() {
        return temperatureViewModel;
    }
}
