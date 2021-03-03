package model;

public class Heater3 implements HeaterState{

  Heater3(Heater heater){
    int limit = 40000;

    Thread timeOut = new Thread(()->
    {
      try {
        Thread.sleep(limit);
        if(this.getPower() == 3)
        {
          this.turnDown(heater);
        }
      }
      catch(InterruptedException e)
      {
        e.printStackTrace();
      }

    });
    timeOut.setDaemon(true);
    timeOut.start();
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