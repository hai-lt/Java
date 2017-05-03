package views.outgoing_files;

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

public class OutgoingDocumentsView extends JPanel {
  private JTextField txtDocument, txtReceivers;
  private JButton btnSearch, btnNew;
  private DocumentsTable tbDocuments;

  public OutgoingDocumentsView() {
    super(new BorderLayout());
    txtDocument = new JTextField(10);
    txtReceivers = new JTextField(10);

    JPanel filterView = new JPanel();
    filterView.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
    filterView.add(new JLabel("Chủ đề"));
    filterView.add(txtDocument);
    filterView.add(new JLabel("Tên người nhận"));
    filterView.add(txtReceivers);
    btnSearch = new JButton("Tìm");
    btnSearch.addActionListener(searchDocument());
    filterView.add(btnSearch);
    add(filterView, BorderLayout.NORTH);

    JPanel pnNew = new JPanel(new BorderLayout());
    btnNew = new JButton("Tạo mới");
    btnNew.setSize(20, 10);
    pnNew.add(btnNew, BorderLayout.WEST);
    add(pnNew, BorderLayout.CENTER);

    tbDocuments = new DocumentsTable(DocumentRecord.convertFrom(new Document().all()));
    JScrollPane scrollPane = new JScrollPane(tbDocuments);
    add(scrollPane, BorderLayout.SOUTH);
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
        String receiverName = txtReceivers.getText().trim();
        String conditions = "";
        if (!receiverName.equals("")) {
          conditions += " INNER JOIN document_user ON document_user.document_code = document.code"
              + " INNER JOIN user on user.code = document_user.user_code" + " WHERE user.full_name LIKE '%"
              + receiverName + "%'";
        }
        if (!subject.equals("")) {
          if (!conditions.equals("")) {
            conditions += " AND ";
          } else {
            conditions += " WHERE ";
          }
          conditions += " subject LIKE '%" + subject + "%'";
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

  public JButton getBtnNew() {
    return btnNew;
  }

  public void setBtnNew(JButton btnNew) {
    this.btnNew = btnNew;
  }

}
