package views.incoming_files;

import javax.swing.JPanel;

public class IncommingDocumentManagementView extends JPanel {

  public IncommingDocumentManagementView() {
    super();
    add(new ListDocumentView());
  }
}
