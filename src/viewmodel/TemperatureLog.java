package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Temperature;

public class TemperatureLog {
    private StringProperty nameProperty;
    private StringProperty temperatureProperty;

    public TemperatureLog(Temperature temperature)
    {
        this.nameProperty = new SimpleStringProperty(temperature.getName());
        this.temperatureProperty = new SimpleStringProperty(temperature.getTemperature());
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public StringProperty getTemperatureProperty() {
        return temperatureProperty;
    }
}
