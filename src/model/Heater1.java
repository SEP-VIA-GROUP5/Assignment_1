package model;

public class Heater1 implements HeaterState{

  @Override public void turnUp(Heater heater)
  {
    heater.setState(new Heater2());
  }

  @Override public void turnDown(Heater heater)
  {
    heater.setState(new Heater0());
  }

  @Override public int getPower()
  {
    return 1;
  }
}