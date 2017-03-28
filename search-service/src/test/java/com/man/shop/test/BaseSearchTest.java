package com.man.shop.test;

import com.man.shop.docker.DockerRule;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

/**
 * Created by smanolache on 3/28/2017.
 */
public abstract class BaseSearchTest {

    private DockerRule solr;

    @Value("${solr.core}")
    private String solrCore;

    @Value("${solr.config.schema}")
    private String solrConfigSchema;

    @Value("${solr.create_container}")
    private boolean createContainer;

    @Value("${solr.remove_container}")
    private boolean removeContainer;

    @Value("${solr.instanceName}")
    private String instanceName;

    @Before
    public void setup() throws Throwable {
        if (createContainer) {
            solr =
                    DockerRule.builder()
                            .image("solr:latest")
                            .instanceName(instanceName)
                            .ports("8984:8983")
                            .binds(Arrays.asList(solrConfigSchema + ":/myconfig"))
                            .cmd(new String[]{"solr-create", "-c", solrCore, "-d", "/myconfig"})
                            .waitForPort("8983/tcp")
                            .build();
            solr.before();

            //TODO - this is used for waiting for the new core the be loaded - must find a better way to do this - maybe scanning the logs??
            Thread.sleep(20000);
        }

        doBeforeLogic();
    }

    protected abstract void doBeforeLogic() throws Exception;

    @After
    public void tearDown(){
        doAfterLogic();

        if (removeContainer) {
            solr.after();
        }
    }

    protected abstract void doAfterLogic();
}
