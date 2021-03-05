package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;

import java.beans.PropertyChangeListener;

import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

public class TemperatureViewModel implements PropertyChangeListener
{

  private DoubleProperty t0;
  private DoubleProperty t1;
  private DoubleProperty t2;
  private IntegerProperty power;
  private DoubleProperty tempLow;
  private DoubleProperty tempHigh;
  private StringProperty error;
  private Model model;
  private PropertyChangeSupport propertyChangeSupport;

  public TemperatureViewModel(Model model)
  {
    this.model = model;

    this.t0 = new SimpleDoubleProperty(model.getT0());
    this.t1 = new SimpleDoubleProperty(model.getT1());
    this.t2 = new SimpleDoubleProperty(model.getT2());
    this.power = new SimpleIntegerProperty();
    this.tempLow = new SimpleDoubleProperty();
    this.tempHigh = new SimpleDoubleProperty();
    this.error = new SimpleStringProperty();
    this.model.addListener("t0", this);
    this.model.addListener("t1", this);
    this.model.addListener("t2", this);
    this.model.addListener("heater", this);
  }

  @Override public void propertyChange(
      PropertyChangeEvent evt) //platform run later
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "t0":
        {
          t0.set((Double) evt.getNewValue());
          break;
        }
        case "t1":
        {
          double index = (Double) evt.getNewValue();
          t1.set(index);
          if (index < tempLow.get())
          {
            error.set("temperature is too low!");
          }
          else if (index > tempHigh.get())
          {
            error.set("temperature is too high!");
          }
          break;
        }
        case "t2":
        {
          double index = (Double) evt.getNewValue();
          t2.set(index);
          if (index < tempLow.get())
          {
            error.set("temperature is too low!");
          }
          else if (index > tempHigh.get())
          {
            error.set("temperature is too high!");
          }
        }
        case "heater":
          power.set(model.getPower());
          break;

      }
    });

  }

  public DoubleProperty getT0()
  {
    return t0;
  }

  public DoubleProperty getT1()
  {
    return t1;
  }

  public DoubleProperty getT2()
  {
    return t2;
  }

  public IntegerProperty getPower()
  {
    return power;
  }

  public DoubleProperty getTempLow()
  {
    return tempLow;
  }

  public DoubleProperty getTempHigh()
  {
    return tempHigh;
  }

  public StringProperty getError()
  {
    return error;
  }

  public void setTempLow()
  {
    model.setLow(tempLow.get());
  }

  public void setTempHigh()
  {
    model.setHigh(tempHigh.get());
  }

  public void turnUp()
  {
    model.turnUp();
  }

  public void turnDown()
  {
    model.turnDown();
  }
}
