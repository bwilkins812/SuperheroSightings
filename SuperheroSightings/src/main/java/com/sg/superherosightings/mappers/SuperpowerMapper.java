/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.mappers;

import com.sg.superherosightings.model.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author brettwilkins
 */
public class SuperpowerMapper implements RowMapper<Superpower> {

    @Override
    public Superpower mapRow(ResultSet rs, int i) throws SQLException {
        Superpower power = new Superpower();
        power.setSuperpowerID(rs.getInt("SuperpowerID"));
        power.setPowerName(rs.getString("PowerName"));
        power.setPowerDescription(rs.getString("PowerDescription"));

        return power;

    }
}
