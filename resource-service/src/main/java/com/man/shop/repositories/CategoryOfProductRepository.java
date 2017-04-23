package com.man.shop.repositories;

import com.man.shop.model.CategoryOfProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by smanolache on 12/19/2014.
 */
@Repository
public interface CategoryOfProductRepository  extends PagingAndSortingRepository<CategoryOfProduct, Long> {
    public List<CategoryOfProduct> findDistinctByLevel(int level);

    public List<CategoryOfProduct> findByNameIgnoreCaseLike(@Param("name") String name, Pageable page);
}
