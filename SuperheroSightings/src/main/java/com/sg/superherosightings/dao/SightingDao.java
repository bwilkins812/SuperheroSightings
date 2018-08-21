/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Sighting;
import java.util.List;

/**
 *
 * @author brettwilkins
 */
public interface SightingDao {

    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingID);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingsByID(int id);

    public List<Sighting> getAllSightings();

    public List<Sighting> getRecentSightings();

}
