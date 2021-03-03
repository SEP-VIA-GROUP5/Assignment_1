package model;

public class Heater0 implements HeaterState{

  @Override public void turnUp(Heater heater)
  {
    heater.setState(new Heater1());
  }

  @Override public void turnDown(Heater heater)
  {
  }

  @Override public int getPower()
  {
    return 0;
  }
}

