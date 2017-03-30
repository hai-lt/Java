package views.process;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.processes.ProcessesManagement;

public class ProcessesPanel extends JPanel {
  private ProcessesManagement processesManagement;
  private ProcessTable tbProcesses;
  private JButton btnRefresh, btnKillProcess;

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
  }

  private ActionListener refreshProcesses() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tbProcesses.getProcessesManagement().loadProcesses();
        tbProcesses.refreshData();
      }
    };
  }

  private ActionListener killProcessListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // do something when press the End Process button
      }
    };
  }

}
