package system;

import models.users.User;
import models.users.UserRecord;

public class RootSystem {
  private static RootSystem instance;
  private UserRecord currentUser;
  
  private RootSystem() {
    setCurrentUser(new UserRecord(new User().all().get(0)));
  }

  public static RootSystem getInstance() {
    if (instance != null) {
      return instance;
    }
    synchronized (RootSystem.class) {
      if (instance != null) {
        return instance;
      }
      instance = new RootSystem();
      return instance;
    }
  }

  public UserRecord getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(UserRecord currentUser) {
    this.currentUser = currentUser;
  }
  
  
  
}
