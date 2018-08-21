/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.mappers;

import com.sg.superherosightings.model.ProfilePhoto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author brettwilkins
 */
public class ProfilePhotoMapper implements RowMapper<ProfilePhoto> {

    @Override
    public ProfilePhoto mapRow(ResultSet rs, int i) throws SQLException {
        ProfilePhoto profilePhoto = new ProfilePhoto();
        profilePhoto.setProfilePhotoID(rs.getInt("ProfilePhotoID"));
        profilePhoto.setTitle(rs.getString("Title"));
        profilePhoto.setFilename(rs.getString("Filename"));

        return profilePhoto;

    }
}
