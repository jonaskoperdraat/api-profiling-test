package hello;

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

  public Object invoke(MethodInvocation method) throws Throwable {

    StopWatch stopWatch = (StopWatch) request.getAttribute("stopwatch");
    if (stopWatch == null) {
      return method.proceed();
    }

    // Before calling the method

    Class<?> declaringClass = method.getMethod().getDeclaringClass();
    if (GreetingController.class.equals(declaringClass)) {
      stopWatch.stop();
    }

    if (DataAccessObject.class.equals(declaringClass)) {
      stopWatch.start("db_access");
    }

    if (ResponseBuilder.class.equals(declaringClass)) {
      stopWatch.start("create_response");
    }

    // Call the method
    Object result = method.proceed();

    // After calling the method
    if (ResponseBuilder.class.equals(declaringClass)) {
      stopWatch.stop();
    }

    if (DataAccessObject.class.equals(declaringClass)) {
      stopWatch.stop();
    }

    if (GreetingController.class.equals(declaringClass)) {
      stopWatch.start("response_processing");
    }

    return result;

  }

}