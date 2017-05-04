package views.login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
  }

  private void setEvent() {
    btnSubmit.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        login();
      }
    });
  }

  private void login() {
    if (loginSuccess()) {
      showMainView();
      return;
    }
  }

  private void showMainView() {
    MainView.createUi(new MainView());
    dispose();
  }

  private boolean loginSuccess() {
    return true;
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
