import model.*;

import javax.swing.plaf.TableHeaderUI;

public class TemperatureSimulation
{
  private int d1; //distance 1
  private int d2; //distance 2
  private double t0;
  private double t1;
  private double t2;
  private ModelManager manager;
  private static final int min = -10;
  private static final int max = 10;

  public TemperatureSimulation(int d1, int d2, ModelManager manager)
  {
    this.d1 = d1;
    this.d2 = d2;
    this.manager = manager;
    Thread t00 = new Thread(() ->
    {
      try{
        this.t0 = externalTemperature(t0);
        Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    });

    Thread t11 = new Thread(() ->
    {
      try{
        this.t1 = insideTemperature(t1, manager.getHeater().getPower(), d1, t0, 6);
        Thread.sleep(6000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    });

    Thread t22 = new Thread(() ->
    {
      try{
        this.t2 = insideTemperature(t2, manager.getHeater().getPower(), d2, t0, 6);
        Thread.sleep(6000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    });
  }

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
   * @param min a lower limit (may temporally be deceeded)
   * @param max an upper limit (may temporally be exceeded)
   * @return an updated external temperature*/
  public double externalTemperature(double t0)
  {
    double left = t0 - min;
    double right = max - t0;
    int sign = Math.random() * (left + right) > left ? 1 : -1;
    t0 += sign * Math.random();
    return t0;
  }

}
