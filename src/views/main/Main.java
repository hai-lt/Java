package views.main;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import models.os.OperatingSystem;
import views.memories.MemoriesPanel;
import views.networks.NetworkPanel;
import views.process.ProcessesPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class Main extends JPanel {
  private final static String NAME_TITLE = "System Management";
  private final static String PROCESSES_LABEL = "Processes";
  private final static String REMOTE_LABEL = "Access Remote";
  private final static String MEMORIES_LABEL = "Memories";
  private JTabbedPane tabMenu;
  private OperatingSystem os;

  public Main(OperatingSystem operatingSystem) {
    super(new GridLayout(1, 1));

    os = operatingSystem;
    tabMenu = new JTabbedPane();
    tabMenu.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    add(tabMenu);
    
    addTab(new ProcessesPanel(os.getProcessesManagement()), PROCESSES_LABEL, KeyEvent.VK_P);
    addTab(new MemoriesPanel(os.getRootFilesManagement()), MEMORIES_LABEL, KeyEvent.VK_M);
    addTab(new NetworkPanel(), REMOTE_LABEL, KeyEvent.VK_A);

  }

  public void addTab(Component component, String tabName, int mnemonic) {
    tabMenu.add(tabName, component);
    tabMenu.setMnemonicAt(tabMenu.getTabCount() - 1, mnemonic);
  }
  
  public String getTitle() {
    return NAME_TITLE + " - " + os.getName().toUpperCase();
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event dispatch thread.
   */
  private static void createAndShowGUI(Main ui) {
    // Create and set up the window.
    JFrame frame = new JFrame(ui.getTitle());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add content to the window.
    frame.add(ui, BorderLayout.CENTER);

    // Display the window.
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    // Schedule a job for the event dispatch thread:
    // creating and showing this application's GUI.
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        // Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        createAndShowGUI(new Main(new OperatingSystem()));
      }
    });
  }
}
