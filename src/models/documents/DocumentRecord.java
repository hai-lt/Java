package models.documents;

import java.util.ArrayList;
import java.util.Vector;

import hailt.models.ObjectRecord;
import models.document_user.DocumentUser;
import models.document_user.DocumentUserRecord;
import models.users.User;
import models.users.UserRecord;

public class DocumentRecord {
  private String code, subject, userCode, content, src, sended_at;

  public DocumentRecord(ObjectRecord object) {
    setAttributes(object.getValues());
  }

  public DocumentRecord(String... strings) {
    setAttributes(strings);
  }

  public void setAttributes(String... strings) {
    code = strings[0];
    subject = strings[1];
    userCode = strings[2];
    content = strings[3];
    src = strings[4];
    sended_at = strings[5];
  }

  public UserRecord sender() {
    return new UserRecord(new User().all("code = '" + userCode + "'").get(0));
  }

  public ArrayList<UserRecord> receivers() {
    return UserRecord.convertFrom(
        new User().query("SELECT * FORM user" + "INNER JOIN document_user ON user.code = document_user.user_code"
            + "INNER JOIN document ON document.code = document_user.document_code"));
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public String getSended_at() {
    return sended_at;
  }

  public void setSended_at(String sended_at) {
    this.sended_at = sended_at;
  }

  public static ArrayList<DocumentRecord> convertFrom(ArrayList<ObjectRecord> documents) {
    ArrayList<DocumentRecord> docs = new ArrayList<>();
    for (ObjectRecord objectRecord : documents) {
      docs.add(new DocumentRecord(objectRecord));
    }
    return docs;
  }

}
