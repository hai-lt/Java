package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
  private DatagramSocket socket;
  private InetAddress ipAddress;
  private int port;

  public Client(InetAddress ipAddress, int port) {
    this.ipAddress = ipAddress;
    this.port = port;
    try {
      socket = new DatagramSocket();
    } catch (SocketException e) {
      e.printStackTrace();
    }
  }

  public void close() {
    if (socket == null || socket.isClosed()) {
      return;
    }
    socket.close();
  }

  public InetAddress getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(InetAddress ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String send(String message) {
    byte[] data = message.getBytes();

    // send string to ip address with port is 9876
    DatagramPacket sendPacket = new DatagramPacket(data, data.length, getIpAddress(), getPort());
    try {
      socket.send(sendPacket);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // receive response data from server
    byte[] incomingData = new byte[getMaxBytes()];
    DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
    try {
      socket.receive(incomingPacket);
    } catch (IOException e) {
      e.printStackTrace();
    }

    String responseMessage = new String(incomingPacket.getData());
    System.out.println("Response from server:" + responseMessage);
    return responseMessage;
  }

  public static void main(String[] args) {
    Client client = new Client(NetworkInfo.getInstance().getLocalAddress(), Server.getInstance().getPort());
    client.send("Hey, this is the first time");
    Client client2 = new Client(NetworkInfo.getInstance().getLocalAddress(), Server.getInstance().getPort());
    client2.send("And the second too");
    client.close();
    client2.close();
  }

  public int getMaxBytes() {
    return Server.MAX_RECEIVING_BYTES;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }
}