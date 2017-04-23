package com.man.shop.repositories;


import com.man.shop.model.Picture;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by smanolache on 12/19/2014.
 */
@Repository
public interface PictureRepository extends PagingAndSortingRepository<Picture, Long> {
}
