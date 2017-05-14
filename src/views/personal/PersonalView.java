package views.personal;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import system.RootSystem;
import views.login.Login;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class PersonalView extends JPanel {
  private JTextField txtName;
  private JTextField txtCode;
  private JTextField text;
  private JTextField txtBirthdate;
  private JTextField txtLevel;
  private JPasswordField textOldPassword;
  private JPasswordField txtNewpassword;
  private JPasswordField txtPasswordconfim;
  private JButton btnLogout, btnSubmit, btnChangepassword;
  private JLabel lbAvatar;
  private JFileChooser chooseImage;
  private JButton btnChangeAvatar;

  /**
   * Create the panel.
   */
  public PersonalView() {
    setLayout(null);
    setUi();
    setEvent();
  }

  private void setUi() {
    JLabel lbInfoTitle = new JLabel("THÔNG TIN CÁ NHÂN");
    lbInfoTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lbInfoTitle.setBounds(12, 93, 251, 15);
    add(lbInfoTitle);

    txtName = new JTextField();
    txtName.setBounds(117, 134, 163, 19);
    txtName.setEditable(false);
    add(txtName);
    txtName.setColumns(10);

    JLabel lblFullname = new JLabel("Họ tên");
    lblFullname.setBounds(12, 138, 70, 15);
    add(lblFullname);

    JLabel lblCode = new JLabel("Mã người dùng");
    lblCode.setBounds(12, 165, 104, 15);
    add(lblCode);

    txtCode = new JTextField();
    txtCode.setBounds(117, 161, 163, 19);
    txtCode.setEditable(false);
    add(txtCode);
    txtCode.setColumns(10);

    JLabel lbposition = new JLabel("Chức vụ");
    lbposition.setBounds(12, 189, 70, 15);
    add(lbposition);

    text = new JTextField();
    text.setBounds(117, 185, 163, 19);
    text.setEditable(false);
    add(text);
    text.setColumns(10);

    JLabel lblBirthdate = new JLabel("Ngày sinh");
    lblBirthdate.setBounds(12, 216, 70, 15);
    add(lblBirthdate);

    txtBirthdate = new JTextField();
    txtBirthdate.setBounds(117, 212, 163, 19);
    txtBirthdate.setEditable(false);
    add(txtBirthdate);
    txtBirthdate.setColumns(10);

    JLabel lblLevel = new JLabel("Cấp bậc");
    lblLevel.setBounds(12, 243, 70, 15);
    add(lblLevel);

    txtLevel = new JTextField();
    txtLevel.setBounds(117, 239, 163, 19);
    txtLevel.setEditable(false);
    add(txtLevel);
    txtLevel.setColumns(10);

    btnSubmit = new JButton("Cập nhật thông tin cá nhân");
    btnSubmit.setBounds(12, 274, 268, 25);
    add(btnSubmit);
    
    JLabel lblOldpassword = new JLabel("Mật khẩu cũ");
    lblOldpassword.setBounds(303, 245, 119, 15);
    add(lblOldpassword);

    textOldPassword = new JPasswordField();
    textOldPassword.setBounds(440, 243, 152, 19);
    textOldPassword.setEditable(false);
    add(textOldPassword);
    textOldPassword.setColumns(10);

    JLabel lblNewpassword = new JLabel("Mật khẩu mới");
    lblNewpassword.setBounds(300, 276, 122, 15);
    add(lblNewpassword);

    txtNewpassword = new JPasswordField();
    txtNewpassword.setBounds(440, 276, 152, 19);
    txtNewpassword.setEditable(false);
    add(txtNewpassword);
    txtNewpassword.setColumns(10);

    JLabel lblPasswordconfirm = new JLabel("Xác nhận mật khẩu");
    lblPasswordconfirm.setBounds(303, 306, 135, 15);
    add(lblPasswordconfirm);

    txtPasswordconfim = new JPasswordField();
    txtPasswordconfim.setBounds(440, 303, 152, 19);
    txtPasswordconfim.setEditable(false);
    add(txtPasswordconfim);
    txtPasswordconfim.setColumns(10);

    btnChangepassword = new JButton("Cập nhật mật khẩu");
    btnChangepassword.setBounds(303, 339, 289, 25);
    add(btnChangepassword);

    btnLogout = new JButton("Đăng xuất");
    btnLogout.setBounds(481, 12, 117, 25);
    add(btnLogout);

    // Tạo button thay đổi ảnh đại diện SIZE 32x32.
    btnChangeAvatar = new JButton();
    btnChangeAvatar.setBounds(521, 161, 32, 32);
    btnChangeAvatar.setIcon(new ImageIcon(getClass().getResource("/views/images/camera32.png")));
    btnChangeAvatar.setVisible(false);
    add(btnChangeAvatar);

    // Tạo image avatar size 150x150
    lbAvatar = new JLabel();
    lbAvatar.setBounds(405, 45, 150, 150);
    /*
     * if (user.getPath() != null) { showAvatar(lbAvatar, user.getPath()); }
     * else {
     */
    // avatar mặc định
    lbAvatar.setIcon(new ImageIcon(getClass().getResource("/views/images/wufu150.png")));
    // }
    Border avaBorder = BorderFactory.createLineBorder(Color.RED, 2);
    lbAvatar.setBorder(avaBorder);
    add(lbAvatar);

    // khởi tạo filechooser
    chooseImage = new JFileChooser();
  }

  private void setEvent() {
    btnSubmit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updatePersonalInfo();
      }
    });

    btnLogout.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("444444444444444");
        logout();
      }
    });

    btnChangepassword.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        updatePassword();
      }
    });

    btnChangeAvatar.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        int select = chooseImage.showOpenDialog(PersonalView.this);
        if (select == JFileChooser.APPROVE_OPTION) {
          String path = chooseImage.getSelectedFile().getPath();
          if (checkImage(path)) {
            /*
             * copyPathAndStoreImage(lbAvatar, path, Login.PATHSTORE + "/" +
             * "Info " + user.getUserName() + "/" + user.getUserName() +
             * ".png"); user.setPath(Login.PATHSTORE + "/" + "Info " +
             * user.getUserName() + "/" + user.getUserName() + ".png");
             */
          } else {
            JOptionPane.showMessageDialog(null, "File không hợp lệ, vui lòng thực hiện lại!");
          }
        }
      }
    });
  }

  private boolean checkImage(String path) {
    int size = path.length();
    return (path.substring(size - 4, size).equals(".jpg") || path.substring(size - 4, size).equals(".png"));
  }
  
  public boolean checkValidate(JTextField textField, int minLength, int maxLength){
    boolean check = false;
    String text = textField.getText();
    char [] checkArr = text.toCharArray();
    if(text.length() < minLength || text.length() > maxLength){
      JOptionPane.showMessageDialog(null, "Do dai chuoi khong hop le!");
      check = false;
    }else{
      for(int i = 0; i < checkArr.length; i++){
        // !, @, #, $, %, ^,&, *, (, ), -, +, _, =, \, |, ", ', :, ;, 
        if(i==0){
          if(checkArr[i] >= 48 && checkArr[i] <= 57){
            System.out.println("Ki tu dau tien khong duoc la ki tu so");
            check = false;
          }
        }else{
          if((checkArr[i] >= 65 && checkArr[i] <=97)||(checkArr[i] >= 45 && checkArr[i] <=57)){
            System.out.println("OK_ hop le!");
            check = true;
          }else{
            System.out.println("Khong hop le!");
            check = false;
          }
        }
      }
    }
    return check;
  }
  
  private void updatePersonalInfo() {
    if (!txtName.isEditable()) {
      setEditState("Thay đổi", true);
    } else { // update infomation
      JOptionPane.showMessageDialog(null, "Vao method update information");
      /*
       * if (updateInformation() != 0) { setEditState("Cập nhật thông tin",
       * false); } else { JOptionPane.showMessageDialog(null,
       * "Cập nhật không hợp lệ, vui lòng thực hiện lại!"); }
       */
    }

  }

  public void logout() {
    System.out.println("44444444444");
    RootSystem.getInstance().setCurrentUser(null);
  }

  private void updatePassword() {
    if (!textOldPassword.isEditable()) {
      setEditPasswordState("Đổi mật khẩu", true);
    } else {
      JOptionPane.showMessageDialog(null, "Vao method update password");
      /*
       * if (updatePassword() != 0) { setEditPasswordState("Cập nhật mật khẩu",
       * false); } else { JOptionPane.showMessageDialog(null,
       * "Cập nhật thất bại"); }
       */
    }

  }

  private void setEditState(String title, boolean state) {
    btnSubmit.setText(title);
    txtName.setEditable(state);
    // dateChooser.setEnabled(state);
    txtLevel.setEditable(state);
    text.setEditable(state);
    btnChangeAvatar.setVisible(state);
  }

  private void setEditPasswordState(String title, boolean state) {
    btnChangepassword.setText(title);
    textOldPassword.setEditable(state);
    txtNewpassword.setEditable(state);
    txtPasswordconfim.setEditable(state);
  }

}
