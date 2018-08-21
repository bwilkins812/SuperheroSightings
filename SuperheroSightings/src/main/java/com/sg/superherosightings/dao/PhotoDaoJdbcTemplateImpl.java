/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.mappers.ProfilePhotoMapper;
import com.sg.superherosightings.mappers.SightingPhotoMapper;
import com.sg.superherosightings.model.ProfilePhoto;
import com.sg.superherosightings.model.SightingPhoto;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brettwilkins
 */
public class PhotoDaoJdbcTemplateImpl implements PhotoDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_PROFILE_PHOTO
            = "insert into ProfilePhoto (Title, Filename) values (?, ?)";

    private static final String SQL_INSERT_SIGHTING_PHOTO
            = "insert into SightingPhoto (Title, Filename) values (?, ?)";

    private static final String SQL_SELECT_ALL_PROFILE_PHOTOS
            = "select * from ProfilePhoto";

    private static final String SQL_SELECT_ALL_SIGHTING_PHOTOS
            = "select * from SightingPhoto";

    private static final String SQL_INSERT_SUPERHEROES_PROFILE_PHOTO
            = "insert into SuperheroesProfilePhoto (SuperheroID, ProfilePhotoID) values (?, ?)";

    private static final String SQL_INSERT_SUPERHEROES_SIGHTING_PHOTO
            = "insert into SuperheroesSightingPhoto (SightingID, SightingPhotoID) values (?, ?)";

    private static final String SQL_SELECT_PROFILE_PHOTO_BY_SUPERHERO_ID
            = "select pp.ProfilePhotoID, pp.Title, pp.Filename from ProfilePhoto pp "
            + "join SuperheroesProfilePhoto spp on pp.ProfilePhotoID = spp.ProfilePhotoID where spp.SuperheroID = ?";

    private static final String SQL_SELECT_SIGHTING_PHOTO_BY_SIGHTING_ID
            = "select sp.SightingPhotoID, sp.Title, sp.Filename from SightingPhoto sp "
            + "join SuperheroesSightingPhoto ssp on sp.SightingPhotoID = ssp.SightingPhotoID where ssp.SightingID = ?";

    //Methods for Profile Photo
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addProfilePhoto(ProfilePhoto profilePhoto, int superheroID) {

        jdbcTemplate.update(SQL_INSERT_PROFILE_PHOTO,
                profilePhoto.getTitle(),
                profilePhoto.getFilename());

        int profilePhotoID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        profilePhoto.setProfilePhotoID(profilePhotoID);
        insertSuperheroesProfilePhoto(profilePhoto, superheroID);
    }

    private void insertSuperheroesProfilePhoto(ProfilePhoto profilePhoto, int superheroID) {
        final int profilePhotoID = profilePhoto.getProfilePhotoID();

        jdbcTemplate.update(SQL_INSERT_SUPERHEROES_PROFILE_PHOTO, superheroID, profilePhotoID);
    }

    @Override
    public List<ProfilePhoto> getAllProfilePhotos() {
        List<ProfilePhoto> profilePhotoList = jdbcTemplate.query(SQL_SELECT_ALL_PROFILE_PHOTOS, new ProfilePhotoMapper());
        return profilePhotoList;
    }

    @Override
    public ProfilePhoto getProfilePhotoForSuperhero(int superheroID) {

        try {
            ProfilePhoto profilePhoto = jdbcTemplate.queryForObject(SQL_SELECT_PROFILE_PHOTO_BY_SUPERHERO_ID, new ProfilePhotoMapper(), superheroID);

            return profilePhoto;
        } catch (EmptyResultDataAccessException ex) {
            return null;

        }

    }

    //Methods for Sighting Photo
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSightingPhoto(SightingPhoto sightingPhoto, int sightingID) {

        jdbcTemplate.update(SQL_INSERT_SIGHTING_PHOTO,
                sightingPhoto.getTitle(),
                sightingPhoto.getFilename());

        int sightingPhotoID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        sightingPhoto.setSightingPhotoID(sightingPhotoID);
        insertSuperheroesSightingPhoto(sightingPhoto, sightingID);
    }

    private void insertSuperheroesSightingPhoto(SightingPhoto sightingPhoto, int superheroID) {
        final int sightingPhotoID = sightingPhoto.getSightingPhotoID();

        jdbcTemplate.update(SQL_INSERT_SUPERHEROES_SIGHTING_PHOTO, superheroID, sightingPhotoID);
    }

    @Override
    public List<SightingPhoto> getAllSightingPhotos() {
        List<SightingPhoto> sightingPhotoList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTING_PHOTOS, new SightingPhotoMapper());
        return sightingPhotoList;
    }

    @Override
    public SightingPhoto getSightingPhotoForSighting(int sightingID) {

        try {
            SightingPhoto sightingPhoto = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_PHOTO_BY_SIGHTING_ID, new SightingPhotoMapper(), sightingID);

            return sightingPhoto;
        } catch (EmptyResultDataAccessException ex) {
            return null;

        }

    }

    //REST Methods
    @Override
    public void addRESTPicture(SightingPhoto sightingPhoto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
