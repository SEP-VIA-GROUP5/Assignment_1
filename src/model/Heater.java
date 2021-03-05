package model;

import utility.observer.subject.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Heater implements NamedPropertyChangeSubject
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
    propertyChangeSupport.firePropertyChange("heater",null,getPower());
  }
  public void turnDown()
  {
    state.turnDown(this);
    propertyChangeSupport.firePropertyChange("heater",null,getPower());
  }

  public int getPower()
  {
    return state.getPower();
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }
}
