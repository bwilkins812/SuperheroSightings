/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import java.util.List;

/**
 *
 * @author brettwilkins
 */
public interface LocationDao {

    public void addLocation(Location location);

    public void deleteLocation(int locationID);

    public void updateLocation(Location location);

    public Location getLocationByID(int id);

    public List<Location> getAllLocations();

    public List<Location> getLocationsBySightingID(int sightingID);

}
