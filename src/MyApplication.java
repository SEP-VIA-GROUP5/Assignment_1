import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import model.TemperatureSimulation;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.awt.font.TextMeasurer;

public class MyApplication extends Application {
    public void start(Stage primaryStage)
    {
        // Model
        Model model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        // View
        TemperatureSimulation temperatureSimulation = new TemperatureSimulation(1,7,model);

        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);

    }

}
