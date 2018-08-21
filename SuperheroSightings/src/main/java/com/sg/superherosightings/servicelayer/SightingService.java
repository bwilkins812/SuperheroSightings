/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.model.Sighting;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brettwilkins
 */
public interface SightingService {

    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingID);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingsByID(int id);

    public List<Sighting> getAllSightings();

    public List<Sighting> getRecentSightings();

}
