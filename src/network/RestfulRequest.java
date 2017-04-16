package network;

import java.net.InetAddress;

public class RestfulRequest extends Client {

  public RestfulRequest(InetAddress ipAddress, int port) {
    super(ipAddress, port);
  }

  public String get(String... path) {
    return request(RestfulAPI.formatGet(path));
  }
  
  public String get(String resource, long id) {
    return request(RestfulAPI.formatGet(resource, id));
  }

  public String put(String resource, long id) {
    return request(RestfulAPI.formatPut(resource, id));
  }

  public String delete(String resource, long id) {
    return request(RestfulAPI.formatDelete(resource, id));
  }

  public String post(String resource) {
    return request(RestfulAPI.formatPost(resource));
  }

}
