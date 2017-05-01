package views.incoming_files;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import hailt.models.ObjectRecord;
import models.document_user.DocumentUser;
import models.document_user.DocumentUserRecord;
import models.documents.DocumentRecord;

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

      ArrayList<ObjectRecord> receivers = new DocumentUser().all("document_code = " + documentRecord.getCode());
      ArrayList<DocumentUserRecord> dus = DocumentUserRecord.convertFrom(receivers);
      String names = "";
      for (DocumentUserRecord documentUserRecord : dus) {
        names += documentUserRecord.receiver().getFullName() + ", ";
      }
      document.add(documentRecord.getSended_at());
      document.add("Xóa");
      documentVectors.add(document);
    }
    return documentVectors;
  }
}