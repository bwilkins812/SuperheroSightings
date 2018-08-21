/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.servicelayer.LocationService;
import com.sg.superherosightings.servicelayer.SightingService;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@Controller("/location")
public class LocationController {

    LocationService locationService;
    SightingService sightingService;

    @Inject
    public LocationController(LocationService locationService, SightingService sightingService) {
        this.locationService = locationService;
        this.sightingService = sightingService;

    }

    @RequestMapping(value = "/displayLocationsPage", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {

        List<Location> locationList = locationService.getAllLocations();
        List<Sighting> sightingList = sightingService.getAllSightings();
        model.addAttribute("locationList", locationList);
        model.addAttribute("sightingList", sightingList);

        return "locations";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request) {

        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));
        location.setLocationDescription(request.getParameter("locationDescription"));
        location.setAddress(request.getParameter("locationAddress"));
        String latitudeString = request.getParameter("locationLatitude");
        BigDecimal latitude = new BigDecimal(latitudeString);
        location.setLatitude(latitude);
        String longitudeString = request.getParameter("locationLongitude");
        BigDecimal longitude = new BigDecimal(longitudeString);
        location.setLongitude(longitude);
        String stringSightingID = request.getParameter("sighting");
        int sightingID = Integer.parseInt(stringSightingID);
        Sighting sighting = sightingService.getSightingsByID(sightingID);
        List<Sighting> sightings = new ArrayList();
        sightings.add(sighting);
        location.setSightings(sightings);

        locationService.addLocation(location);

        return "redirect:displayLocationsPage";
    }

    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locationIDParameter = request.getParameter("locationID");
        int locationID = Integer.parseInt(locationIDParameter);

        Location location = locationService.getLocationByID(locationID);
        List<Sighting> sightingList = location.getSightings();

        model.addAttribute("location", location);
        model.addAttribute("sightingList", sightingList);
        return "locationDetails";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIDParameter = request.getParameter("locationID");
        long longLocationID = Long.parseLong(locationIDParameter);
        int locationID = (int) longLocationID;
        locationService.deleteLocation(locationID);
        return "redirect:displayLocationsPage";
    }

    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationsForm(HttpServletRequest request, Model model) {
        String locationIDParameter = request.getParameter("locationID");
        long longLocationID = Long.parseLong(locationIDParameter);
        int locationID = (int) longLocationID;
        Location location = locationService.getLocationByID(locationID);
        List<Sighting> sightingList = sightingService.getAllSightings();
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("location", location);
        return "editLocationsForm";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocations(@Valid @ModelAttribute("location") Location location, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "editLocationsForm";
        }

        String stringSightingID = request.getParameter("sighting");
        int sightingID = Integer.parseInt(stringSightingID);
        Sighting sighting = sightingService.getSightingsByID(sightingID);
        List<Sighting> sightings = new ArrayList();
        sightings.add(sighting);
        location.setSightings(sightings);

        locationService.updateLocation(location);

        return "redirect:displayLocationsPage";
    }

}
