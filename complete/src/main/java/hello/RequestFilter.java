package hello;


import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class RequestFilter implements Filter {

  private static final Logger LOG = LoggerFactory.getLogger(RequestFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    LOG.debug("Initializing RequestFilter.");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    String requestURI = ((RequestFacade) servletRequest).getRequestURI();
    LOG.debug("request filter for {}", requestURI);

    UUID id = UUID.randomUUID();
    servletRequest.setAttribute("id", id);
    StopWatch stopWatch = new StopWatch(id.toString());
    servletRequest.setAttribute("stopwatch", stopWatch);
    stopWatch.start("request_processing");

    filterChain.doFilter(servletRequest, servletResponse);

    stopWatch.stop();
    LOG.debug("Request path: {} {}", requestURI, ((RequestFacade) servletRequest).getMethod());
    LOG.debug("Stopwatch: {}", stopWatch.prettyPrint());
  }

  @Override
  public void destroy() {

  }
}
