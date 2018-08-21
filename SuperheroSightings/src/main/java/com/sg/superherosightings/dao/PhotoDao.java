/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.ProfilePhoto;
import com.sg.superherosightings.model.SightingPhoto;
import java.util.List;

/**
 *
 * @author brettwilkins
 */
public interface PhotoDao {

    //Methods for Profile Photos
    public void addProfilePhoto(ProfilePhoto profilePhoto, int superheroID);

    public ProfilePhoto getProfilePhotoForSuperhero(int superheroID);

    public List<ProfilePhoto> getAllProfilePhotos();

    //Methods for SightingPhotos
    public void addSightingPhoto(SightingPhoto sightingPhoto, int sightingID);

    public SightingPhoto getSightingPhotoForSighting(int sightingID);

    public List<SightingPhoto> getAllSightingPhotos();

    //REST methods
    public void addRESTPicture(SightingPhoto sightingPhoto);

}
