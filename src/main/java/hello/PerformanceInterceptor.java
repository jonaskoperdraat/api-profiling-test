package hello;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class PerformanceInterceptor implements MethodInterceptor {

  private @Autowired
  HttpServletRequest request;

  private Map<Class, String> classTaskMapping = new HashMap<>();

  {
    classTaskMapping.put(GreetingController.class, "request_processing");
    classTaskMapping.put(DataAccessObject.class, "db_access");
    classTaskMapping.put(ResponseBuilder.class, "create_response");
  }

  public Object invoke(MethodInvocation method) throws Throwable {

    StopWatch stopWatch = (StopWatch) request.getAttribute("stopwatch");
    if (stopWatch == null) {
      return method.proceed();
    }

    // Before calling the method

    Class<?> declaringClass = method.getMethod().getDeclaringClass();
    String previousTask = "other";
    if (stopWatch.isRunning()) {

      previousTask = stopWatch.currentTaskName();
      stopWatch.stop();
    }

    String task = classTaskMapping.getOrDefault(declaringClass, "");
    stopWatch.start(task);

    // Call the method
    Object result = method.proceed();

    // After calling the method
    stopWatch.stop();
    // Continue timing the previous task.
    stopWatch.start(previousTask);

    return result;

  }

}