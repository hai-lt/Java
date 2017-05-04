package models.system_config;

import java.sql.SQLException;
import java.util.HashMap;

import hailt.models.ObjectRecord;

public class SystemConfigRecord {
  private String rememberUser;
  private static SystemConfigRecord instance;

  public static SystemConfigRecord getInstance() {
    if (instance != null) {
      return instance;
    }
    synchronized (SystemConfigRecord.class) {
      if (instance != null) {
        return instance;
      }
      instance = new SystemConfigRecord();
      return instance;
    }
  }

  private SystemConfigRecord() {
    try {
      setAttributes(getRecord().getValues());
    } catch (Exception e) {
      initializeSystemConfig();
    }
  }

  private void initializeSystemConfig() {
    try {
      new SystemConfig().create(new HashMap<>());
      setAttributes(getRecord().getValues());
    } catch (SQLException e1) {
      System.out.println(e1.getMessage());
    }
  }

  private ObjectRecord getRecord() {
    try {
      return new SystemConfig().all().get(0);
    } catch (Exception e) {
      return null;
    }
  }

  public SystemConfigRecord reload() {
    setAttributes(getRecord().getValues());
    return instance;
  }

  public void setAttributes(String... strings) {
    rememberUser = strings[0];
  }

  public String getRememberUser() {
    return rememberUser;
  }

  public void setRememberUser(String rememberUser) {
    this.rememberUser = rememberUser;
  }

}
