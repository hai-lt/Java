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
}
