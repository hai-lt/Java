package views.incoming_files;

import javax.swing.JPanel;

import models.documents.Document;
import models.documents.DocumentRecord;

public class IncommingDocumentManagementView extends JPanel {

  public IncommingDocumentManagementView() {
    super();
    add(new IncomingDocumentDetailView(new DocumentRecord(new Document().all().get(0))));
  }
}
