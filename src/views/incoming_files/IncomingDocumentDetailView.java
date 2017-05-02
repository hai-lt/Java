package views.incoming_files;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import models.documents.DocumentRecord;
import models.users.UserRecord;

public class IncomingDocumentDetailView extends JPanel {
  private JTextField txtPathDocument;
  private JLabel lblFile, lbSubject, lbSender, lbReceiver;
  private JButton btnBack;
  private JTextArea txtContent;

  private JPanel container;

  private DocumentRecord document;

  /**
   * Create the panel.
   */
  public IncomingDocumentDetailView(DocumentRecord document) {
    super(new BorderLayout());
    this.document = document;
    setUI();
    setData();
    add(container, BorderLayout.NORTH);
  }

  public MouseListener openFile() {
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
        System.out.println("Back");
      }
    };
  }

  public void setUI() {
    container = new JPanel(null);
    container.setPreferredSize(new Dimension(500, 300));

    JLabel lbSenderTitle = new JLabel("Người gửi:");
    lbSenderTitle.setBounds(29, 39, 79, 15);
    container.add(lbSenderTitle);

    JLabel lbReceiversTitle = new JLabel("Ngời nhận:");
    lbReceiversTitle.setBounds(184, 39, 84, 15);
    container.add(lbReceiversTitle);

    JLabel lbSubjectTitle = new JLabel("Chủ đề:");
    lbSubjectTitle.setBounds(29, 66, 59, 15);
    container.add(lbSubjectTitle);

    JLabel lb = new JLabel("Nội dung");
    lb.setBounds(29, 103, 70, 15);
    container.add(lb);

    btnBack = new JButton("Trở về");
    btnBack.setBounds(347, 263, 91, 25);
    container.add(btnBack);

    txtContent = new JTextArea();
    txtContent.setText("Content");
    txtContent.setBounds(117, 103, 302, 108);
    container.add(txtContent);

    lblFile = new JLabel("File");
    lblFile.setBounds(83, 232, 33, 15);
    container.add(lblFile);
    lblFile.addMouseListener(openFile());

    txtPathDocument = new JTextField();
    txtPathDocument.setText("path/your file");
    txtPathDocument.setBounds(117, 230, 302, 19);
    container.add(txtPathDocument);
    txtPathDocument.setColumns(10);

    lbSubject = new JLabel("Răng cũng được");
    lbSubject.setBounds(117, 66, 302, 15);
    container.add(lbSubject);

    lbSender = new JLabel("Tấn Hải");
    lbSender.setBounds(102, 39, 91, 15);
    container.add(lbSender);

    lbReceiver = new JLabel("Tấn Hải");
    lbReceiver.setBounds(274, 39, 145, 15);
    container.add(lbReceiver);

  }

  public void setData() {
    lbSender.setText(document.sender().getFullName());
    String receivers = "";
    for (UserRecord user : document.receivers()) {
      receivers += user.getFullName() + ", ";
    }
    receivers = receivers.substring(0, receivers.length() - 2);
    lbReceiver.setText(receivers);
    lbSubject.setText(document.getSubject());
    txtContent.setText(document.getContent());
    txtPathDocument.setText(document.getSrc());
    txtPathDocument.setEnabled(false);
  }
}
