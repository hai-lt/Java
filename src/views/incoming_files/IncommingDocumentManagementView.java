package views.incoming_files;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import models.documents.DocumentRecord;

public class IncommingDocumentManagementView extends JPanel {
  private ListDocumentView documentsView;
  private IncomingDocumentDetailView documentDetailView;

  public IncommingDocumentManagementView() {
    super(new BorderLayout());
    documentsView = new ListDocumentView();
    add(documentsView, BorderLayout.NORTH);
    documentsView.getTbDocuments().addMouseListener(showDocumentDetail());
  }

  private MouseListener showDocumentDetail() {
    return new MouseListener() {

      @Override
      public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseClicked(MouseEvent e) {
        DocumentsTable tb = documentsView.getTbDocuments();
        int row = tb.getSelectedRow();
        int col = tb.getSelectedColumn();

        if (row == tb.getHeaders().size() - 1) {
          destroyDocument();
          return;
        }
        DocumentRecord document = tb.getDocuments().get(row);
        if (documentDetailView == null) {
          documentDetailView = new IncomingDocumentDetailView(document);
          add(documentDetailView, BorderLayout.NORTH);
        } else {
          documentDetailView.setDocument(document);
        }
        documentDetailView.setData();
        showDocumentDetailView();
      }
    };
  }

  private void destroyDocument() {

  }

  private void showDocumentDetailView() {
    documentDetailView.setVisible(true);
    documentsView.setVisible(false);
  }

  private void showDocumentsView() {
    documentDetailView.setVisible(false);
    documentsView.setVisible(true);
  }

}
