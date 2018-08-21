/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.model.ProfilePhoto;
import com.sg.superherosightings.model.SightingPhoto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author brettwilkins
 */
public interface PhotoService {

    //Profile Photo Methods
    public String addProfilePhoto(HttpServletRequest request, Model model, String displayTitle, MultipartFile pictureFile, int superheroID);
    
    public ProfilePhoto getProfilePhotoForSuperhero(int superheroID);

    public List<ProfilePhoto> getAllProfilePhotos();

    //Sighting Photo Methods
    public String addSightingPhoto(HttpServletRequest request, Model model, String displayTitle, MultipartFile pictureFile, int sightingID);
    
    public SightingPhoto getSightingPhotoForSighting(int sightingID);

    public List<SightingPhoto> getAllSightingPhotos();

    //REST Methods
    public void addRESTPicture(SightingPhoto sightingPhoto);

}
