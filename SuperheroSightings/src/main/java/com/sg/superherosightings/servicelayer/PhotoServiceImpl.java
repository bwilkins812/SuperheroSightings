/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.dao.PhotoDao;
import com.sg.superherosightings.model.ProfilePhoto;
import com.sg.superherosightings.model.SightingPhoto;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author brettwilkins
 */
public class PhotoServiceImpl implements PhotoService {

    private PhotoDao photoDao;
    public static final String pictureFolder = "images/";

    public PhotoServiceImpl(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    //Profile Photo Methods
    @Override
    public String addProfilePhoto(HttpServletRequest request, Model model, String displayTitle, MultipartFile pictureFile, int superheroID) {
        String message;

        if (!pictureFile.isEmpty()) {
            try {

                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + pictureFolder;
                File dir = new File(savePath);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String filename = pictureFile.getOriginalFilename();
                pictureFile.transferTo(new File(savePath + filename));

                ProfilePhoto profilePhoto = new ProfilePhoto();
                profilePhoto.setFilename(pictureFolder + filename);
                profilePhoto.setTitle(displayTitle);
                photoDao.addProfilePhoto(profilePhoto, superheroID);
                message = "redirect:pictureGallery";
                return message;
            } catch (Exception e) {

                model.addAttribute("errorMsg", "File upload failed: "
                        + e.getMessage());
                message = "addPictureForm";
                return message;
            }
        } else {

            model.addAttribute("errorMsg",
                    "Please specify a non-empty file.");
            message = "pictureGallery";
            return message;
        }

    }

    @Override
    public ProfilePhoto getProfilePhotoForSuperhero(int superheroID) {
        return photoDao.getProfilePhotoForSuperhero(superheroID);
    }

    @Override
    public List<ProfilePhoto> getAllProfilePhotos() {
        return photoDao.getAllProfilePhotos();
    }

    //Sighting Photo Methods
    @Override
    public String addSightingPhoto(HttpServletRequest request, Model model, String displayTitle, MultipartFile pictureFile, int sightingID) {
        
        String message;
        //int sightingID = Integer.parseInt(request.getParameter("sighting"));
        
        if (!pictureFile.isEmpty()) {
            try {
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + pictureFolder;
                File dir = new File(savePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String filename = pictureFile.getOriginalFilename();
                pictureFile.transferTo(new File(savePath + filename));

                SightingPhoto sightingPhoto = new SightingPhoto();
                sightingPhoto.setFilename(pictureFolder + filename);
                sightingPhoto.setTitle(displayTitle);
                sightingPhoto.setSightingPhotoID(sightingID);
                photoDao.addSightingPhoto(sightingPhoto, sightingID);
                message = "redirect:pictureGallery";

                return message;
            } catch (Exception e) {

                model.addAttribute("errorMsg", "File upload failed: "
                        + e.getMessage());
                message = "addPictureForm";
                return message;
            }
        } else {

            model.addAttribute("errorMsg",
                    "Please specify a non-empty file.");
            message = "pictureGallery";
            return message;
        }

    }

    @Override
    public SightingPhoto getSightingPhotoForSighting(int sightingID) {
        return photoDao.getSightingPhotoForSighting(sightingID);
    }

    @Override
    public List<SightingPhoto> getAllSightingPhotos() {
        return photoDao.getAllSightingPhotos();
    }

    //REST Methods
    @Override
    public void addRESTPicture(SightingPhoto sightingPhoto) {
        photoDao.addRESTPicture(sightingPhoto);
    }

}
