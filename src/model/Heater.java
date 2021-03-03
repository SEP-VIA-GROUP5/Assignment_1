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
    state.turnUp(this);
  }
  public void turnDown()
  {
    state.turnDown(this);
  }

  public int getPower()
  {
    state.getPower();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }
}
