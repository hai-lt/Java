package views.process;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.processes.ProcessInfo;
import models.processes.ProcessesManagement;

public class ProcessTable extends JTable {
  private ProcessesManagement processesManagement;
  private Vector<String> headers;

  public ProcessTable(ProcessesManagement processesManagement) {
    super();
    this.processesManagement = processesManagement;
    setHeaders();
    DefaultTableModel model = new DefaultTableModel(processesManagement.getProcessesVector(), getHeaders());
    setModel(model);
  }

  public void addRow(ProcessInfo processInfo) {
    ((DefaultTableModel) getModel()).addRow(processInfo.toVector());
  }

  public ProcessesManagement getProcessesManagement() {
    return processesManagement;
  }

  public void setProcessesManagement(ProcessesManagement processesManagement) {
    this.processesManagement = processesManagement;

  }

  public Vector<String> getHeaders() {
    return headers;
  }

  public void setHeaders(String[] headers) {
    this.headers = new Vector<>();
    for (String string : headers) {
      this.headers.add(string);
    }
  }

  public void setHeaders() {
    setHeaders(processesManagement.getTitles());
  }

  public void refreshData() {
    DefaultTableModel model = new DefaultTableModel(processesManagement.getProcessesVector(), getHeaders());
    setModel(model);
    model.fireTableDataChanged();
  }

}
