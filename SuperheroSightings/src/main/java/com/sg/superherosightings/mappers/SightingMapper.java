/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.mappers;

import com.sg.superherosightings.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author brettwilkins
 */
public class SightingMapper implements RowMapper<Sighting> {

    @Override
    public Sighting mapRow(ResultSet rs, int i) throws SQLException {
        Sighting sighting = new Sighting();
        sighting.setSightingID(rs.getInt("SightingID"));
        sighting.setSightingName(rs.getString("SightingName"));
        sighting.setSightingDate(rs.getTimestamp("SightingDate").toLocalDateTime().toLocalDate());

        return sighting;

    }
}
