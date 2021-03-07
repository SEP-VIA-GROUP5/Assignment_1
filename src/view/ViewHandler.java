package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import view.LogViewController;
import view.MainViewController;
import viewmodel.ViewModelFactory;

public class ViewHandler
{
    private Scene currentScene;
    private Stage primaryStage;
    private ViewModelFactory viewModelFactory;
    private Region root;
    private MainViewController mainViewController;
    private LogViewController logViewController;

    public ViewHandler(ViewModelFactory viewModelFactory)
    {
        this.viewModelFactory = viewModelFactory;
        currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        switchView("Heater");
    }

    public void stop()
    {
        primaryStage.close();
    }

    void switchView(String view)
    {
        root = null;
        try
        {
            switch (view)
            {
                case "Heater":
                    root = loadMainView("MainView.fxml");
                    break;
                    case "History":
                    root = loadLogView("LogView.fxml");
                    break;
            }
            currentScene.setRoot(root);
            String title = "";
            if (root.getUserData() != null)
            {
                title += root.getUserData();
            }
            primaryStage.setTitle(title);
            primaryStage.setScene(currentScene);
            primaryStage.setWidth(root.getPrefWidth());
            primaryStage.setHeight(root.getPrefHeight());
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Region loadMainView(String fxmlFile)
    {
        if (mainViewController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                mainViewController = loader.getController();
                mainViewController.init(this, viewModelFactory.getTemperatureViewModel(), root);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            mainViewController.reset();
        }
        return mainViewController.getRoot();
    }

   private Region loadLogView(String fxmlFile)
    {
        if (logViewController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                logViewController = loader.getController();
                logViewController.init(this, viewModelFactory.getLogViewModel(), root);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            logViewController.reset();
        }
        return logViewController.getRoot();
    }


}