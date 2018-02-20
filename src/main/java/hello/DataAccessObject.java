package hello;

import org.springframework.stereotype.Component;

@Component
public class DataAccessObject {

  public void accessDatabase() {
    try {
      Thread.sleep((long) (Math.random() * 50));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
