package network;

import java.net.DatagramPacket;

import controllers.DemoController;

public class Routes {
  private static final String ROUTE_NOT_MATCH = "404 Not Found";

  public static String solve(DatagramPacket receive) {
    String receiveMessage = new String(receive.getData()).trim();
    switch (receiveMessage) {
    case "demo":
      return DemoController.demo();
    }
    return ROUTE_NOT_MATCH;
  }
}
