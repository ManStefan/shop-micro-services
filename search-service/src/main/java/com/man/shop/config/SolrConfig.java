package com.man.shop.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by smanolache on 1/19/2017.
 */
@Configuration
@EnableSolrRepositories(basePackages = { "com.man.shop" }, multicoreSupport = true)
public class SolrConfig {

    @Value("${solr.host}")
    private String solrHost;

    @Value("${solr.core}")
    private String solrCore;

    @Bean
    SolrClient solrClient() {
        HttpSolrClient client =new HttpSolrClient(solrHost + solrCore);
        client.setConnectionTimeout(1000);
        return client;
    }

    @Bean
    public SolrOperations solrTemplate() throws ParserConfigurationException, SAXException, IOException {
        return new SolrTemplate(solrClient());
    }



}
