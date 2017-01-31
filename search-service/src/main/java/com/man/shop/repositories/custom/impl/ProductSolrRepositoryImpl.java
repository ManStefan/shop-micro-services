package com.man.shop.repositories.custom.impl;

import com.man.shop.filters.model.ProductFilterRequest;
import com.man.shop.filters.service.ProductFilterService;
import com.man.shop.model.solr.SolrProduct;
import com.man.shop.repositories.custom.ProductSolrRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.FacetPage;

/**
 * Created by smanolache on 1/8/2015.
 */
public class ProductSolrRepositoryImpl implements ProductSolrRepositoryCustom {

    @Autowired
    private SolrOperations solrOperations;



    @Override
    public FacetPage<SolrProduct> getFacetsForProduct(/*ProductFilterRequest productFilter*/ FacetQuery facetQuery) {
        return solrOperations.queryForFacetPage(/*productFilterService.createFacetQuery(productFilter)*/facetQuery, SolrProduct.class);
    }

    @Override
    public Page<SolrProduct> getTopProductsBySales(int n) {
        Query simpleQyery = new SimpleQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD));

        simpleQyery.addSort(new Sort(Sort.Direction.DESC, SolrProduct.Fields.UNITS_SOLD)).setRows(n);

        return solrOperations.queryForPage(simpleQyery, SolrProduct.class);
    }

    @Override
    public Page<SolrProduct> getLatestProducts(int n) {
        Query simpleQyery = new SimpleQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD));

        simpleQyery.addSort(new Sort(Sort.Direction.DESC, SolrProduct.Fields.ACTIVATION_DATE)).setRows(n);

        return solrOperations.queryForPage(simpleQyery, SolrProduct.class);

    }
}
