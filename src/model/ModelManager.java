package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model, PropertyChangeListener {

  private Heater heater;
  private double tempHigh;
  private double tempLow;
  private PropertyChangeSupport propertyChangeSupport;
  private TemperatureSimulation temperatureSimulation;
  private ArrayList<Temperature> temperatures;

  public ModelManager()
  {
    this.heater = new Heater();
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.heater.addListener("heater",this);
    this.temperatureSimulation = new TemperatureSimulation(1,7,this);
    this.temperatureSimulation.addListener(null,this);
    temperatures = new ArrayList<>();
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
    this.tempLow = low;
  }

  @Override public void setHigh(double high)
  {
    this.tempHigh = high;
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

  @Override public void propertyChange(PropertyChangeEvent evt) // ASK ABOUT IF THIS IS NECESSARY
  {
    propertyChangeSupport.firePropertyChange(evt.getPropertyName(), evt.getOldValue(),evt.getNewValue());
  }

  @Override
  public ArrayList<Temperature> getTemperatures() {
    return temperatures;
  }

  @Override
  public void addTemperature(String name, String temperature) {
    temperatures.add(new Temperature(name,temperature));
  }
}
