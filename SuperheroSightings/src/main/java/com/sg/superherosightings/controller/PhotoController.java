/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.ProfilePhoto;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SightingPhoto;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.servicelayer.PhotoService;
import com.sg.superherosightings.servicelayer.SightingService;
import com.sg.superherosightings.servicelayer.SuperheroService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author brettwilkins
 */
@Controller
public class PhotoController {

    private PhotoService photoService;
    SightingService sightingService;
    SuperheroService superheroService;

    @Inject
    public PhotoController(PhotoService photoService, SightingService sightingService, SuperheroService superheroService) {
        this.photoService = photoService;
        this.sightingService = sightingService;
        this.superheroService = superheroService;

    }

    @RequestMapping(value = "/pictureGallery", method = RequestMethod.GET)
    public String displayPictureGallery(Model model) {

        List<SightingPhoto> sightingPhotos = photoService.getAllSightingPhotos();
        model.addAttribute("sightingPhotoList", sightingPhotos);

        List<ProfilePhoto> profilePhotos = photoService.getAllProfilePhotos();
        model.addAttribute("profilePhotoList", profilePhotos);

        return "pictureGallery";
    }

    @RequestMapping(value = "addPictureForm", method = RequestMethod.GET)
    public String displayAddPictureForm(Model model) {

        List<Sighting> sightingList = sightingService.getAllSightings();
        model.addAttribute("sightingList", sightingList);

        List<Superhero> superheroList = superheroService.getAllSuperheroes();
        model.addAttribute("superheroList", superheroList);

        return "addPictureForm";
    }

    @RequestMapping(value = "/addSightingPhoto", method = RequestMethod.POST)
    public String addSightingPhoto(HttpServletRequest request,
            Model model,
            @RequestParam("displayTitle") String displayTitle,
            @RequestParam("sightingPhoto") MultipartFile pictureFile) {

        int sightingID = Integer.parseInt(request.getParameter("sighting"));

        String message = photoService.addSightingPhoto(request, model, displayTitle, pictureFile, sightingID);

        return message;

    }

    @RequestMapping(value = "/addProfilePhoto", method = RequestMethod.POST)
    public String addProfilePhoto(HttpServletRequest request,
            Model model,
            @RequestParam("displayTitle") String displayTitle,
            @RequestParam("profilePhoto") MultipartFile pictureFile) {

        int superheroID = Integer.parseInt(request.getParameter("superhero"));

        String message = photoService.addProfilePhoto(request, model, displayTitle, pictureFile, superheroID);

        return message;
    }

}
