package controllers;

import java.net.DatagramPacket;

import models.os.OperatingSystem;
import network.RestfulAPI;

public class OperatingSystemController extends RestfulAPI {
  public static final String RESOURCES = "/operating_system";

  public OperatingSystemController(DatagramPacket request) {
    super(request);
  }

  @Override
  public String create() {
    return null;
  }

  @Override
  public String index() {
    return new OperatingSystem().toString();
  }

  @Override
  public String show() {
    return null;
  }

  @Override
  public String update() {
    return null;
  }

  @Override
  public String destroy() {
    return null;
  }

  @Override
  public String getResources() {
    return RESOURCES;
  }

}
