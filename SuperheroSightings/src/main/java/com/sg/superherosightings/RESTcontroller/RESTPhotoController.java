/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.RESTcontroller;

import com.sg.superherosightings.model.SightingPhoto;
import com.sg.superherosightings.servicelayer.PhotoService;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author brettwilkins
 */
@CrossOrigin
@Controller
public class RESTPhotoController {

    private PhotoService photoService;

    @Inject
    public RESTPhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping(value = "/picture/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SightingPhoto getPicture(@PathVariable("sightingID") int sightingID) {
        return photoService.getSightingPhotoForSighting(sightingID);
    }

    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addRESTPicture(@Valid @RequestBody SightingPhoto sightingPhoto) {
        photoService.addRESTPicture(sightingPhoto);
    }

    @RequestMapping(value = "/pictures", method = RequestMethod.GET)
    @ResponseBody
    public List<SightingPhoto> getAllSightingPhotos() {
        return photoService.getAllSightingPhotos();
    }

}
