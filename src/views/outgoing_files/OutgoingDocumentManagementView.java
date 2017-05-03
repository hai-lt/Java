package views.outgoing_files;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class OutgoingDocumentManagementView extends JPanel {
  private NewDocumentView newDocumentView;
  private OutgoingDocumentsView outgoingDocumentsView;

  public OutgoingDocumentManagementView() {
    super();
    outgoingDocumentsView = new OutgoingDocumentsView();
    outgoingDocumentsView.getBtnNew().addActionListener(newAction());
    add(outgoingDocumentsView);
  }

  private void showOutgoingDocumentsView() {
    outgoingDocumentsView.setVisible(true);
    newDocumentView.setVisible(false);
  }

  private void showNewDocumentView() {
    outgoingDocumentsView.setVisible(false);
    if (newDocumentView == null) {
      newDocumentView = new NewDocumentView() {

        @Override
        protected void afterSaveAction() {
          showOutgoingDocumentsView();
        }

        @Override
        protected void afterBackAction() {
          showOutgoingDocumentsView();
        }
      };
      add(newDocumentView, BorderLayout.NORTH);
    }
    newDocumentView.setVisible(true);
  }

  private ActionListener newAction() {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        showNewDocumentView();
      }
    };
  }
}
