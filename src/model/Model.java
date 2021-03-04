package model;
import utility.observer.subject.NamedPropertyChangeSubject;

public interface Model extends NamedPropertyChangeSubject {

  int getPower();
  void turnUp();
  void turnDown();
  void setLow(double low);
  void setHigh(double high);
  double getT0();
  double getT1();
  double getT2();

}
