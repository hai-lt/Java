package views.outgoing_files;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import models.users.User;
import models.users.UserRecord;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class NewDocumentView extends JPanel {
  private JPanel container;
  private JTextField txtSubject;
  private JTextField txtPathFile;
  private JComboBox cbSelectReceiver;
  private JTextArea txtListReceivers, txtContent;
  private JButton btnSave, btnChooseFile;

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
    cbSelectReceiver.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        txtListReceivers.setText(txtListReceivers.getText() + cbSelectReceiver.getSelectedItem() + ", ");
      }
    });
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
  }
}
