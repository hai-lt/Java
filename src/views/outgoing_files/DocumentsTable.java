package views.outgoing_files;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.documents.DocumentRecord;
import models.users.UserRecord;

public class DocumentsTable extends JTable {
  public static final String[] TITLES = { "Mã văn bản", "Chủ đề", "Người nhận", "Ngày gửi", "" };

  private ArrayList<DocumentRecord> documents;
  private Vector<String> headers;

  public DocumentsTable(ArrayList<DocumentRecord> documents) {
    super();
    this.documents = documents;
    setHeaders();
    DefaultTableModel model = new DefaultTableModel(getContent(), getHeaders());
    setModel(model);
  }

  public void addRow(DocumentRecord document) {
  }

  public Vector<String> getHeaders() {
    return headers;
  }

  public void setHeaders(String[] headers) {
    this.headers = new Vector<>();
    for (String string : headers) {
      this.headers.add(string);
    }
  }

  public ArrayList<DocumentRecord> getDocuments() {
    return documents;
  }

  public void setDocuments(ArrayList<DocumentRecord> documents) {
    this.documents = documents;
  }

  public void setHeaders() {
    setHeaders(TITLES);
  }

  public void refreshData() {
    DefaultTableModel model = new DefaultTableModel(getContent(), getHeaders());
    setModel(model);
    model.fireTableDataChanged();
  }

  public Vector<Vector<String>> getContent() {
    Vector<Vector<String>> documentVectors = new Vector<>();
    for (DocumentRecord documentRecord : documents) {
      Vector<String> document = new Vector<>();
      document.add(documentRecord.getCode());
      document.add(documentRecord.getSubject());
      String receivers = "";
      for (UserRecord user : documentRecord.receivers()) {
        receivers += user.getFullName() + ", ";
      }
      if (!receivers.equals("")) {
        receivers = receivers.substring(0, receivers.length() - 2);
      }
      document.add(receivers);
      document.add(documentRecord.getSended_at());
      document.add("Xóa");
      documentVectors.add(document);
    }
    return documentVectors;
  }
}