package com.techelevator.controller;

import com.techelevator.dao.ImageDao;
import com.techelevator.model.Image;
import com.techelevator.model.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageDao imageDao;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{bandName}/gallery")
    public List<Image> getImagesForGallery(@PathVariable String bandName) {
        return imageDao.getGallery(bandName);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/band/gallery-upload")
    public void uploadImageToGallery(@RequestBody Map<String, String> body){
        imageDao.uploadImage(body.get("url"), body.get("bandName"));
    }



}
