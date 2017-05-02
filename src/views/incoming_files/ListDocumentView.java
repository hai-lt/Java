package views.incoming_files;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import models.documents.Document;
import models.documents.DocumentRecord;

public class ListDocumentView extends JPanel {
  private JTextField txtDocument, txtSender;
  private JButton btnSearch;
  private DocumentsTable tbDocuments;

  public ListDocumentView() {
    super(new BorderLayout());
    txtDocument = new JTextField(10);
    txtSender = new JTextField(10);

    JPanel filterView = new JPanel();
    filterView.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
    filterView.add(new JLabel("Tên văn bản"));
    filterView.add(txtDocument);
    filterView.add(new JLabel("Tên người nhận"));
    filterView.add(txtSender);
    btnSearch = new JButton("Tìm");
    btnSearch.addActionListener(searchDocument());
    filterView.add(btnSearch);
    add(filterView, BorderLayout.NORTH);

    tbDocuments = new DocumentsTable(DocumentRecord.convertFrom(new Document().all()));
    JScrollPane scrollPane = new JScrollPane(tbDocuments);
    add(scrollPane, BorderLayout.CENTER);
  }

  public DocumentsTable getTbDocuments() {
    return tbDocuments;
  }

  public void setTbDocuments(DocumentsTable tbDocuments) {
    this.tbDocuments = tbDocuments;
  }

  private ActionListener searchDocument() {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String subject = txtDocument.getText().trim();
        String userCode = txtSender.getText().trim();
        String conditions = "";
        if (!userCode.equals("")) {
          conditions += " INNER JOIN user ON document.user_code = user.code" + " WHERE user.full_name LIKE '%"
              + userCode + "%'";
        }
        if (!subject.equals("")) {
          if (!conditions.equals("")) {
            conditions += " AND ";
          }
          conditions += "subject LIKE '%" + subject + "%'";
        }
        if (!conditions.equals("")) {
          tbDocuments.setDocuments(
              DocumentRecord.convertFrom(new Document().query("select distinct * from document " + conditions)));
        } else {
          tbDocuments.setDocuments(DocumentRecord.convertFrom(new Document().all()));
        }
        tbDocuments.refreshData();
      }
    };
  }
}
