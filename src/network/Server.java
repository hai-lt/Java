package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
  private static int MAX_RECEIVING_BYTES = 35000;
  private static Server instance = null;
  private DatagramSocket socket;
  private int port;
  private byte[] incomingData;
  private boolean isRunning;

  private Server() {
    port = 9876;
    incomingData = new byte[MAX_RECEIVING_BYTES];
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
      int i = 0;
      isRunning = true;
      socket = new DatagramSocket(Server.getInstance().getPort(), NetworkInfo.getInstance().getLocalAddress());
      while (true) {
        DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
        socket.receive(incomingPacket);
        i++;
        solve(incomingPacket);
        if (i == 2) {
          close();
        }
      }

    } catch (SocketException e) {
      e.printStackTrace();
    } catch (IOException i) {
      i.printStackTrace();
    }
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

  private void solve(DatagramPacket incomingPacket) {
    new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          String message = new String(incomingPacket.getData());
          System.out.println("Received message from client: " + message);

          InetAddress incomingAddress = incomingPacket.getAddress();
          int port = incomingPacket.getPort();
          String reply = "Thank you for the message";
          byte[] data = reply.getBytes();

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