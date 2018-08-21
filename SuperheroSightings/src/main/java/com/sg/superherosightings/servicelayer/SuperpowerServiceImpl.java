/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.dao.SuperpowerDao;
import com.sg.superherosightings.model.Superpower;
import java.util.List;

/**
 *
 * @author brettwilkins
 */
public class SuperpowerServiceImpl implements SuperpowerService {

    private SuperpowerDao superpowerDao;

    public SuperpowerServiceImpl(SuperpowerDao superpowerDao) {
        this.superpowerDao = superpowerDao;

    }

    @Override
    public void addSuperpower(Superpower superpower) {
        superpowerDao.addSuperpower(superpower);
    }

    @Override
    public void deleteSuperpower(int superpowerID) {
        superpowerDao.deleteSuperpower(superpowerID);
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        superpowerDao.updateSuperpower(superpower);
    }

    @Override
    public Superpower getSuperpowersByID(int id) {
        return superpowerDao.getSuperpowersByID(id);
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return superpowerDao.getAllSuperpowers();
    }

}
