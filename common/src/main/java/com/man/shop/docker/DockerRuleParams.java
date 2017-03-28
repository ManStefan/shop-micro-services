package com.man.shop.docker;

import java.util.List;

public class DockerRuleParams {

  String imageName;

  String instanceName;

  String[] ports;
  String [] cmd;

  String portToWaitOn;
  public int waitTimeout;
  String logToWait;

  public List<String> environmentVariables;

  public List<String> binds;
}
