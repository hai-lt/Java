package controllers;

import models.os.LocalOperatingSystem;
import network.RestfulAPI;

public class MemoriesController extends RestfulAPI {
  public static final String RESOURCES = "/memories";

  public MemoriesController(String request) {
    super(request);
  }

  @Override
  public String create() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String index() {
    return LocalOperatingSystem.getInstance().refreshMemories().toString();
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
  public String destroy() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getResources() {
    return RESOURCES;
  }

}
