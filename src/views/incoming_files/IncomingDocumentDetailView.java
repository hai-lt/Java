package views.incoming_files;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.documents.DocumentRecord;
import models.users.UserRecord;
import views.AppResources;
import views.base.ImageHandle;

public class IncomingDocumentDetailView extends JPanel {
  private DocumentRecord document;

  private JLabel sender, receiver, subject;
  private JTextArea content;
  private JTextField path;
  private JButton back, gotoSrc;
  private JPanel userInfo;

  public IncomingDocumentDetailView(DocumentRecord document) {
    super(new BorderLayout());
    initializeView();
    this.document = document;
    addData();
    setUI();
  }

  public void initializeView() {
    sender = new JLabel();
    receiver = new JLabel();
    userInfo = new JPanel(new BorderLayout(20, 10));
    userInfo.add(sender, BorderLayout.WEST);
    userInfo.add(receiver, BorderLayout.EAST);

    subject = new JLabel();
    content = new JTextArea(5, 15);
    path = new JTextField();
    back = new JButton("Back");
    back.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        backAction();
      }
    });
    gotoSrc = new JButton(new ImageHandle(AppResources.FILE_ATTACHMENT_ICON).getIcon());
    gotoSrc.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Open dialog");
      }
    });
  }

  public void addData() {
    sender.setText("Người gửi: " + document.sender().getFullName());
    String usersName = "";
    for (UserRecord user : document.receivers()) {
      usersName += user.getFullName() + ", ";
    }
    receiver.setText("Người nhận: " + usersName);
    subject.setText("Chủ đề: " + document.getSubject());
    content.setText(document.getContent());
    path.setText(document.getSrc());
  }

  public void setUI() {
    JPanel north = new JPanel(new BorderLayout());
    // show user info
    north.add(userInfo, BorderLayout.NORTH);

    // add subject view
    JPanel subjectView = new JPanel(new BorderLayout());
    subjectView.add(subject, BorderLayout.NORTH);
    north.add(subjectView, BorderLayout.CENTER);
    add(north, BorderLayout.NORTH);

    JPanel center = new JPanel(new BorderLayout());
    // add content view
    JPanel contentView = new JPanel(new BorderLayout());
    contentView.add(new JLabel("Nội dung: "), BorderLayout.WEST);
    contentView.add(content, BorderLayout.EAST);
    center.add(contentView, BorderLayout.NORTH);

    // add attachment view
    JPanel attachmentView = new JPanel();
    attachmentView.add(gotoSrc);
    path.setEnabled(false);
    attachmentView.add(path);
    center.add(attachmentView, BorderLayout.CENTER);
    add(center, BorderLayout.CENTER);
  }

  public DocumentRecord getDocument() {
    return document;
  }

  public void setDocument(DocumentRecord document) {
    this.document = document;
  }

  public void notifyDataHasChanged() {
    addData();
  }

  public void backAction() {
    setVisible(false);
  }
}
