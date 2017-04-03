package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
  DatagramSocket Socket;
  private byte[] incomingData;

  public Client() {
    incomingData = new byte[1024];
  }

  public void createAndListenSocket() {
    try {
      Socket = new DatagramSocket();
      // get ip address
      InetAddress IPAddress = NetworkInfo.getInstance().getLocalAddress();

      // create a string to send
      String sentence = "This is a message from me, a client";
      byte[] data = sentence.getBytes();

      // send string to ip address with port is 9876
      DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, Server.getInstance().getPort());
      Socket.send(sendPacket);
      System.out.println("Client: Sended");

      // receive response data from server
      DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
      Socket.receive(incomingPacket);

      // print data out
      String response = new String(incomingPacket.getData());
      System.out.println("Response from server:" + response);

      // close socket
      Socket.close();

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Client client = new Client();
    client.createAndListenSocket();
  }
}