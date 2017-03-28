package com.man.shop.docker;

import com.spotify.docker.client.*;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.util.*;

import static com.spotify.docker.client.DockerClient.LogsParam.*;

/**
 * <p>
 * JUnit rule starting a docker container before the test and killing it
 * afterwards.
 * </p>
 * <p>
 * Uses spotify/docker-client.
 * Adapted from https://gist.github.com/mosheeshel/c427b43c36b256731a0b
 * </p>
 * author: Geoffroy Warin (geowarin.github.io)
 */
public class DockerRule {
  protected final Log logger = LogFactory.getLog(getClass());
  public static final String DOCKER_MACHINE_SERVICE_URL = "https://192.168.99.100:2376";

  private final DockerClient dockerClient;
  private ContainerCreation container;
  private Map<String, List<PortBinding>> ports;
  private DockerRuleParams params;

  public static DockerRuleBuilder builder() {
    return new DockerRuleBuilder();
  }

  DockerRule(DockerRuleParams params) {
    this.params = params;
    dockerClient = createDockerClient();
    ContainerConfig containerConfig = createContainerConfig(params.imageName, params.ports, params.cmd, params.environmentVariables, params.binds);

    try {
      dockerClient.pull(params.imageName);
      container = dockerClient.createContainer(containerConfig, params.instanceName);
    } catch (DockerException | InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

  public void before() throws Throwable {
    dockerClient.startContainer(container.id());
    ContainerInfo info = dockerClient.inspectContainer(container.id());
    ports = info.networkSettings().ports();

    if (params.portToWaitOn != null) {
      waitForPort(getHostPort(params.portToWaitOn), params.waitTimeout);
    }

    if (params.logToWait != null) {
      waitForLog(params.logToWait);
    }
  }

  public void after() {
    try {
      dockerClient.killContainer(container.id());
      dockerClient.removeContainer(container.id(), true);
      dockerClient.close();
    } catch (DockerException | InterruptedException e) {
      throw new RuntimeException("Unable to stop/remove docker container " + container.id(), e);
    }
  }

  /**
   * Utility method to get the docker host.
   * Can be different from localhost if using docker-machine
   *
   * @return The current docker host
   */
  private String getDockerHost() {
    return dockerClient.getHost();
  }

  private void waitForPort(int port, long timeoutInMillis) {
    SocketAddress address = new InetSocketAddress(getDockerHost(), port);
    long totalWait = 0;
    while (true) {
      try {
        SocketChannel.open(address);
        return;
      } catch (IOException e) {
        try {
          Thread.sleep(100);
          totalWait += 100;
          if (totalWait > timeoutInMillis) {
            throw new IllegalStateException("Timeout while waiting for port " + port);
          }
        } catch (InterruptedException ie) {
          throw new IllegalStateException(ie);
        }
      }
    }
  }

  private DockerClient createDockerClient() {
    if (isUnix() || System.getenv("DOCKER_HOST") != null) {
      try {
        return DefaultDockerClient.fromEnv().build();
      } catch (DockerCertificateException e) {
        System.err.println(e.getMessage());
      }
    }

    logger.info("Could not create docker client from the environment. Assuming docker-machine environment with url " + DOCKER_MACHINE_SERVICE_URL);
    DockerCertificates dockerCertificates = null;
    try {
      String userHome = System.getProperty("user.home");
      dockerCertificates = new DockerCertificates(Paths.get(userHome, ".docker/machine/certs"));
    } catch (DockerCertificateException e) {
      System.err.println(e.getMessage());
    }
    return DefaultDockerClient.builder()
      .uri(URI.create(DOCKER_MACHINE_SERVICE_URL))
      .dockerCertificates(dockerCertificates)
      .build();
  }

  private ContainerConfig createContainerConfig(String imageName, String[] ports, String [] cmd, List<String> environmentVariables, List<String> binds) {
    Map<String, List<PortBinding>> portBindings = new HashMap<>();

    for (String port : ports) {
        String hostContainerPort[] = port.split(":");
        List<PortBinding> hostPorts = Collections.singletonList(PortBinding.of("0.0.0.0",hostContainerPort[0]));
            portBindings.put(hostContainerPort[1], hostPorts);
    }

    HostConfig.Builder hostConfigBuilder = HostConfig.builder()
      .portBindings(portBindings);

    if (binds != null && !binds.isEmpty()){
        hostConfigBuilder = hostConfigBuilder.binds(binds);
    }

    HostConfig hostConfig = hostConfigBuilder.build();

    List<String> exposedPorts = new ArrayList<>();
      for (String port : ports) {
          String hostContainerPort[] = port.split(":");
          exposedPorts.add(hostContainerPort[1]);
      }
    ContainerConfig.Builder configBuilder = ContainerConfig.builder()
      .hostConfig(hostConfig)
      .image(imageName)
      .networkDisabled(false)
      .exposedPorts(exposedPorts.toArray(new String[exposedPorts.size()]))
            ;



    if (cmd != null) {
      configBuilder = configBuilder.cmd(cmd);
    }

    if (environmentVariables != null && !environmentVariables.isEmpty()){
        configBuilder = configBuilder.env(environmentVariables);
    }
    return configBuilder.build();
  }

  private int getHostPort(String containerPort) {
    List<PortBinding> portBindings = ports.get(containerPort);
    if (portBindings.isEmpty()) {
      return -1;
    }
    return Integer.parseInt(portBindings.get(0).hostPort());
  }

  private static boolean isUnix() {
    String os = System.getProperty("os.name").toLowerCase();
    return os.contains("nix") || os.contains("nux") || os.contains("aix");
  }

  private void waitForLog(String messageToMatch) throws DockerException, InterruptedException, UnsupportedEncodingException {
    LogStream logs = dockerClient.logs(container.id(), follow(), stdout());
    String log;
    do {
      LogMessage logMessage = logs.next();
      ByteBuffer buffer = logMessage.content();
      byte[] bytes = new byte[buffer.remaining()];
      buffer.get(bytes);
      log = new String(bytes);
    } while (!log.contains(messageToMatch));
  }
}
