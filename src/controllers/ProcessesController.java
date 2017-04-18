package controllers;

import java.net.DatagramPacket;

import models.os.LocalOperatingSystem;
import models.processes.ProcessInfo;
import network.RestfulAPI;

public class ProcessesController extends RestfulAPI {
  public static final String RESOURCES = "/processes";

  public ProcessesController(DatagramPacket request) {
    super(request);
  }

  @Override
  public String index() {
    return LocalOperatingSystem.getInstance().refreshProcesses().toString();
  }

  @Override
  public String destroy() {
    long id;
    try {
      id = Long.parseLong(getMessage().substring(getResources().length() + RestfulAPI.DELETE_METHOD.length() + 1));
    } catch (NumberFormatException e) {
      return e.getMessage();
    }
    ProcessInfo process = LocalOperatingSystem.getInstance().getProcessesManagement().killProcessPid(id);
    if (process != null) {
      return process.toString();
    }
    return failed();
  }

  @Override
  public String create() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String show() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String update() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getResources() {
    return RESOURCES;
  }
}
