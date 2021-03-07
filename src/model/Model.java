package model;
import utility.observer.subject.NamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject {

  int getPower();
  void turnUp();
  void turnDown();
  void setLow(double low);
  void setHigh(double high);
  double getT0();
  double getT1();
  double getT2();
  Heater getHeater();
  ArrayList<Temperature> getTemperatures();
  void addTemperature(String name, String temperature);
}
