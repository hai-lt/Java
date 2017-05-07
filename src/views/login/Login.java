package views.login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hailt.models.ObjectRecord;
import models.users.User;
import models.users.UserRecord;
import system.RootSystem;
import views.AppResources;
import views.main.MainView;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

/**
 * 
 * @author tanhai 20170504
 *
 */
public class Login extends JFrame {

  private JPanel container;
  private JTextField txtUserCode;
  private JTextField txtPassword;
  private JCheckBox chckbxRememberMe;
  private JButton btnSubmit;
  private JLabel lbStatus;

  public static void main(String[] args) {
    MainView.turnOffBoldFont();
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Login frame = new Login();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public Login() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    setUi();
    setEvent();
    new Thread(new Runnable() {

      @Override
      public void run() {
        if (isLogined()) {
          showMainView();
        }
      }
    }).start();
  }

  private void setEvent() {
    btnSubmit.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        login();
      }
    });
  }

  private boolean isLogined() {
    String userCode = RootSystem.getInstance().getSystemConfigRecord().getRememberUser();
    if (userCode.equals("")) {
      return false;
    }
    try {
      RootSystem.getInstance().setCurrentUser(new UserRecord(new User().all("code = '" + userCode + "'").get(0)));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private void login() {
    if (loginSuccess()) {
      showMainView();
      return;
    }
    AppResources.notifyMessage(lbStatus, "Vui lòng kiểm tra lại mã nhân viên và mật khẩu!",
        AppResources.DURATION_STANDARD, AppResources.COLOR_WARNING);
  }

  private void showMainView() {
    MainView.createUi(new MainView());
    dispose();
  }

  private boolean loginSuccess() {
    String userCode = txtUserCode.getText();
    if (userCode.equals("")) {
      return false;
    }
    String password = txtPassword.getText();
    if (password.equals("")) {
      return false;
    }
    HashMap<String, String> record = new HashMap<>();
    record.put("code", userCode);
    record.put("password", password);
    try {
      ObjectRecord objectRecord = new User().findBy(record);
      UserRecord user = new UserRecord(objectRecord);
      RootSystem.getInstance().setCurrentUser(user);
      // update remember
      String remember_user;
      if (chckbxRememberMe.isSelected()) {
        remember_user = user.getCode();
      } else {
        remember_user = "";
      }
      HashMap<String, String> attributes = new HashMap<>();
      attributes.put("remember_user", remember_user);
      RootSystem.getInstance().getSystemConfigRecord().update(attributes);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private void setUi() {

    container = new JPanel();
    container.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(container);
    container.setLayout(null);

    JLabel lbUserCode = new JLabel("Mã số nhân viên");
    lbUserCode.setBounds(62, 54, 132, 15);
    container.add(lbUserCode);

    txtUserCode = new JTextField();
    txtUserCode.setBounds(196, 52, 171, 19);
    container.add(txtUserCode);
    txtUserCode.setColumns(10);

    JLabel lbPassword = new JLabel("Mật khẩu");
    lbPassword.setBounds(62, 98, 70, 15);
    container.add(lbPassword);

    txtPassword = new JTextField();
    txtPassword.setBounds(196, 98, 171, 19);
    container.add(txtPassword);
    txtPassword.setColumns(10);

    chckbxRememberMe = new JCheckBox("Ghi nhớ đăng nhập");
    chckbxRememberMe.setBounds(196, 159, 171, 23);
    container.add(chckbxRememberMe);

    btnSubmit = new JButton("Đăng nhập");
    btnSubmit.setBounds(62, 190, 305, 25);
    container.add(btnSubmit);

    lbStatus = new JLabel("");
    lbStatus.setBounds(62, 125, 305, 15);
    container.add(lbStatus);

  }
}
