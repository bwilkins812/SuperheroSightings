/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.RESTcontroller;

import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.model.Sighting;
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
public class RESTSightingController {

    private SightingDao sightingDao;

    @Inject
    public RESTSightingController(SightingDao sightingDao) {
        this.sightingDao = sightingDao;
    }

    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Sighting getSighting(@PathVariable("sightingID") int sightingID) {
        return sightingDao.getSightingsByID(sightingID);
    }

    @RequestMapping(value = "/sighting", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addSighting(@Valid @RequestBody Sighting sighting) {
        sightingDao.addSighting(sighting);
    }

    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSighting(@PathVariable("sightingID") int sightingID) {
        sightingDao.deleteSighting(sightingID);
    }

    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSighting(@PathVariable("sightingID") int sightingID,
            @Valid @RequestBody Sighting sighting) throws UpdateIntegrityException {

        if (sightingID != sighting.getSightingID()) {
            throw new UpdateIntegrityException("Sighting ID on URL must match Sighting ID in submitted data.");
        }
        sightingDao.updateSighting(sighting);
    }

    @RequestMapping(value = "/sightings", method = RequestMethod.GET)
    @ResponseBody
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }

}
