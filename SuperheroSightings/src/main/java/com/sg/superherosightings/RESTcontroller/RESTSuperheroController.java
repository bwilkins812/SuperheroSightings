/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.RESTcontroller;

import com.sg.superherosightings.dao.SuperheroDao;
import com.sg.superherosightings.model.Superhero;
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
public class RESTSuperheroController {

    private SuperheroDao superheroDao;

    @Inject
    public RESTSuperheroController(SuperheroDao superheroDao) {
        this.superheroDao = superheroDao;
    }

    @RequestMapping(value = "/superhero/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Superhero getSuperhero(@PathVariable("superheroID") int superheroID) {
        return superheroDao.getSuperheroByID(superheroID);
    }

    @RequestMapping(value = "/superhero", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addSuperhero(@Valid @RequestBody Superhero superhero) {
        superheroDao.addSuperhero(superhero);
    }

    @RequestMapping(value = "/superhero/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSuperhero(@PathVariable("superheroID") int superheroID) {
        superheroDao.deleteSuperhero(superheroID);
    }

    @RequestMapping(value = "/superhero/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSuperhero(@PathVariable("superheroID") int superheroID,
            @Valid @RequestBody Superhero superhero) throws UpdateIntegrityException {

        if (superheroID != superhero.getSuperheroID()) {
            throw new UpdateIntegrityException("Superhero ID on URL must match Superhero ID in submitted data.");
        }

        superheroDao.updateSuperhero(superhero);
    }

    @RequestMapping(value = "/superheroes", method = RequestMethod.GET)
    @ResponseBody
    public List<Superhero> getAllSuperheroes() {
        return superheroDao.getAllSuperheroes();
    }

}
