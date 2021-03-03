package model;

import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Heater implements UnnamedPropertyChangeSubject
{
  private HeaterState state;
  private PropertyChangeSupport propertyChangeSupport;

  public Heater()
  {
     state = new Heater0();
     propertyChangeSupport = new PropertyChangeSupport(this);
  }

  public void setState(HeaterState state)
  {
    this.state = state;
  }

  public void turnUp()
  {

  }
  public void turnDown()
  {

  }

  public void timeout()
  {

  }

  public int getPower()
  {
    state.getPower();
  }

  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName,PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }
}
