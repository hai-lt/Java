package models.users;

import java.util.ArrayList;

import hailt.models.ObjectRecord;
import models.documents.Document;
import models.documents.DocumentRecord;

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

  public ArrayList<DocumentRecord> receiveDocuments() {
    return DocumentRecord.convertFrom(new Document()
        .query("SELECT * FROM document" + " INNER JOIN document_user on document_user.document_code = document.code"
            + " INNER JOIN user on user.code = document_user.user_code" + " WHERE document_user.user_code = '"
            + getCode() + "'"));
  }

  public ArrayList<DocumentRecord> receiveDocuments(String condition) {
    String query = "SELECT * FROM document" + " INNER JOIN document_user on document_user.document_code = document.code"
        + " INNER JOIN user on user.code = document_user.user_code" + " WHERE document_user.user_code = '" + getCode()
        + "'";
    if (condition != null && !condition.equals("")) {
      query += " AND " + condition;
    }
    return DocumentRecord.convertFrom(new Document().query(query));
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

  public ArrayList<DocumentRecord> getSentDocuments() {
    return DocumentRecord.convertFrom(new Document().all("user_code = '" + getCode() + "'"));
  }

  public static ArrayList<UserRecord> convertFrom(ArrayList<ObjectRecord> records) {
    ArrayList<UserRecord> users = new ArrayList<>();
    for (ObjectRecord objectRecord : records) {
      users.add(new UserRecord(objectRecord));
    }
    return users;
  }

  @Override
  public String toString() {
    return getFullName();
  }
}
