package model;

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
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.temperatureSimulation = new TemperatureSimulation(1,7,this);
    this.temperatureSimulation.addListener("t0",this);
    this.temperatureSimulation.addListener("t1",this);
    this.temperatureSimulation.addListener("t2",this);
    this.heater.addListener("heater",this);

  }

  public Heater getHeater()
  {
    return heater;
  }

  @Override public int getPower()
  {
    return heater.getPower();
  }

  @Override public void turnUp()
  {
    heater.turnUp();
  }

  @Override public void turnDown()
  {
    heater.turnDown();
  }

  @Override public void setLow(double low)
  {

  }

  @Override public void setHigh(double high)
  {

  }

  @Override public double getT0()
  {
    return temperatureSimulation.getT0();
  }

  @Override public double getT1()
  {
    return temperatureSimulation.getT1();
  }

  @Override public double getT2()
  {
    return temperatureSimulation.getT2();
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(propertyName,listener);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    propertyChangeSupport.firePropertyChange(evt.getPropertyName(), evt.getOldValue(),evt.getNewValue());
  }
}
