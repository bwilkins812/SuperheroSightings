/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.RESTcontroller;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.model.Location;
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
public class RESTLocationController {

    private LocationDao locationDao;

    @Inject
    public RESTLocationController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Location getLocation(@PathVariable("locationID") int locationID) {
        return locationDao.getLocationByID(locationID);
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addLocation(@Valid @RequestBody Location location) {
        locationDao.addLocation(location);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("locationID") int locationID) {
        locationDao.deleteLocation(locationID);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLocation(@PathVariable("locationID") int locationID,
            @Valid @RequestBody Location location) throws UpdateIntegrityException {

        if (locationID != location.getLocationID()) {
            throw new UpdateIntegrityException("Location ID on URL must match Location ID in submitted data.");
        }
        locationDao.updateLocation(location);
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    @ResponseBody
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

}
