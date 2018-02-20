package hello;

import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContext;

@RestController
public class GreetingController {



  @Autowired DataAccessObject dao;

  @Autowired ResponseBuilder responseBuilder;

  @RequestMapping("/greeting")
  public Greeting greeting(HttpServletRequest request, @RequestParam(value = "name", defaultValue = "World")
      String name) {

    // Simulate database access.
    dao.accessDatabase();

    return responseBuilder.createGreeting(name);
  }
}
