package views.outgoing_files;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import models.documents.Document;
import models.documents.DocumentRecord;
import models.users.User;
import models.users.UserRecord;
import system.RootSystem;
import views.AppResources;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public abstract class NewDocumentView extends JPanel {
  private JPanel container;
  private JTextField txtSubject;
  private JTextField txtPathFile;
  private JComboBox cbSelectReceiver;
  private JTextArea txtListReceivers, txtContent;
  private JButton btnSave, btnChooseFile, btnBack;
  private JLabel lbStatus;
  private ArrayList<UserRecord> receivers;

  /**
   * Create the panel.
   */
  public NewDocumentView() {
    super(new BorderLayout());
    setPreferredSize(new Dimension(500, 400));
    setUi();
    setEvent();
  }

  public JButton getBtnSave() {
    return btnSave;
  }

  private void setEvent() {
    btnChooseFile.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
          txtPathFile.setText(fc.getSelectedFile().toString());
        }
      }
    });

    btnSave.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (createNewDocument()) {
          afterSaveAction();
        }
      }
    });

    btnBack.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        clearView();
        afterBackAction();
      }
    });

    cbSelectReceiver.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        addReceiverView((UserRecord) cbSelectReceiver.getSelectedItem());
      }
    });

  }

  private Vector<UserRecord> getAllUsers() {
    Vector<UserRecord> users = new Vector();
    for (UserRecord user : UserRecord.convertFrom(new User().all())) {
      users.add(user);
    }
    return users;
  }

  private void setUi() {
    container = new JPanel(null);
    container.setPreferredSize(new Dimension(500, 400));
    add(container, BorderLayout.NORTH);

    JLabel lblCreateDoc = new JLabel("TẠO MỚI VĂN BẢN");
    lblCreateDoc.setHorizontalAlignment(SwingConstants.CENTER);
    lblCreateDoc.setBounds(12, 25, 426, 15);
    container.add(lblCreateDoc);

    cbSelectReceiver = new JComboBox(getAllUsers());
    cbSelectReceiver.setBounds(247, 76, 194, 24);
    cbSelectReceiver.setAutoscrolls(true);
    container.add(cbSelectReceiver);

    JLabel lbSubject = new JLabel("Chủ đề");
    lbSubject.setBounds(22, 159, 70, 15);
    container.add(lbSubject);

    txtSubject = new JTextField();
    txtSubject.setBounds(120, 157, 321, 19);
    container.add(txtSubject);
    txtSubject.setColumns(10);

    JLabel lbContent = new JLabel("Nội dung");
    lbContent.setBounds(22, 186, 70, 15);
    container.add(lbContent);

    txtContent = new JTextArea(15, 30);
    txtContent.setBounds(120, 188, 321, 103);
    container.add(txtContent);

    txtPathFile = new JTextField();
    txtPathFile.setEditable(false);
    txtPathFile.setBounds(120, 303, 321, 19);
    container.add(txtPathFile);
    txtPathFile.setColumns(10);

    btnSave = new JButton("Lưu");
    btnSave.setBounds(355, 339, 83, 25);
    container.add(btnSave);

    btnBack = new JButton("Hủy");
    btnBack.setBounds(273, 339, 83, 25);
    container.add(btnBack);

    JLabel lblNguoiNhan = new JLabel("Chọn người nhận");
    lblNguoiNhan.setBounds(22, 81, 128, 15);
    container.add(lblNguoiNhan);

    JLabel lblDanhSachNguoi = new JLabel("Danh sách người nhận:");
    lblDanhSachNguoi.setBounds(22, 108, 172, 15);
    container.add(lblDanhSachNguoi);

    txtListReceivers = new JTextArea();
    txtListReceivers.setBounds(193, 112, 249, 38);
    txtListReceivers.setEditable(false);
    container.add(txtListReceivers);

    btnChooseFile = new JButton("Chọn file");
    btnChooseFile.setBounds(22, 300, 95, 25);
    container.add(btnChooseFile);

    lbStatus = new JLabel("Thông báo");
    lbStatus.setForeground(AppResources.COLOR_SUCCESS);
    lbStatus.setBounds(22, 373, 416, 15);
    container.add(lbStatus);
  }

  public boolean createNewDocument() {
    if (txtListReceivers.getText().equals("")) {
      AppResources.notifyMessage(lbStatus, "Chọn người nhận văn bản", AppResources.DURATION_STANDARD,
          AppResources.COLOR_WARNING);
      return false;
    }
    String subject = txtSubject.getText();
    if (subject.equals("")) {
      AppResources.notifyMessage(lbStatus, "Nhập chủ đề của văn bản", AppResources.DURATION_STANDARD,
          AppResources.COLOR_WARNING);
      return false;
    }
    String content = txtContent.getText();
    if (content.equals("")) {
      AppResources.notifyMessage(lbStatus, "Nhập nội dung của văn bản", AppResources.DURATION_STANDARD,
          AppResources.COLOR_WARNING);
      return false;
    }
    String file = txtPathFile.getText();
    if (file.equals("")) {
      AppResources.notifyMessage(lbStatus, "Chọn file đính kèm.", AppResources.DURATION_STANDARD,
          AppResources.COLOR_WARNING);
      return false;
    }
    HashMap<String, String> document = new HashMap<>();
    document.put("content", content);
    document.put("subject", subject);
    document.put("user_code", RootSystem.getInstance().getCurrentUser().getCode());
    document.put("src", file);
    try {
      Document doc = new Document();
      doc.create(document);
      DocumentRecord documentRecord = new DocumentRecord(doc.findBy(document));
      for (UserRecord user : receivers) {
        documentRecord.addReceiver(user);
      }
      AppResources.notifyMessage(lbStatus, "Tạo văn bản thành công.", AppResources.DURATION_STANDARD,
          AppResources.COLOR_SUCCESS);
      clearView();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      AppResources.notifyMessage(lbStatus, "Có lỗi xảy ra, không tạo được văn bản." + e.getMessage(),
          AppResources.DURATION_STANDARD, AppResources.COLOR_WARNING);
      return false;
    }
  }

  private void clearView() {
    txtContent.setText("");
    clearReceiversView();
    txtPathFile.setText("");
    txtSubject.setText("");
  }

  public JButton getBtnBack() {
    return btnBack;
  }

  private void addReceiverView(UserRecord user) {
    if (receivers == null) {
      receivers = new ArrayList<>();
    }
    if (receivers.contains(user)) {
      return;
    }
    receivers.add(user);
    String oldReceivers = txtListReceivers.getText();
    if (!oldReceivers.equals("")) {
      oldReceivers += ", ";
    }
    txtListReceivers.setText(oldReceivers + user.getFullName());
  }

  private void clearReceiversView() {
    receivers.clear();
    txtListReceivers.setText("");
  }

  protected abstract void afterBackAction();

  protected abstract void afterSaveAction();
}
