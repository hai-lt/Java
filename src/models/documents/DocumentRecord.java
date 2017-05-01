package models.documents;

import hailt.models.ObjectRecord;

public class DocumentRecord {
  private String code, subject, userCode, content, src, sended_at;
  public DocumentRecord(ObjectRecord object) {
    setAttributes(object.getValues());
  }
  
  public DocumentRecord(String...strings) {
    setAttributes(strings);
  }
  
  public void setAttributes(String...strings) {
    code = strings[0];
    subject = strings[1];
    userCode = strings[2];
    content = strings[3];
    src = strings[4];
    sended_at = strings[5];
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
}
