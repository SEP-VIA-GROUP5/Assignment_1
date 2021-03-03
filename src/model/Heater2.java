package model;

public class Heater2 implements HeaterState{

  @Override public void turnUp(Heater heater)
  {
    heater.setState(new Heater3(heater));
  }

  @Override public void turnDown(Heater heater)
  {
    heater.setState(new Heater1());
  }

  @Override public int getPower()
  {
    return 2;
  }
}