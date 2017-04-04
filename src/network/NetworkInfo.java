package network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

public class NetworkInfo {
  private static int MAX_PORT = 65536;
  private static final String WLAN_NAME = "wlan0";
  private static NetworkInfo instance;
  private ArrayList<InetAddress> whiteListAddresses;
  private ArrayList<InetAddress> blackListAddresses;
  private int port;

  private NetworkInfo() {
    setPort();
    whiteListAddresses = new ArrayList<>();
    blackListAddresses = new ArrayList<>();
  }

  public static NetworkInfo getInstance() {
    if (instance != null) {
      return instance;
    }
    synchronized (NetworkInfo.class) {
      if (instance != null) {
        return instance;
      } else {
        instance = new NetworkInfo();
        return instance;
      }
    }
  }

  public static void main(String args[]) throws SocketException {
    System.out.println(NetworkInfo.getInstance().getLocalAddress().toString());
  }

  public void addToWhiteList(InetAddress whiteAddress) {
    whiteListAddresses.add(whiteAddress);
  }

  public void addToBlackList(InetAddress blackAddress) {
    blackListAddresses.add(blackAddress);
  }

  public boolean isWhiteAddress(InetAddress address) {
    for (InetAddress inetAddress : whiteListAddresses) {
      if (address.equals(inetAddress)) {
        return true;
      }
    }
    return false;
  }

  public boolean isBlackAddress(InetAddress address) {
    for (InetAddress inetAddress : blackListAddresses) {
      if (inetAddress.equals(address)) {
        return true;
      }
    }
    return false;
  }

  public void removeWhiteAddress(InetAddress address) {
    whiteListAddresses.remove(address);
  }

  public void removeBlackAddress(InetAddress address) {
    blackListAddresses.remove(address);
  }

  public InetAddress getLocalAddress() {
    try {
      InetAddress localhost = InetAddress.getLocalHost();
      Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
      while (ifaces.hasMoreElements()) {
        NetworkInterface iface = ifaces.nextElement();
        Enumeration<InetAddress> addresses = iface.getInetAddresses();

        while (addresses.hasMoreElements() && iface.getName().equals(WLAN_NAME)) {
          InetAddress addr = addresses.nextElement();
          if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
            return addr;
          }
        }
      }
      return localhost;
    } catch (Exception e) {
      return null;
    }
  }

  private void setPort() {
    port = Math.abs(new Random().nextInt()) % MAX_PORT;
  }

  public int getPort() {
    return port;
  }
}
