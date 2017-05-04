package views.personal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class PersonalView extends JPanel implements ActionListener{
  public PersonalView() {
    super();
    add(new JLabel("Cá Nhân"));
  }
  
  private JLabel lbAvatar;
	private JButton btnUdapteUserInfo, btnUpdatePass;
	private JButton btnChangeAvatar;
	private JFileChooser chooseImage;
	private JTextField tfUserCode, tfUserName, tfUserRank, tfUserWork;
	private JPasswordField pfOldPass, pfNewPass, pfVerifyPw;
	private JDateChooser dateChooser;
	private Connection mConnection;
	private PreparedStatement mPrepareStatement;
	private User user;
	
	public UserTab(MainScreen main, User user){
		this.user = user;
		try {
			mConnection = ConnectionDatabase.getConnection();
		} catch (Exception e) {
			System.out.println("_MainScreen_ Kết nối thất bại.");
		}
	}
	
	public void createUserInformationUI(JPanel tabPanel) {
		// tạo UI cho quản lý thông tin cá nhân.
		JLabel lbUserInfo = new JLabel("THÔNG TIN CÁ NHÂN");
		lbUserInfo.setBounds(50, 30, 200, 30);
		tabPanel.add(lbUserInfo);
		// dòng chứa họ tên
		tfUserName = new JTextField();
		createRow(tabPanel, new JLabel("Họ tên:"), 30, 70, 100, 30, tfUserName, 150, 70, 150, 30,
				Login.THONGTINCANHAN[1]);

		// dòng chứa mã người dùng
		tfUserCode = new JTextField();
		createRow(tabPanel, new JLabel("Mã người dùng:"), 30, 110, 100, 30, tfUserCode, 150, 110, 150, 30,
				Login.THONGTINCANHAN[0]);

		// dòng chứa ngày sinh
		JLabel lbBirth = new JLabel("Ngày sinh:");
		lbBirth.setBounds(30, 150, 100, 30);
		tabPanel.add(lbBirth);
		// tạo 1 cái datechooser
		dateChooser = new JDateChooser();
		dateChooser.setBounds(150, 150, 150, 30);
		// String d = "2017-05-14";
		try {
			dateChooser.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirth()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateChooser.setEnabled(false);
		// dateChooser.setDate(arg0);
		tabPanel.add(dateChooser);

		// dòng chứa chức vụ
		tfUserWork = new JTextField();
		createRow(tabPanel, new JLabel("Chức vụ:"), 30, 190, 100, 30, tfUserWork, 150, 190, 150, 30,
				Login.THONGTINCANHAN[5]);

		// dòng chứa cấp bậc
		tfUserRank = new JTextField();
		createRow(tabPanel, new JLabel("Cấp bậc:"), 30, 230, 80, 30, tfUserRank, 150, 230, 150, 30,
				Login.THONGTINCANHAN[4]);
		// tạo button cập nhật thông tin cá nhân
		btnUdapteUserInfo = createButton("Cập nhật thông tin", 30, 280, 270, 30);
		tabPanel.add(btnUdapteUserInfo);
		btnUdapteUserInfo.addActionListener(this);

		// Tạo button thay đổi ảnh đại diện SIZE 32x32.
		btnChangeAvatar = new JButton();
		createAvatarButton(tabPanel, btnChangeAvatar, 521, 141, 32, 32, "/Image/camera32.png");
		btnChangeAvatar.addActionListener(UserTab.this);

		// Tạo image avatar size 150x150
		lbAvatar = new JLabel();
		lbAvatar.setBounds(405, 25, 150, 150);
		if (user.getPath() != null) {
			showAvatar(lbAvatar, user.getPath());
		} else {
			// avatar mặc định
			lbAvatar.setIcon(new ImageIcon(getClass().getResource("/Image/wufu150.png")));
		}
		// showAvatar(avatarLabel, PATH[0]);
		Border avaBorder = BorderFactory.createLineBorder(Color.RED, 2);
		lbAvatar.setBorder(avaBorder);
		tabPanel.add(lbAvatar);

		// dòng mật khẩu cũ
		pfOldPass = new JPasswordField();
		createRow(tabPanel, new JLabel("Mật khẩu cũ:"), 320, 190, 100, 30, pfOldPass, 405, 190, 150, 30);
		// dòng mật khẩu mới
		pfNewPass = new JPasswordField();
		createRow(tabPanel, new JLabel("Mật khẩu mới:"), 320, 230, 100, 30, pfNewPass, 405, 230, 150, 30);
		// dòng xác nhận mật khẩu
		pfVerifyPw = new JPasswordField();
		createRow(tabPanel, new JLabel("Xác nhận lại:"), 320, 270, 100, 30, pfVerifyPw, 405, 270, 150, 30);

		// tạo button cập nhật mật khẩu
		btnUpdatePass = createButton("Cập nhật mật khẩu", 320, 320, 235, 30);
		tabPanel.add(btnUpdatePass);
		btnUpdatePass.addActionListener(this);

		// khởi tạo filechooser
		chooseImage = new JFileChooser();
	}
	
	private JButton createButton(String title, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		JButton button = new JButton(title);
		button.setBounds(x, y, width, height);
		button.setBackground(Color.blue);
		button.setForeground(Color.WHITE);
		return button;
	}
	
	private void createAvatarButton(JPanel panel, JButton buttonChange, int x, int y, int width, int height,
			String path) {
		// 491, 151, 32, 32
		buttonChange.setBounds(x, y, width, height);
		buttonChange.setIcon(new ImageIcon(getClass().getResource(path)));
		buttonChange.setVisible(false);
		panel.add(buttonChange);
	}

	private void createRow(JPanel panel, JLabel label, int x1, int y1, int width1, int height1,
			JPasswordField passwordfield, int x2, int y2, int width2, int height2) {
		// TODO Auto-generated method stub
		label.setBounds(x1, y1, width1, height1);
		panel.add(label);
		passwordfield.setBounds(x2, y2, width2, height2);
		passwordfield.setEditable(false);
		panel.add(passwordfield);
	}

	private void createRow(JPanel panel, JLabel label, int x1, int y1, int width1, int height1, JTextField textfield,
			int x2, int y2, int width2, int height2, String info) {
		// TODO Auto-generated method stub
		label.setBounds(x1, y1, width1, height1);
		panel.add(label);
		textfield.setBounds(x2, y2, width2, height2);
		textfield.setEditable(false);
		switch (info) {
		case "hoten":
			textfield.setText(user.getUserName());
			break;
		case "manguoidung":
			textfield.setText(user.getEmploymentCode());
			break;
		case "chucvu":
			textfield.setText(user.getWork());
			break;
		default: // "capbac"
			textfield.setText(user.getRank());
			break;
		}
		panel.add(textfield);
	}

	public void showAvatar(JLabel label, String path) {
		try {
			BufferedImage img = ImageIO.read(new File(path));
			ImageIcon icon = new ImageIcon(img);
			label.setIcon(icon);
		} catch (IOException e) {
			System.out.println("_MainScreen_Error_" + e + " line 273");
		}
	}

	private int updatePassword() {
		int key = 0;
		if (pfOldPass.getText().trim().equals(user.getPassword())) {
			if (pfNewPass.getText().trim().equals(pfVerifyPw.getText().trim())) {
				String sqlUpdatePassword = "UPDATE " + Login.TABLE_NGUOIDUNG + " SET " + Login.THONGTINCANHAN[2]
						+ " = ? " + " WHERE " + Login.THONGTINCANHAN[0] + " = ?;";
				try {
					mPrepareStatement = mConnection.prepareStatement(sqlUpdatePassword);
					mPrepareStatement.setString(1, pfNewPass.getText());
					mPrepareStatement.setString(2, user.getEmploymentCode());
					key = mPrepareStatement.executeUpdate();
				} catch (SQLException e) {
					System.out.println("_MainScreen_Error_" + e + " :Method updatePassword.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không chính xác!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng!");
		}
		return key;
	}

	private String convertDateToString(JDateChooser dc) {
		return new SimpleDateFormat("yyyy-MM-dd").format(dc.getDate());
	}

	private int updateInformation() {
		String sqlUpdateInformation = "UPDATE " + Login.TABLE_NGUOIDUNG + " SET " + Login.THONGTINCANHAN[1] + " = ?, "
				+ Login.THONGTINCANHAN[3] + " = ?, " + Login.THONGTINCANHAN[4] + " = ?, " + Login.THONGTINCANHAN[5]
				+ " = ?, " + Login.THONGTINCANHAN[8] + " = ? " + " WHERE " + Login.THONGTINCANHAN[0] + " = ?;";
		int key = 0;
		try {
			mPrepareStatement = mConnection.prepareStatement(sqlUpdateInformation);
			mPrepareStatement.setString(1, tfUserName.getText().trim());
			java.sql.Date sqlDate = new java.sql.Date(0000 - 00 - 00);
			mPrepareStatement.setDate(2, sqlDate.valueOf(convertDateToString(dateChooser)));
			mPrepareStatement.setString(3, tfUserRank.getText().trim());
			mPrepareStatement.setString(4, tfUserWork.getText().trim());
			mPrepareStatement.setString(5, user.getPath());
			mPrepareStatement.setString(6, user.getEmploymentCode());
			key = mPrepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("_MainScreen_Exception_" + e + " :Method updateInformation.");
		}
		return key;
	}

	private void copyPathAndStoreImage(JLabel label, String srcPath, String desPath) {
		try {
			InputStream inStream = new FileInputStream(new File(srcPath));
			OutputStream outStream = new FileOutputStream(new File(desPath));
			byte[] bufferImage = new byte[1024];
			int length;
			while ((length = inStream.read(bufferImage)) > 0) {
				outStream.write(bufferImage, 0, length);
			}
			showAvatar(label, desPath);
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Không thể đổi ảnh đại diện");
		}
	}

	private boolean checkImage(String path) {
		int size = path.length();
		return (path.substring(size - 4, size).equals(".jpg") || path.substring(size - 4, size).equals(".png"));
	}

	private void setEditState(String title, boolean state) {
		btnUdapteUserInfo.setText(title);
		tfUserName.setEditable(state);
		dateChooser.setEnabled(state);
		tfUserWork.setEditable(state);
		tfUserRank.setEditable(state);
		btnChangeAvatar.setVisible(state);
	}

	private void setEditPasswordState(String title, boolean state) {
		btnUpdatePass.setText(title);
		pfOldPass.setEditable(state);
		pfNewPass.setEditable(state);
		pfVerifyPw.setEditable(state);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnUdapteUserInfo) {
			if (!tfUserName.isEditable()) {
				setEditState("Thay đổi", true);
			} else { // update infomation
				if (updateInformation() != 0) {
					setEditState("Cập nhật thông tin", false);
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật không hợp lệ, vui lòng thực hiện lại!");
				}
			}
		} else if (e.getSource() == btnChangeAvatar) {
			// tạo filechooser
			int select = chooseImage.showOpenDialog(PersonalView.this);
			if (select == JFileChooser.APPROVE_OPTION) {
				String path = chooseImage.getSelectedFile().getPath();
				if (checkImage(path)) {
					copyPathAndStoreImage(lbAvatar, path,
							Login.PATHSTORE + "/" + "Info " + user.getUserName() + "/" + user.getUserName() + ".png");
					user.setPath(
							Login.PATHSTORE + "/" + "Info " + user.getUserName() + "/" + user.getUserName() + ".png");
				} else {
					JOptionPane.showMessageDialog(null, "File không hợp lệ, vui lòng thực hiện lại!");
				}
			}
		} else if (e.getSource() == btnUpdatePass) {
			if (!pfOldPass.isEditable()) {
				setEditPasswordState("Đổi mật khẩu", true);
			} else {
				if (updatePassword() != 0) {
					setEditPasswordState("Cập nhật mật khẩu", false);
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
				}
			}
		}
	}
  
}
