package model;

public interface HeaterState {


  default void turnUp(Heater heater){}
  default void turnDown(Heater heater){};
  public int getPower();

}
