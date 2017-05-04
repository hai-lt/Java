package views.incoming_files;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import models.documents.Document;
import models.documents.DocumentRecord;
import system.RootSystem;

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
    filterView.add(new JLabel("Chủ đề"));
    filterView.add(txtDocument);
    filterView.add(new JLabel("Tên người gửi"));
    filterView.add(txtSender);
    btnSearch = new JButton("Tìm");
    btnSearch.addActionListener(searchDocument());
    filterView.add(btnSearch);
    add(filterView, BorderLayout.NORTH);

    tbDocuments = new DocumentsTable(RootSystem.getInstance().getCurrentUser().receiveDocuments());
    JScrollPane scrollPane = new JScrollPane(tbDocuments);
    add(scrollPane, BorderLayout.CENTER);
  }

  public DocumentsTable getTbDocuments() {
    return tbDocuments;
  }

  private ArrayList<DocumentRecord> getDocuments(String condition) {
    return RootSystem.getInstance().getCurrentUser().receiveDocuments(condition);
  }

  public void setTbDocuments(DocumentsTable tbDocuments) {
    this.tbDocuments = tbDocuments;
  }

  private ActionListener searchDocument() {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String subject = txtDocument.getText().trim();
        String username = txtSender.getText().trim();
        String condition = "";
        if (!username.equals("")) {
          condition += " user.full_name LIKE '%" + username + "%'";
        }
        if (!subject.equals("")) {
          if (!condition.equals("")) {
            condition += " AND ";
          }
          condition += " subject LIKE '%" + subject + "%'";
        }
        tbDocuments.setDocuments(RootSystem.getInstance().getCurrentUser().receiveDocuments(condition));
        tbDocuments.refreshData();
      }
    };
  }
}
