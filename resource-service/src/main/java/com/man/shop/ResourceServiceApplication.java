package com.man.shop;

import com.man.shop.clients.SearchClient;
import com.man.shop.rest.entites.RestProductFilterRequest;
import com.man.shop.rest.entites.RestProductFilterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

// TODO - need a way to stall the DB setup before after the Docker client initilisez the mysql container

@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ResourceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServiceApplication.class, args);
    }
}

@RestController
class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private SearchClient searchClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        System.out.println("The application name is:" + applicationName);
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping("/testFilter")
    public RestProductFilterResponse testFilterSolr(){
        RestProductFilterRequest restProductFilterRequest = new RestProductFilterRequest();

        Map<Long, List<Long>> attributes = new HashMap<>();

        List<Long> attrs = new ArrayList<>();
        attrs.add(new Long(36));
        attributes.put(new Long(14), attrs);
        restProductFilterRequest.setAttributes(attributes);


        Set<Long> facetedCats = new HashSet<>();
        facetedCats.add(new Long(14));facetedCats.add(new Long(45));facetedCats.add(new Long(56));
        restProductFilterRequest.setFacetedCategoriesOfAttributes(facetedCats);

        restProductFilterRequest.setPageNumber(0);
        restProductFilterRequest.setPageSize(10);

        return searchClient.filter(restProductFilterRequest);
    }
}
