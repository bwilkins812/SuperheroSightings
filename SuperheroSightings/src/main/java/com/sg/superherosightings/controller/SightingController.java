/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SightingPhoto;
import com.sg.superherosightings.servicelayer.PhotoService;
import com.sg.superherosightings.servicelayer.SightingService;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author brettwilkins
 */
@Controller("/sighting")
public class SightingController {

    SightingService sightingService;
    PhotoService photoService;

    @Inject
    public SightingController(SightingService sightingService, PhotoService photoService) {
        this.sightingService = sightingService;
        this.photoService = photoService;
    }

    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {

        List<Sighting> sightingList = sightingService.getAllSightings();
        model.addAttribute("sightingList", sightingList);

        return "sightings";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayRecentSightings(Model model) {

        List<Sighting> recentList = sightingService.getRecentSightings();
        model.addAttribute("recentList", recentList);

        return "home";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request) {

        Sighting sighting = new Sighting();
        sighting.setSightingName(request.getParameter("sightingName"));
        String sdString = request.getParameter("sightingDate");
        LocalDate sightingDate = LocalDate.parse(sdString);
        sighting.setSightingDate(sightingDate);

        sightingService.addSighting(sighting);

        return "redirect:displaySightingsPage";
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIDParameter = request.getParameter("sightingID");
        int sightingID = Integer.parseInt(sightingIDParameter);

        Sighting sighting = sightingService.getSightingsByID(sightingID);
        SightingPhoto sightingPhoto = photoService.getSightingPhotoForSighting(sightingID);

        model.addAttribute("sighting", sighting);
        model.addAttribute("sightingPhoto", sightingPhoto);

        return "sightingDetails";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIDParameter = request.getParameter("sightingID");
        long longSightingID = Long.parseLong(sightingIDParameter);
        int sightingID = (int) longSightingID;
        sightingService.deleteSighting(sightingID);
        return "redirect:displaySightingsPage";
    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingIDParameter = request.getParameter("sightingID");
        long longSightingID = Long.parseLong(sightingIDParameter);
        int sightingID = (int) longSightingID;
        Sighting sighting = sightingService.getSightingsByID(sightingID);
        model.addAttribute("sighting", sighting);
        return "editSightingsForm";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result, HttpServletRequest request) {

        String stringSightingDate = request.getParameter("sightingDate");
        LocalDate sightingDate = LocalDate.parse(stringSightingDate);
        sighting.setSightingDate(sightingDate);

        if (result.hasErrors()) {
            return "editSightingsForm";
        }

        sightingService.updateSighting(sighting);

        return "redirect:displaySightingsPage";
    }

}
