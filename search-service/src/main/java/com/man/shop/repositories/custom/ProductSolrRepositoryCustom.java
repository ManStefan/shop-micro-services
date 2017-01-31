package com.man.shop.repositories.custom;

import com.man.shop.filters.model.ProductFilterRequest;
import com.man.shop.model.solr.SolrProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.result.FacetPage;

/**
 * Created by smanolache on 1/8/2015.
 */
public interface ProductSolrRepositoryCustom {

    public FacetPage<SolrProduct> getFacetsForProduct(FacetQuery facetQuery);
    public Page<SolrProduct> getTopProductsBySales(int n);
    public Page<SolrProduct> getLatestProducts(int n);
}
