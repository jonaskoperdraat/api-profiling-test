package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  public Greeting createGreeting(String name) {
    // Simulate doing some heavy stuff.
    try {
      Thread.sleep((long) (Math.random() * 20));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new Greeting(counter.incrementAndGet(),
        String.format(template, name));
  }

}
