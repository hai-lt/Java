package views.incoming_files;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.documents.DocumentRecord;
import models.users.User;
import models.users.UserRecord;
import views.AppResources;
import views.base.ImageHandle;

public class IncomingDocumentDetailView extends JPanel {
  private DocumentRecord document;

  public IncomingDocumentDetailView(DocumentRecord document) {
    super();
    this.document = document;
    // show user info
    JPanel userView = new JPanel();
    UserRecord sender = new UserRecord(new User().all("code = " + document.getCode()).get(0));
    userView.add(new JLabel("Người gửi: " + sender.getFullName()));
    userView.add(new JLabel("Người nhận: " + "revievertest"));
    add(userView);

    // add subject view
    JPanel subjectView = new JPanel();
    subjectView.add(new JLabel("Chủ đề: "));
    subjectView.add(new JLabel("Hoc bong"));
    add(subjectView);

    // add content view
    JPanel contentView = new JPanel();
    contentView.add(new JLabel("Nội dung: "));
    contentView.add(new JTextArea(document.getContent(), 5, 30));
    add(contentView);

    // add attachment view
    JPanel attachmentView = new JPanel();
    attachmentView.add(new JLabel(new ImageHandle(AppResources.FILE_ATTACHMENT_ICON).getIcon()));
    JTextField pathView = new JTextField(document.getSrc());
    pathView.setEnabled(false);
    attachmentView.add(pathView);
    add(attachmentView);
  }
}
