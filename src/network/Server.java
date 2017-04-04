package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
  public final static int MAX_RECEIVING_BYTES = 35000;
  private static Server instance = null;
  private DatagramSocket socket;
  private int port;
  private boolean isRunning;

  private Server() {
    port = 9876;
  }

  public static Server getInstance() {
    if (instance != null) {
      return instance;
    }

    synchronized (Server.class) {
      if (instance != null) {
        return instance;
      }
      instance = new Server();
      return instance;
    }
  }

  public void run() {
    if (isRunning) {
      return;
    }
    try {
      isRunning = true;
      socket = new DatagramSocket(Server.getInstance().getPort(), NetworkManagement.getInstance().getLocalAddress());
      while (true) {
        byte[] incomingData = new byte[MAX_RECEIVING_BYTES];
        DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
        socket.receive(incomingPacket);

        log(incomingPacket);

        solve(incomingPacket);
      }

    } catch (SocketException e) {
      e.printStackTrace();
    } catch (IOException i) {
      i.printStackTrace();
    }
    isRunning = false;
  }

  public int getPort() {
    return port;
  }

  public void close() {
    if (socket != null && !socket.isClosed()) {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      socket.close();
      isRunning = false;
    }
  }

  private void log(DatagramPacket incoming) {
    System.out.println("Requested: " + new String(incoming.getData()));
  }

  private void solve(DatagramPacket incomingPacket) {
    new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          String response = Routes.solve(incomingPacket);

          InetAddress incomingAddress = incomingPacket.getAddress();
          int port = incomingPacket.getPort();
          byte[] data = response.getBytes();
          DatagramPacket replyPacket = new DatagramPacket(data, data.length, incomingAddress, port);
          socket.send(replyPacket);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    }).start();
  }

  public static void main(String[] args) {
    Server server = Server.getInstance();
    server.run();
  }
}