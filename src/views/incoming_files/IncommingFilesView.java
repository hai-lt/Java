package views.incoming_files;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class IncommingFilesView extends JPanel {

  public IncommingFilesView() {
    super();
    add(new ListDocumentView());
  }
}
