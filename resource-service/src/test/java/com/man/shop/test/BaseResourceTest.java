package com.man.shop.test;

import com.man.shop.docker.DockerRule;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

/**
 * Created by smanolache on 3/28/2017.
 */
public abstract class BaseResourceTest {

    private DockerRule mysql;

    @Value("${mysql.create_container}")
    private boolean createContainer;

    @Value("${mysql.remove_container}")
    private boolean removeContainer;

    @Value("${mysql.instanceName}")
    private String instanceName;

    @Before
    public void setup() throws Throwable {
        if (createContainer) {
            mysql =
                    DockerRule.builder()
                            .image("mysql:latest")
                            .instanceName(instanceName)
                            .ports("3307:3306")
                            .waitForPort("3306/tcp")
                            .build();
            mysql.before();

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
            mysql.after();
        }
    }

    protected abstract void doAfterLogic();
}
