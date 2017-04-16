package views.process;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import models.processes.ProcessInfo;
import models.processes.ProcessesManagement;
import views.AppResources;

public abstract class ProcessesPanel extends JPanel {
  private final static int NOTIFICATION_DEFAULT_TIME = 2000;
  private final static int WARNING = 0;
  private final static int SUCCESS = 1;
  private final static int DANGER = 2;

  private final static String WELCOME_NOTIFICATION = "Welcome to OS Management designed by Hai Le Tan";
  private ProcessesManagement processesManagement;
  private ProcessTable tbProcesses;
  private JButton btnRefresh, btnKillProcess;
  private JLabel notificationLabel;

  public ProcessesPanel(ProcessesManagement processesManagement) {
    super();
    this.processesManagement = processesManagement;
    setUiProcesses();
  }

  public void setUiProcesses() {
    setLayout(new BorderLayout());
    tbProcesses = new ProcessTable(processesManagement);
    JScrollPane scrollPane = new JScrollPane(tbProcesses);
    add(scrollPane, BorderLayout.CENTER);

    JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    setAlignmentX(RIGHT_ALIGNMENT);
    btnRefresh = new JButton("Refresh");
    btnRefresh.addActionListener(refreshProcesses());
    buttons.add(btnRefresh);
    btnKillProcess = new JButton("End Process");
    btnKillProcess.addActionListener(killProcessListener());
    buttons.add(btnKillProcess);

    add(buttons, BorderLayout.NORTH);

    notificationLabel = new JLabel(WELCOME_NOTIFICATION);
    notificationLabel.setForeground(getColor(SUCCESS));
    add(notificationLabel, BorderLayout.SOUTH);
  }

  public abstract ActionListener refreshProcesses();

  private ActionListener killProcessListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // do something when press the End Process button
        int rowSelected = tbProcesses.getSelectedRow();
        if (rowSelected > -1) {
          killProcess(rowSelected);
        } else {
          notifyMessage("Please select a process which you really want to stop!", WARNING);
        }
      }
    };
  }

  private void killProcess(int rowSelected) {
    long pid = Long.parseLong((String) tbProcesses.getValueAt(rowSelected, 0));
    ProcessInfo processKilled = tbProcesses.getProcessesManagement().killProcessPid(pid);
    if (processKilled != null) {
      tbProcesses.getProcessesManagement().loadProcesses();
      tbProcesses.refreshData();
      notifyMessage("Stopped a process which Pid is " + processKilled.getId());
    } else {
      notifyMessage("You are not allowed to stop this process", DANGER);
    }
  }

  private void notifyMessage(String msg) {
    notifyMessage(msg, SUCCESS);
  }

  private void notifyMessage(String msg, int type) {
    new Thread(new Runnable() {

      @Override
      public void run() {
        notificationLabel.setText(msg);
        notificationLabel.setForeground(getColor(type));
        try {
          Thread.sleep(NOTIFICATION_DEFAULT_TIME);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        notificationLabel.setText(WELCOME_NOTIFICATION);
        notificationLabel.setForeground(getColor(SUCCESS));
        ;
      }
    }).start();
  }

  public ProcessTable getProcessTable() {
    return tbProcesses;
  }

  public ProcessesManagement getProcessesManagement() {
    return processesManagement;
  }

  public void setProcessesManagement(ProcessesManagement processesManagement) {
    this.processesManagement = processesManagement;
  }

  public void notifyProcessesChanged() {
    tbProcesses.setProcessesManagement(processesManagement);
    tbProcesses.refreshData();
  }

  private Color getColor(int type) {
    switch (type) {
    case WARNING:
      return AppResources.COLOR_WARNING;
    case DANGER:
      return AppResources.COLOR_DANGER;

    default:
      return AppResources.COLOR_SUCCESS;
    }
  }

}
