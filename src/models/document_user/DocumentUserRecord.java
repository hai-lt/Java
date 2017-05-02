package models.document_user;

import java.util.ArrayList;

import hailt.models.ObjectRecord;
import models.documents.Document;
import models.documents.DocumentRecord;
import models.users.User;
import models.users.UserRecord;

public class DocumentUserRecord {
  private String userCode, documentCode, receivedAt;

  public DocumentUserRecord(ObjectRecord object) {
    setAttributes(object.getValues());
  }

  public DocumentUserRecord(String... strings) {
    setAttributes(strings);
  }

  public void setAttributes(String... strings) {
    userCode = strings[0];
    documentCode = strings[1];
    receivedAt = strings[2];
  }

  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }

  public String getDocumentCode() {
    return documentCode;
  }

  public void setDocumentCode(String documentCode) {
    this.documentCode = documentCode;
  }

  public String getReceivedAt() {
    return receivedAt;
  }

  public void setReceivedAt(String receivedAt) {
    this.receivedAt = receivedAt;
  }

  public UserRecord receiver() {
    try {
      return new UserRecord(new User().all("code = '" + getUserCode() + "'").get(0));
    } catch (Exception e) {
      return null;
    }
  }

  public DocumentRecord document() {
    try {
      return new DocumentRecord(new Document().all("code = " + getDocumentCode()).get(0));
    } catch (Exception e) {
      return null;
    }
  }

  public static ArrayList<DocumentUserRecord> convertFrom(ArrayList<ObjectRecord> objects) {
    ArrayList<DocumentUserRecord> du = new ArrayList<>();
    for (ObjectRecord objectRecord : objects) {
      du.add(new DocumentUserRecord(objectRecord));
    }
    return du;
  }

}
