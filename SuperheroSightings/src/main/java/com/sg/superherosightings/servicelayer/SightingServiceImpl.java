/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.model.Sighting;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brettwilkins
 */
public class SightingServiceImpl implements SightingService {

    private SightingDao sightingDao;

    public SightingServiceImpl(SightingDao sightingDao) {
        this.sightingDao = sightingDao;

    }

    @Override
    public void addSighting(Sighting sighting) {
        sightingDao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingID) {
        sightingDao.deleteSighting(sightingID);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }

    @Override
    public Sighting getSightingsByID(int id) {
        return sightingDao.getSightingsByID(id);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }

    @Override
    public List<Sighting> getRecentSightings() {
        return sightingDao.getRecentSightings();
    }

}
