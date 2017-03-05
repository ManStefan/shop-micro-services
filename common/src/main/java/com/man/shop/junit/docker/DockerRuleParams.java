package com.man.shop.junit.docker;

import java.util.List;

public class DockerRuleParams {

  String imageName;

  String[] ports;
  String [] cmd;

  String portToWaitOn;
  public int waitTimeout;
  String logToWait;

  public List<String> environmentVariables;

  public List<String> binds;
}
