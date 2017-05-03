package views.outgoing_files;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JPanel;

import models.documents.Document;
import models.documents.DocumentRecord;

public class OutgoingDocumentManagementView extends JPanel {
  private NewDocumentView newDocumentView;
  private OutgoingDocumentsView outgoingDocumentsView;

  public OutgoingDocumentManagementView() {
    super();
    outgoingDocumentsView = new OutgoingDocumentsView();
    outgoingDocumentsView.getBtnNew().addActionListener(newAction());
    outgoingDocumentsView.getTbDocuments().addMouseListener(tableListener());
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

  private MouseListener tableListener() {
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
        DocumentsTable tb = outgoingDocumentsView.getTbDocuments();
        if (tb.getSelectedColumn() == tb.getHeaders().size() - 1) {
          tb.removeDocument(tb.getSelectedRow());
        }
      }
    };
  }

  private boolean destroyDocument(DocumentRecord document) {
    HashMap<String, String> record = new HashMap<>();
    record.put("code", document.getCode());
    try {
      new Document().destroy(record);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
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
