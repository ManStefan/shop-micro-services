package com.man.shop.repositories;


import com.man.shop.model.CategoryOfAttribute;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by smanolache on 12/18/2014.
 */
@Repository
public interface CategoryOfAttributeRepository extends PagingAndSortingRepository<CategoryOfAttribute, Long> {

    public List<CategoryOfAttribute> findByNameIgnoreCaseLike(@Param("name") String name, Pageable page);

    public List<CategoryOfAttribute> findByNameIgnoreCase(@Param("name") String name);
}
