package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * POC application for testing custom profiling of a REST application.
 *
 * Based on https://spring.io/guides/gs/rest-service/
 * Extended with an Interceptor and Filter to record various tasks.
 *
 * TODO:
 *  - Add request id to logging
 *  - Check timing for concurrent requests
 *  - Rename classes to make more sense
 *  - Change folder structure; remove top level nesting
 *  - Strip tutorial-documentation from the project
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
