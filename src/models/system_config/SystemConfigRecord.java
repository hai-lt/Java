package models.system_config;

import hailt.models.ObjectRecord;

public class SystemConfigRecord {
  private String rememberUser;

  public SystemConfigRecord(ObjectRecord object) {
    setAttributes(object.getValues());
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
