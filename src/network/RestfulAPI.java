package network;

public abstract class RestfulAPI {
  public static final String NOT_MATCH = "ROUTE NOT MATCH";
  public static final String FAILED_MESSAGE = "400 FAILED";
  public static final String DELETE_METHOD = "/DELETE";
  public static final String PUT_METHOD = "/PUT";
  public static final String POST_METHOD = "/POST";
  private String request;

  public abstract String create();

  public abstract String index();

  public abstract String show();

  public abstract String update();

  public abstract String destroy();

  public abstract String getResources();

  public RestfulAPI(String request) {
    super();
    this.request = request;
  }

  public String solve() {
    if (request.indexOf(getResources()) == -1) {
      return NOT_MATCH;
    }
    if (getResources().equals(request) || getResources().length() == request.indexOf("?")) {
      return index();
    }
    if (getResources().length() == request.indexOf(DELETE_METHOD)) {
      return destroy();
    }
    if (getResources().length() == request.indexOf(PUT_METHOD)) {
      return update();
    }
    if (getResources().length() == request.indexOf(POST_METHOD)) {
      return create();
    }
    System.out.println("routes44 : " + request.indexOf(getResources() + "/"));
    if (0 == request.indexOf(getResources() + "/")) {
      return show();
    }
    return NOT_MATCH;
  }

  public String failed() {
    return FAILED_MESSAGE;
  }

  public String getRequest() {
    return request;
  }

  public static String formatGet(String resource, long id) {
    return resource + "/" + id;
  }

  public static String formatGet(String... path) {
    String resource = path[0];
    String params = "";
    for (int i = 1; i < path.length; i++) {
      params += path[i] + "&";
    }
    if (!params.equals("")) {
      params.substring(0, params.length() - 2);
      resource += "?" + params;
    }
    return resource;
  }

  public static String formatPut(String resource, long id) {
    return resource + PUT_METHOD + "/" + id;
  }

  public static String formatDelete(String resource, long id) {
    return resource + DELETE_METHOD + "/" + id;
  }

  public static String formatPost(String resource) {
    return resource + POST_METHOD;
  }
}
