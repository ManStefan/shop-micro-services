package com.man.shop.utils;

import com.man.shop.docker.DockerRule;
import com.man.shop.docker.DockerRuleBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by smanolache on 3/30/2017.
 */
public class DockerInstanceBuilder {
    private static void createDockerInstance(String imageName, String instanceName,
                                        String ports, String solrConfigSchema, String [] cmd,
                                        String waitForPort, List<String> env) throws Throwable {

        DockerRuleBuilder builder = DockerRule.builder()
                    .image(imageName)
                    .instanceName(instanceName)
                    .ports(ports);

        if (solrConfigSchema != null){
            builder = builder.binds(Arrays.asList(solrConfigSchema + ":/myconfig"));
        }
        if (cmd != null) {
            builder = builder.cmd(cmd);
        }
        if (waitForPort != null){
            builder = builder.waitForPort(waitForPort);
        }
        if (env != null){
            builder = builder.env(env);
        }


        DockerRule solr = builder.build();

        solr.before();
    }

    public static void createSolrInstanceForDev() throws Throwable {
        String imageName = "solr:latest";
        String instanceName = "solr_dev";
        String core = "shop";
        String ports = "8983:8983";
        String solrConfigSchema = "//D/me/store/microservices/shop-micro-services/common/src/main/resources/solr/config";
        String [] cmd = new String[]{"solr-create", "-c", core, "-d", "/myconfig"};
        String waitForPort = "8983/tcp";

        createDockerInstance(imageName, instanceName, ports, solrConfigSchema, cmd, waitForPort, null);
    }

    public static void createSolrInstanceForTest() throws Throwable {
        String imageName = "solr:latest";
        String instanceName = "solr_dev";
        String core = "shop";
        String ports = "8984:8983";
        String solrConfigSchema = "//D/me/store/microservices/shop-micro-services/common/src/main/resources/solr/config";
        String [] cmd = new String[]{"solr-create", "-c", core, "-d", "/myconfig"};
        String waitForPort = "8983/tcp";

        createDockerInstance(imageName, instanceName, ports, solrConfigSchema, cmd, waitForPort, null);
    }

    public static void createMySQLInstanceForTest() throws Throwable {
        String imageName = "mysql:latest";
        String instanceName = "mysql_unit_test";
        String ports = "3307:3306";
        List<String> env = Arrays.asList("MYSQL_ROOT_PASSWORD=password");

        createDockerInstance(imageName, instanceName, ports, null, null, null, env);
    }

    public static void createMySQLInstanceForDev() throws Throwable {
        String imageName = "mysql:latest";
        String instanceName = "mysql_unit_test";
        String ports = "3306:3306";
        List<String> env = Arrays.asList("MYSQL_ROOT_PASSWORD=password");

        createDockerInstance(imageName, instanceName, ports, null, null, null, env);
    }

    public static void main(String args[]) throws Throwable {
        createMySQLInstanceForTest();
    }
}
