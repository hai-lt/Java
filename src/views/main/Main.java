package views.main;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import models.os.OperatingSystem;
import views.memories.MemoriesPanel;
import views.networks.NetworkPanel;
import views.process.ProcessesPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class Main extends JPanel {
  private static String appName = "System Management";
  private final static String PROCESSES_LABEL = "Processes";
  private final static String REMOTE_LABEL = "Access Remote";
  private final static String MEMORIES_LABEL = "Memories";
  private final static int WIDTH = 500;
  private final static int HEIGHT = 500;

  public Main() {
    super(new GridLayout(1, 1));

    JTabbedPane tabMenu = new JTabbedPane();
    OperatingSystem os = new OperatingSystem();
    ProcessesPanel pn = new ProcessesPanel(os.getProcessesManagement());
    tabMenu.addTab(PROCESSES_LABEL, pn);
    tabMenu.setMnemonicAt(0, KeyEvent.VK_P);

    JComponent memoriesPanel = new MemoriesPanel(os.getRootFilesManagement());
    tabMenu.addTab(MEMORIES_LABEL, memoriesPanel);
    tabMenu.setMnemonicAt(1, KeyEvent.VK_M);

    JComponent remotePanel = new NetworkPanel();
    tabMenu.addTab(REMOTE_LABEL, remotePanel);
    tabMenu.setMnemonicAt(2, KeyEvent.VK_A);
    remotePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    add(tabMenu);

    tabMenu.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event dispatch thread.
   */
  private static void createAndShowGUI() {
    // Create and set up the window.
    JFrame frame = new JFrame(appName);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add content to the window.
    frame.add(new Main(), BorderLayout.CENTER);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    // Schedule a job for the event dispatch thread:
    // creating and showing this application's GUI.
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        // Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        createAndShowGUI();
      }
    });
  }
}
