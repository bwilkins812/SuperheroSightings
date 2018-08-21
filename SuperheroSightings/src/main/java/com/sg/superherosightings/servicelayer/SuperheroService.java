/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.model.Superhero;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brettwilkins
 */
public interface SuperheroService {

    public void addSuperhero(Superhero superhero);

    public void deleteSuperhero(int superheroID);

    public void updateSuperhero(Superhero superhero);

    public Superhero getSuperheroByID(int superheroID);

    public List<Superhero> getAllSuperheroes();

    public List<Superhero> getSuperheroesByOrganizationID(int orgID);

    public List<Superhero> getSuperheroesBySuperpowerID(int superpowerID);

    public List<Superhero> getSuperheroesSightingID(int sightingID);

    public void insertMoreSuperpowers(int superheroID, int superpowerID);

    public void insertMoreOrganizations(int superheroID, int orgID);

    public void insertMoreSightings(int superheroID, int sightingID);

    public void deleteSuperpowerFromSuperhero(int superpowerID);

}
