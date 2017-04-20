package network;

import java.net.DatagramPacket;

import controllers.DemoController;
import controllers.MemoriesController;
import controllers.OperatingSystemController;
import controllers.ProcessesController;

public class Routes {
  private static final String ROUTE_NOT_MATCH = "404 Not Found";

  public static String solve(DatagramPacket receive) {
    String receiveMessage = new String(receive.getData()).trim();
    if (receiveMessage.equals("/demo")) {
      return DemoController.demo();
    }
    if (isResourcesOf(receiveMessage, ProcessesController.RESOURCES)) {
      return new ProcessesController(receive).solve();
    }
    if (isResourcesOf(receiveMessage, MemoriesController.RESOURCES)) {
      return new MemoriesController(receive).solve();
    }
    if (isResourcesOf(receiveMessage, OperatingSystemController.RESOURCES)) {
      return new OperatingSystemController(receive).solve();
    }
    return ROUTE_NOT_MATCH;
  }

  public static boolean isResourcesOf(String receiveMessage, String resources) {
    return receiveMessage.indexOf(resources) == 0;
  }
}
