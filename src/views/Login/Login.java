package views.Login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*import ConnectionDatabase.ConnectionDatabase;
import MainScreen.MainScreen;
import UserObject.User;*/

public class Login extends JFrame implements ActionListener {

	public static final String PATHSTORE = "C:/Source/eclipse/workspace/DoAnFPT";
	public static final String TABLE_NGUOIDUNG = "NGUOIDUNG";
	public static final String[] THONGTINCANHAN = { "manguoidung", "hoten", "matkhau", "ngaysinh", "capbac", "chucvu",
			"trangthai", "quyenhan", "path"};
	private JButton btnLogin;
	private JButton btnCancel;
	private JTextField tfUsername;
	private JPasswordField pfPassword;

	private Connection mConnection;
	private ResultSet mResultSet;
	private PreparedStatement mPrepareStatement;

	public Login(String title) {
		super(title);
		initializeComponents();
		// Khởi tạo kết nối
		/*try {
			mConnection = ConnectionDatabase.getConnection();
		} catch (Exception e) {
			System.out.println("_Login_ Không thể kết nối databasse!");
		}*/
	}

	private void initializeComponents() {
		// tạo 1 panel chứa toàn bộ các item, cho layout NULL cho dễ chỉnh sửa
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		// Tạo title cho screen này
		JLabel titleLabel = new JLabel("Quản lý và ban hành văn bản");
		titleLabel.setBounds(60, 10, 300, 40);
		titleLabel.setFont(new Font("Tohama", Font.BOLD, 18));
		loginPanel.add(titleLabel);
		// Dòng UserName
		JLabel userNameLabel = new JLabel("Mã nhân viên:");
		userNameLabel.setBounds(50, 60, 80, 25);
		loginPanel.add(userNameLabel);
		tfUsername = new JTextField();
		tfUsername.requestFocus();
		
		tfUsername.setBounds(150, 60, 180, 30);
		loginPanel.add(tfUsername);
		// Dòng Password
		JLabel passWordLabel = new JLabel("Mật khẩu:");
		passWordLabel.setBounds(50, 100, 80, 25);
		loginPanel.add(passWordLabel);
		pfPassword = new JPasswordField();
		pfPassword.setBounds(150, 100, 180, 30);
		loginPanel.add(pfPassword);

		btnLogin = new JButton("Đăng nhập");
		btnLogin.setBounds(60, 160, 120, 30);
		loginPanel.add(btnLogin);
		btnCancel = new JButton("Hủy bỏ");
		btnCancel.setBounds(210, 160, 120, 30);

		loginPanel.add(btnCancel);

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.add(loginPanel);
		this.setSize(400, 250);
		this.setVisible(true);
		this.setLocation(470, 250);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnLogin) {
//			checkAccount(tfUsername.getText(), pfPassword.getText());
			System.out.println("Nhảy qua màn hình chính");
		} else {
			System.exit(0);
		}
	}

	/*public void checkAccount(String uc, String pw) {
		String username = uc;
		String password = pw;
		String usertype = "";
		// Nếu có nhập username và password
		if (username != null && password != null) {
			// Kiểm tra xem tài khoản có nhỏ hơn 6 kí tự và nhiều hơn 10 kí
			// tự hay k?
			// Kiểm tra xem mật khẩu có nhỏ hơn 30 kí tự và lớn hơn 6 kí tự
			// hay không?
			// dài quá thì tách ra hàm riêng.
			String sqlLogin = "SELECT * FROM " + TABLE_NGUOIDUNG + " WHERE " + THONGTINCANHAN[0] + " = ? " + "AND "
					+ THONGTINCANHAN[2] + " = ?;";
			try {
				mPrepareStatement = mConnection.prepareStatement(sqlLogin);
				mPrepareStatement.setString(1, username);
				mPrepareStatement.setString(2, password);
				mResultSet = mPrepareStatement.executeQuery();
				if (mResultSet.next()) {
					User user = new User();
					user.setEmploymentCode(mResultSet.getString(THONGTINCANHAN[0]));
					user.setUserName(mResultSet.getString(THONGTINCANHAN[1]));
					user.setPassword(mResultSet.getString(THONGTINCANHAN[2]));
					user.setUserType(mResultSet.getInt(THONGTINCANHAN[7]));
					user.setUserStatus(mResultSet.getInt(THONGTINCANHAN[6]));
					user.setBirth(mResultSet.getDate(THONGTINCANHAN[3]).toString());
					user.setRank(mResultSet.getString(THONGTINCANHAN[4]));
					user.setWork(mResultSet.getString(THONGTINCANHAN[5]));
					user.setPath(mResultSet.getString(THONGTINCANHAN[8]));
					// tạo 1 thư mực lưu trũ
					createStore("Info " + user.getUserName());
					// mở ra screen mới
					new MainScreen(this, user);
					// đóng màn hình login lại
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Username or Password is incorrect!");
				}

			} catch (Exception e1) {
				System.out.println("_Login_Error_" + e1);
				System.out.println("Truy vấn không thành công. Hệ thống dữ liệu bị lỗi!");
			}
		}
	}*/

	public void createStore(String folderName) {
		// TODO Auto-generated method stub
		File dataDirectory = new File(PATHSTORE + "/" + folderName);
		if (!dataDirectory.exists()) {
			dataDirectory.mkdir();
		}
	}
}
