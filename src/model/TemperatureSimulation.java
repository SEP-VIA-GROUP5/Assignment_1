package model;

import model.*;
import utility.observer.subject.NamedPropertyChangeSubject;
import viewmodel.ViewModelFactory;

import javax.swing.plaf.TableHeaderUI;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureSimulation implements NamedPropertyChangeSubject
{
  private int d1; //distance 1
  private int d2; //distance 2
  private double t0;
  private double t1;
  private double t2;
  private Model model;
  private static final int min = -10;
  private static final int max = 10;
  private PropertyChangeSupport propertyChangeSupport;

  public TemperatureSimulation(int d1, int d2, Model model)
  {
    propertyChangeSupport = new PropertyChangeSupport(this);
    this.d1 = d1;
    this.d2 = d2;
    this.model = model;
    Thread t00 = new Thread(() -> {
      while(true)
      {
        try
        {
          this.t0 = externalTemperature(t0);
          Thread.sleep(10000);
          propertyChangeSupport.firePropertyChange("t0", null, t0);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    });
    Thread t11 = new Thread(() -> {
      while(true)
      {
        try
        {
          this.t1 = insideTemperature(t1, model.getHeater().getPower(), d1, t0,
                  6);
          Thread.sleep(6000);
          propertyChangeSupport.firePropertyChange("t1", null, t1);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    });
    Thread t22 = new Thread(() -> {
      while(true)
      {
        try
        {
          this.t2 = insideTemperature(t2, model.getHeater().getPower(), d2, t0,
                  6);
          Thread.sleep(6000);
          propertyChangeSupport.firePropertyChange("t2", null, t2);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    });
    t00.start();
    t11.start();
    t22.start();
  }

//  @Override public void run()
//  {
//    while(true)
//    {
//        try{
//          this.t0 = externalTemperature(t0);
//          Thread.sleep(10000);
//          model.addTemperature(t0,"t0");
//        }
//        catch (InterruptedException e)
//        {
//          e.printStackTrace();
//        }
//
//        try{
//          this.t1 = insideTemperature(t1, model.getHeater().getPower(), d1, t0, 6);
//          Thread.sleep(6000);
//          model.addTemperature(t1,"t1");
//        }
//        catch (InterruptedException e)
//        {
//          e.printStackTrace();
//        }
//
//        try{
//          this.t2 = insideTemperature(t2, model.getHeater().getPower(), d2, t0, 6);
//          Thread.sleep(6000);
//          model.addTemperature(t2,"t2");
//        }
//        catch (InterruptedException e)
//        {
//          e.printStackTrace();
//        }
//
//
//    }
//
//  }

  /*** Calculating the internal temperature in one of two locations.
   *
   * *This includes a term from a heater (depending on location and * heaters power),
   * and a term from an outdoor heat loss.* Values are only valid in the outdoor temperature range
   * [-20; 20]* and when s, the number of seconds between each measurements are* between 4 and 8 seconds.*
   * * @param t  the last measured temperature*
   * @param p  the heaters power {0, 1, 2 or 3} where 0 is turned off, *
   * 1 is low, 2 is medium and 3 is high*
   * @param d  the distance between heater and measurements {1 or 7}*
   * where 1 is close to the heater and 7 is in theopposite corner*
   * @param t0 the outdoor temperature (valid in the range [-20; 20])
   * * @param s the number of seconds since last measurement[4; 8]*
   *@return the temperature*/
  public double insideTemperature(double t, int p, int d, double t0, int s)
  {
    double tMax = Math.min(11 * p + 10, 11 * p + 10 + t0);
    tMax = Math.max(Math.max(t, tMax), t0);
    double heaterTerm = 0;
    if (p > 0)
    {
      double den = Math.max((tMax * (20 - 5 * p) * (d + 5)), 0.1);
      heaterTerm = 30 * s * Math.abs(tMax - t) / den;
    }
    double outdoorTerm = (t - t0) * s / 250.0;
    t = Math.min(Math.max(t - outdoorTerm + heaterTerm, t0), tMax);
    return t;
  }

  /*** Calculating the external temperature.* Values are only valid if the temperature is being measured
   * approximately every 10th second.*
   * * @param t0  the last measured external temperature
   * @return an updated external temperature
   * */
  public double externalTemperature(double t0)
  {
    double left = t0 - min;
    double right = max - t0;
    int sign = Math.random() * (left + right) > left ? 1 : -1;
    t0 += sign * Math.random();
    return t0;
  }

  public double getT0()
  {
    return t0;
  }

  public double getT1()
  {
    return t1;
  }

  public double getT2()
  {
    return t2;
  }

  @Override public void addListener(String propertyName,
                                    PropertyChangeListener listener)
  {
    if (propertyName == null)
    {
      propertyChangeSupport.addPropertyChangeListener(listener);
    }
    else
    {
      propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }
  }

  @Override public void removeListener(String propertyName,
                                       PropertyChangeListener listener)
  {
    if (propertyName == null)
    {
      propertyChangeSupport.removePropertyChangeListener(listener);
    }
    else
    {
      propertyChangeSupport
              .removePropertyChangeListener(propertyName, listener);
    }
  }
}
