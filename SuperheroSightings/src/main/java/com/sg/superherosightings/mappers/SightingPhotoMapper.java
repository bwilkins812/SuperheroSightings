/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.mappers;

import com.sg.superherosightings.model.SightingPhoto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author brettwilkins
 */
public class SightingPhotoMapper implements RowMapper<SightingPhoto> {

    @Override
    public SightingPhoto mapRow(ResultSet rs, int i) throws SQLException {
        SightingPhoto sightingPhoto = new SightingPhoto();
        sightingPhoto.setSightingPhotoID(rs.getInt("SightingPhotoID"));
        sightingPhoto.setTitle(rs.getString("Title"));
        sightingPhoto.setFilename(rs.getString("Filename"));

        return sightingPhoto;

    }
}
