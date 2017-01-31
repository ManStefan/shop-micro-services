package com.man.shop.repositories;



import com.man.shop.model.solr.SolrProduct;
import com.man.shop.repositories.custom.ProductSolrRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;


import java.util.List;

/**
 * Created by smanolache on 12/22/2014.
 */


//http://localhost:8880/solr/collection1/select?q=%7B!tag%3Dcat%7DcategoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=%7B!ex%3Dcat%7DcategoryOfProduct&facet.field=producer
//http://localhost:8880/solr/collection1/select?q=categoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=categoryOfProduct&facet.field=%7B!ex%3Dpr%7Dproducer&fq=%7B!tag%3Dpr%7Dproducer%3A3

//http://localhost:8880/solr/collection1/select?q=*%3A*&fq=%7B!tag%3Dcat%7DcategoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=%7B!ex%3Dcat%7DcategoryOfProduct&facet.field=producer
//http://localhost:8880/solr/collection1/select?q=*%3A*&fq=categoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=categoryOfProduct&facet.field=%7B!ex%3Dpr%7Dproducer&fq=%7B!tag%3Dpr%7Dproducer%3A3

//http://localhost:8880/solr/collection1/select?q=*%3A*&fq=%7B!tag%3Dcat%7DcategoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=%7B!ex%3Dcat%7DcategoryOfProduct&facet.field=producer&facet.field=attributes
//http://localhost:8880/solr/collection1/select?q=*%3A*&fq=categoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=categoryOfProduct&facet.field=%7B!ex%3Dpr%7Dproducer&fq=%7B!tag%3Dpr%7Dproducer%3A3&facet.field=attributes


//http://localhost:8880/solr/collection1/select?q=*%3A*&fq=%7B!tag%3Dcat%7DcategoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=%7B!ex%3Dcat%7DcategoryOfProduct&facet.field=producer&facet.field=attributes
//http://localhost:8880/solr/collection1/select?q=*%3A*&fq=categoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=categoryOfProduct&facet.field=%7B!ex%3Dpr%7Dproducer&fq=%7B!tag%3Dpr%7Dproducer%3A4&facet.field=attributes
//http://localhost:8880/solr/collection1/select?q=*%3A*&fq=categoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=categoryOfProduct&facet.field=producer&fq=producer%3A4&facet.field=%7B!ex%3Dattr%7Dattributes&fq=%7B!tag%3Dattr%7Dattributes%3A11

//buna:
//http://localhost:8880/solr/collection1/select?q=*%3A*&fq=%7B!tag%3Dcat%7DcategoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=%7B!ex%3Dcat%7DcategoryOfProduct&facet.field=producer&facet.field=attributes
//http://localhost:8880/solr/collection1/select?q=*%3A*&fq=%7B!tag%3Dcat%7DcategoryOfProduct%3A4&wt=json&indent=true&facet=true&facet.field=%7B!ex%3Dcat%7DcategoryOfProduct&facet.field=%7B!ex%3Dpr%7Dproducer&fq=%7B!tag%3Dpr%7Dproducer%3A4&facet.field=attributes

public interface ProductSolrRepository extends SolrCrudRepository<SolrProduct, String>, ProductSolrRepositoryCustom {

    public List<SolrProduct> findByCategoryOfProduct(Long categoryOfProduct, Pageable page);

    public List<SolrProduct> findByAttributes(Long attributeId, Pageable pageable);

    public List<SolrProduct> findByProducer(Long producerId, Pageable page);

    public List<SolrProduct> findByQuantityStandard(Long quantityStandard, Pageable page);

    public List<SolrProduct> findByNameIgnoreCase(String name, Pageable page);

    public List<SolrProduct> findByPromoOrderByPromoPercentageDesc(Boolean promo, Pageable page);
}
