package hello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javafx.scene.paint.Stop;
import org.springframework.util.StopWatch;

public class RequestTiming {

  List<StopWatch> stopwatches = new ArrayList<>();

  public void stopCurrentTask() {
    if (!stopwatches.isEmpty()) {
      StopWatch stopWatch = stopwatches.get(stopwatches.size()-1);
      if (stopWatch != null) {
        stopWatch.stop();
      }
    }
  }

  public void startNewTask(String name) {
    stopCurrentTask();
    StopWatch stopWatch = new StopWatch();
    stopWatch.start(name);
    stopwatches.add(stopWatch);
  }

  @Override
  public String toString() {
    return "RequestTiming{" +
        "stopwatches=" + stopwatches +
        '}';
  }
}
