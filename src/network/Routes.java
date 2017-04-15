package network;

import java.net.DatagramPacket;

import controllers.DemoController;
import controllers.ProcessesController;

public class Routes {
  private static final String ROUTE_NOT_MATCH = "404 Not Found";

  public static String solve(DatagramPacket receive) {
    String receiveMessage = new String(receive.getData()).trim();
    if (receiveMessage.equals("/demo")) {
      return DemoController.demo();
    }
    if (receiveMessage.indexOf(ProcessesController.RESOURCES) == 0) {
      return new ProcessesController(receiveMessage).solve();
    }
    return ROUTE_NOT_MATCH;
  }
}
