package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
  private static final long DEFAULT_TIMEOUT = 3000;
  private static final String TIMEOUT_MESSAGE = "Request timeout";
  private InetAddress ipAddress;
  private int port;
  private long timeout;

  public Client(InetAddress ipAddress, int port) {
    this.ipAddress = ipAddress;
    this.port = port;
    this.timeout = DEFAULT_TIMEOUT;
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

      // generate a thread to catch timeout
      Thread timeoutCatching = detectTimeout(socket);

      socket.receive(incomingPacket);
      // if socket has already been responded, then thread will be stopped
      timeoutCatching.stop();
      responseMessage = new String(incomingPacket.getData()).trim();
      socket.close();
    } catch (SocketException e) {
      responseMessage = TIMEOUT_MESSAGE;
    } catch (IOException e) {
      responseMessage = e.getMessage();
    }
    return responseMessage;
  }

  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("You need to pass the params in order: <Ip address> <port> <route>");
      return;
    }
    ;
    Client client;
    try {
      client = new Client(InetAddress.getByName(args[0]), Integer.parseInt(args[1]));
      System.out.println(client.request(args[2]));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
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

  public long getTimeout() {
    return timeout;
  }

  public void setTimeout(long timeout) {
    this.timeout = timeout;
  }

  private Thread detectTimeout(DatagramSocket socket) {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          Thread.sleep(timeout);
        } catch (InterruptedException e) {
        }
        if (!socket.isClosed()) {
          socket.close();
        }
      }
    });
    thread.start();
    return thread;
  }

  @Override
  public String toString() {
    return getIpAddress() + ":" + getPort();
  }
}