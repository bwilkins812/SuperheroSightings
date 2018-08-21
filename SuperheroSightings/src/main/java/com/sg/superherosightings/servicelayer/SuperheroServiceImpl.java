/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.dao.SuperheroDao;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Superhero;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author brettwilkins
 */
public class SuperheroServiceImpl implements SuperheroService {

    private SuperheroDao superheroDao;

    public SuperheroServiceImpl(SuperheroDao superheroDao) {
        this.superheroDao = superheroDao;

    }

    @Override
    public void addSuperhero(Superhero superhero) {
        superheroDao.addSuperhero(superhero);
    }

    @Override
    public void deleteSuperhero(int superheroID) {
        superheroDao.deleteSuperhero(superheroID);
    }

    @Override
    public void updateSuperhero(Superhero superhero) {
        superheroDao.updateSuperhero(superhero);
    }

    @Override
    public Superhero getSuperheroByID(int superheroID) {
        return superheroDao.getSuperheroByID(superheroID);
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        return superheroDao.getAllSuperheroes();
    }

    @Override
    public List<Superhero> getSuperheroesByOrganizationID(int orgID) {
        return superheroDao.getSuperheroesByOrganizationID(orgID);
    }

    @Override
    public List<Superhero> getSuperheroesBySuperpowerID(int superpowerID) {
        return superheroDao.getSuperheroesBySuperpowerID(superpowerID);
    }

    @Override
    public List<Superhero> getSuperheroesSightingID(int sightingID) {
        return superheroDao.getSuperheroesSightingID(sightingID);
    }

    @Override
    public void insertMoreSuperpowers(int superheroID, int superpowerID) {
        superheroDao.insertMoreSuperpowers(superheroID, superpowerID);
    }

    @Override
    public void insertMoreOrganizations(int superheroID, int orgID) {
        superheroDao.insertMoreOrganizations(superheroID, orgID);
    }

    @Override
    public void insertMoreSightings(int superheroID, int sightingID) {
        superheroDao.insertMoreSightings(superheroID, sightingID);
    }

    @Override
    public void deleteSuperpowerFromSuperhero(int superpowerID) {
        superheroDao.deleteSuperpowerFromSuperhero(superpowerID);
    }

}
