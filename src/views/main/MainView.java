package views.main;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import views.incoming_files.IncommingDocumentManagementView;
import views.outgoing_files.OutgoingFilesView;
import views.personal.PersonalView;
import views.users_management.UsersManagementView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainView extends JPanel {
  private final static String INCOMING_FILE_LABEL = "Văn bản đến";
  private final static String USER_MANAGEMENT_LABEL = "Quản lý người dùng";
  private final static String OUTGOING_FILE_LABEL = "Văn bản đi";
  private final static String PERSONAL_LABEL = "Cá nhân";
  private JTabbedPane tabMenu;

  public MainView() {
    super(new GridLayout(1, 1));
    tabMenu = new JTabbedPane();
    tabMenu.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    add(tabMenu);
    addTab(new UsersManagementView(), USER_MANAGEMENT_LABEL, KeyEvent.VK_0);
    addTab(new IncommingDocumentManagementView(), INCOMING_FILE_LABEL, KeyEvent.VK_1);
    addTab(new OutgoingFilesView(), OUTGOING_FILE_LABEL, KeyEvent.VK_2);
    addTab(new PersonalView(), PERSONAL_LABEL, KeyEvent.VK_3);

  }

  public void addTab(Component component, String tabName, int mnemonic) {
    tabMenu.add(tabName, component);
    tabMenu.setMnemonicAt(tabMenu.getTabCount() - 1, mnemonic);
  }

  public String getTitle() {
    return "Main screen";
  }

  public static void turnOffBoldFont() {
    // Turn off metal's use of bold fonts
    UIManager.put("swing.boldMetal", Boolean.FALSE);
  }

  public static JFrame createUi(MainView ui) {
    // Create and set up the window.
    JFrame frame = new JFrame(ui.getTitle());
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Add content to the window.
    frame.add(ui, BorderLayout.CENTER);

    // Display the window.
    frame.pack();
    // frame.setResizable(false);
    frame.setVisible(true);
    return frame;
  }

  public static void main(String[] args) {
    MainView.turnOffBoldFont();
    MainView.createUi(new MainView());
  }
}
