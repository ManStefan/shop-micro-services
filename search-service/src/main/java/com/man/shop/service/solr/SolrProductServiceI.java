package com.man.shop.service.solr;

import com.man.shop.filters.model.ProductFilterRequest;
import com.man.shop.model.solr.SolrProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;

import java.util.List;

/**
 * Created by smanolache on 1/4/2015.
 */
public interface SolrProductServiceI {

    public Iterable<SolrProduct> findAll();

    public SolrProduct findOne(String id);

    public SolrProduct save(SolrProduct solrProduct);

    public FacetPage<SolrProduct> getFacetsForProduct(ProductFilterRequest productFilter, Pageable page);

    public Page<SolrProduct> getLatestProducts(int n);

    public Page<SolrProduct> getTopProductsBySales(int n);

    public boolean categoryHasProducts(long categoryId);

    public List<SolrProduct> findProductsForCategory(long categoryId, Pageable page);

    public boolean attributeHasProducts(long attributeId);

    public boolean producerHasProducts(long producerId);

    public boolean unitOfMeasurementHasProducts(long unitOfMeasurementId);

    public List<SolrProduct> findByApproxNameAndCategory(String name, List<Long> categoriesOfProduct, Pageable page);

    public List<SolrProduct> findByApproxName(String name, Pageable page);

    public List<SolrProduct> findByNameIgnoreCase(String name);

    public List<SolrProduct> getTopProductsByPromoPercentage(int numberOfProducts);

}
