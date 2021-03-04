package model;
import

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model, PropertyChangeListener {

  private Heater heater;
  private double tempHigh;
  private double tempLow;
  private PropertyChangeSupport propertyChangeSupport;
  private TemperatureSimulation temperatureSimulation;

  public ModelManager()
  {
    this.heater = new Heater();

  }

  public Heater getHeater()
  {
    return heater;
  }

  @Override public int getPower()
  {
    return 0;
  }

  @Override public void turnUp()
  {

  }

  @Override public void turnDown()
  {

  }

  @Override public void setLow(double low)
  {

  }

  @Override public void setHigh(double high)
  {

  }

  @Override public double getT0()
  {
    return 0;
  }

  @Override public double getT1()
  {
    return 0;
  }

  @Override public double getT2()
  {
    return 0;
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {

  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
