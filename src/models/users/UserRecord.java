package models.users;

import java.util.ArrayList;

import hailt.models.ObjectRecord;

public class UserRecord {
  private String code, fullName, phone, birdthDate, level;
  private String position, password, accessRole, status, avatar;

  public UserRecord(ObjectRecord object) {
    setAttributes(object.getValues());
  }

  public UserRecord(String... strings) {
    setAttributes(strings);
  }

  public void setAttributes(String... strings) {
    code = strings[0];
    fullName = strings[1];
    phone = strings[2];
    birdthDate = strings[3];
    level = strings[4];
    position = strings[5];
    password = strings[6];
    accessRole = strings[7];
    status = strings[8];
    avatar = strings[9];
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getBirdthDate() {
    return birdthDate;
  }

  public void setBirdthDate(String birdthDate) {
    this.birdthDate = birdthDate;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAccessRole() {
    return accessRole;
  }

  public void setAccessRole(String accessRole) {
    this.accessRole = accessRole;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public static ArrayList<UserRecord> convertFrom(ArrayList<ObjectRecord> records) {
    ArrayList<UserRecord> users = new ArrayList<>();
    for (ObjectRecord objectRecord : records) {
      users.add(new UserRecord(objectRecord));
    }
    return users;
  }
}
