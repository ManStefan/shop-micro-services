package com.man.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by smanolache on 1/19/2017.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SearchServiceApplication {

    public static void main(String args[]){
        SpringApplication.run(SearchServiceApplication.class, args);
    }
}
