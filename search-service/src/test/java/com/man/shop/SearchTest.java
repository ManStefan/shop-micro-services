package com.man.shop;


import com.man.shop.junit.docker.DockerRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

/**
 * Created by smanolache on 2/16/2017.
 */

//docker run -d -P -v //D/conf:/myconfig -p 8983:8983 solr solr-create -c mycore -d /myconfig
//docker exec -t -i 472b /bin/bash
    // docker-machine ssh default
    //docker inspect 18d5

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class SearchTest {

//    @ClassRule
//    public static DockerRule mysql =
//            DockerRule.builder()
//                    .image("mysql:latest")
//                    .ports("3306")
//                    .env(Arrays.asList("MYSQL_ROOT_PASSWORD=password"))
//                    .build();

    @Value("${solr.core}")
    private static String solrCore;

    @ClassRule
    public static DockerRule solr =
            DockerRule.builder()
                        .image("solr:latest")
                        .ports("8984:8983")
                         .binds(Arrays.asList("//D/conf:/myconfig"))
                        .cmd(new String[]{"solr-create", "-c", "shop_dev","-d",  "/myconfig"})

                    .waitForPort("8983/tcp")
            .build();

    @Test
    public void testSearch(){
        assert(true);
        int i = 1;
        i = i*2;
//        try {
//            Thread.sleep(500000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
