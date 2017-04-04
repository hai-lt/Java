package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
  private InetAddress ipAddress;
  private int port;

  public Client(InetAddress ipAddress, int port) {
    this.ipAddress = ipAddress;
    this.port = port;
  }

  public InetAddress getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(InetAddress ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String request(String message) {
    String responseMessage = null;
    try {
      byte[] data = message.getBytes();
      DatagramSocket socket = new DatagramSocket();

      // send string to ip address with port
      DatagramPacket sendPacket = new DatagramPacket(data, data.length, getIpAddress(), getPort());
      socket.send(sendPacket);

      // receive response data from server
      byte[] incomingData = new byte[getMaxBytes()];
      DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
      socket.receive(incomingPacket);
      responseMessage = new String(incomingPacket.getData()).trim();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return responseMessage;
  }

  public static void main(String[] args) {
    Client client = new Client(NetworkManagement.getInstance().getLocalAddress(), Server.getInstance().getPort());
    System.out.println(client.request("Hey, this is the first time"));
    System.out.println(client.request("demo"));
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