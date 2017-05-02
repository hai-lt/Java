package views.incoming_files;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.documents.Document;
import models.documents.DocumentRecord;

public class ListDocumentView extends JPanel {
  private JTextField txtDocument, txtSender;
  private JComboBox<String> slSubjects;

  public ListDocumentView() {
    super();
    txtDocument = new JTextField();
    txtSender = new JTextField();
    slSubjects = new JComboBox<>(Document.SUBJECTS);

    JPanel filterView = new JPanel();
    filterView.add(txtDocument);
    filterView.add(txtSender);
    filterView.add(slSubjects);
    add(filterView);

    add(new DocumentsTable(DocumentRecord.convertFrom(new Document().all())));
  }
}
