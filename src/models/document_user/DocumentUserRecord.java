package models.document_user;

import hailt.models.ObjectRecord;

public class DocumentUserRecord {
  private String userCode, documentCode, receivedAt;

  public DocumentUserRecord(ObjectRecord object) {
  }

  public DocumentUserRecord(String... strings) {
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

}
