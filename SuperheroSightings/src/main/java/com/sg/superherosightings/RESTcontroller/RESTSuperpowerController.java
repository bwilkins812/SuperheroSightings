/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.RESTcontroller;

import com.sg.superherosightings.dao.SuperpowerDao;
import com.sg.superherosightings.model.Superpower;
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
public class RESTSuperpowerController {

    private SuperpowerDao superpowerDao;

    @Inject
    public RESTSuperpowerController(SuperpowerDao superpowerDao) {
        this.superpowerDao = superpowerDao;
    }

    @RequestMapping(value = "/superpower/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Superpower getSuperpower(@PathVariable("superpowerID") int superpowerID) {
        return superpowerDao.getSuperpowersByID(superpowerID);
    }

    @RequestMapping(value = "/superpower", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addSuperpower(@Valid @RequestBody Superpower superpower) {
        superpowerDao.addSuperpower(superpower);
    }

    @RequestMapping(value = "/superpower/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSuperpower(@PathVariable("superpowerID") int superpowerID) {
        superpowerDao.deleteSuperpower(superpowerID);
    }

    @RequestMapping(value = "/superpower/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSuperpower(@PathVariable("superpowerID") int superpowerID,
            @Valid @RequestBody Superpower superpower) throws UpdateIntegrityException {

        if (superpowerID != superpower.getSuperpowerID()) {
            throw new UpdateIntegrityException("Superpower ID on URL must match Superpower ID in submitted data.");
        }
        superpowerDao.updateSuperpower(superpower);
    }

    @RequestMapping(value = "/superpowers", method = RequestMethod.GET)
    @ResponseBody
    public List<Superpower> getAllSuperpowers() {
        return superpowerDao.getAllSuperpowers();
    }

}
