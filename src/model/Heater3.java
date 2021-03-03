package model;

public class Heater3 implements HeaterState{

  private int limit = 40000;

  @Override public void turnUp(Heater heater)
  {
  }

  @Override public void turnDown(Heater heater)
  {
    heater.setState(new Heater2());
  }

  @Override public int getPower()
  {
    return 3;
  }
}