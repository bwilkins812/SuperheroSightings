/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.mappers;

import com.sg.superherosightings.model.Superhero;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author brettwilkins
 */
public class SuperheroMapper implements RowMapper<Superhero> {

    @Override
    public Superhero mapRow(ResultSet rs, int i) throws SQLException {
        Superhero hero = new Superhero();
        hero.setSuperheroID(rs.getInt("SuperheroID"));
        hero.setSuperheroName(rs.getString("SuperheroName"));
        hero.setSuperheroDescription(rs.getString("SuperheroDescription"));

        return hero;
    }
}
