package com.man.shop.repositories;

import com.man.shop.model.Attribute;
import com.man.shop.model.CategoryOfAttribute;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by smanolache on 12/19/2014.
 */
@Repository
public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {
    public List<Attribute> findByNameIgnoreCaseLike(@Param("name") String name, Pageable page);

    public List<Attribute> findByNameIgnoreCase(@Param("name") String name);

    public List<Attribute> findByNameIgnoreCaseLikeAndCategoryOfAttribute(@Param("name") String name, @Param("categoryOfAttribute") CategoryOfAttribute categoryOfAttribute, Pageable page);
}
