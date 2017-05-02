package views.incoming_files;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Incoming extends JPanel {
  private JTextField txtPathDocument;
  private JLabel lblFile, lbSubject, lbSender, lbReceiver;
  private JButton btnBack;
  private JTextArea txtContent;

  /**
   * Create the panel.
   */
  public Incoming() {
    setLayout(null);

    JLabel lbSenderTitle = new JLabel("Người gửi:");
    lbSenderTitle.setBounds(29, 39, 79, 15);
    add(lbSenderTitle);

    JLabel lbReceiversTitle = new JLabel("Ngời nhận:");
    lbReceiversTitle.setBounds(184, 39, 84, 15);
    add(lbReceiversTitle);

    JLabel lbSubjectTitle = new JLabel("Chủ đề:");
    lbSubjectTitle.setBounds(29, 66, 59, 15);
    add(lbSubjectTitle);

    JLabel lb = new JLabel("Nội dung");
    lb.setBounds(29, 103, 70, 15);
    add(lb);

    btnBack = new JButton("Trở về");
    btnBack.setBounds(347, 263, 91, 25);
    add(btnBack);

    txtContent = new JTextArea();
    txtContent.setText("Content");
    txtContent.setBounds(117, 103, 302, 108);
    add(txtContent);

    lblFile = new JLabel("File");
    lblFile.setBounds(83, 232, 33, 15);
    add(lblFile);
    lblFile.addMouseListener(openFile());

    txtPathDocument = new JTextField();
    txtPathDocument.setText("path/your file");
    txtPathDocument.setBounds(117, 230, 302, 19);
    add(txtPathDocument);
    txtPathDocument.setColumns(10);

    lbSubject = new JLabel("Răng cũng được");
    lbSubject.setBounds(117, 66, 302, 15);
    add(lbSubject);

    lbSender = new JLabel("Tấn Hải");
    lbSender.setBounds(111, 39, 91, 15);
    add(lbSender);

    lbReceiver = new JLabel("Tấn Hải");
    lbReceiver.setBounds(274, 39, 145, 15);
    add(lbReceiver);

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
}
