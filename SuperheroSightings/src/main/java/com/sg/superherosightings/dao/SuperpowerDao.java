/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Superpower;
import java.util.List;

/**
 *
 * @author brettwilkins
 */
public interface SuperpowerDao {

    public void addSuperpower(Superpower superpower);

    public void deleteSuperpower(int superpowerID);

    public void updateSuperpower(Superpower superpower);

    public Superpower getSuperpowersByID(int id);

    public List<Superpower> getAllSuperpowers();

}
