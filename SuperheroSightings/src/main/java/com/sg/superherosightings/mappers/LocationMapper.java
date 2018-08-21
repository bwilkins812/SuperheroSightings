/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.mappers;

import com.sg.superherosightings.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author brettwilkins
 */
public class LocationMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int i) throws SQLException {
        Location loc = new Location();
        loc.setLocationID(rs.getInt("LocationID"));
        loc.setLocationName(rs.getString("LocationName"));
        loc.setLocationDescription(rs.getString("LocationDescription"));
        loc.setAddress(rs.getString("Address"));
        loc.setLongitude(rs.getBigDecimal("Longitude"));
        loc.setLatitude(rs.getBigDecimal("Latitude"));

        return loc;

    }

}
