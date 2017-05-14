package com.man.shop.service.rest;

import com.man.shop.model.Picture;
import com.man.shop.repositories.PictureRepository;
import com.man.shop.rest.resource.ResourceUtils;
import com.man.shop.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.net.URI;

/**
 * Created by smanolache on 5/13/2017.
 */
@Transactional
@RestController
@RequestMapping("/picture")
public class PictureService {

    @Autowired
    private StorageService storageService;

    @Autowired
    private PictureRepository pictureRepository;

    @PostMapping(path = "/product/{productId}")
    ResponseEntity<?> addPictureToProduct(@PathVariable long productId,
                                          StandardMultipartHttpServletRequest httpServletRequest){

        String description = httpServletRequest.getParameter("description");
        String name = httpServletRequest.getParameter("name");
        boolean isMain = Boolean.valueOf(httpServletRequest.getRequest().getParameter("isMain"));

        MultipartFile pictureFile = httpServletRequest.getFileMap().get("pictureFile");

        Picture picture = new Picture();
        picture.setDescription(description);
        picture.setName(name);
        picture.setMain(isMain);
        pictureRepository.save(picture);

        storageService.store(pictureFile, picture.getId().toString());

        URI location = ResourceUtils.buildProductResource(picture.getId().toString());

        return ResponseEntity.created(location).body(location);
    }
}
