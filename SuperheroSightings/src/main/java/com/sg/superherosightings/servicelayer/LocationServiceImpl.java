/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.model.Location;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brettwilkins
 */
public class LocationServiceImpl implements LocationService {

    private LocationDao locationDao;

    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;

    }

    @Override
    public void addLocation(Location location) {
        locationDao.addLocation(location);
    }

    @Override
    public void deleteLocation(int locationID) {
        locationDao.deleteLocation(locationID);
    }

    @Override
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
    }

    @Override
    public Location getLocationByID(int id) {
        return locationDao.getLocationByID(id);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public List<Location> getLocationsBySightingID(int sightingID) {
        return locationDao.getLocationsBySightingID(sightingID);
    }

}
