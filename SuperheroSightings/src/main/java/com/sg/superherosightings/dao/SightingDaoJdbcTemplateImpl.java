/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.mappers.SightingMapper;
import com.sg.superherosightings.model.Sighting;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brettwilkins
 */
public class SightingDaoJdbcTemplateImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_SIGHTING
            = "insert into Sightings (SightingName, SightingDate) values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from Sightings where SightingID = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update Sightings set SightingName = ?, SightingDate = ? where SightingID = ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from Sightings where SightingID = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from Sightings";

    private static final String SQL_SELECT_RECENT_SIGHTINGS
            = "select * from Sightings order by SightingDate desc limit 10";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {

        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getSightingName(),
                sighting.getSightingDate().toString());

        int sightingID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        sighting.setSightingID(sightingID);

    }

    @Override
    public void deleteSighting(int sightingID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingID);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getSightingName(),
                sighting.getSightingDate().toString(),
                sighting.getSightingID());

    }

    @Override
    public Sighting getSightingsByID(int sightingID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, new SightingMapper(), sightingID);

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());

    }

    @Override
    public List<Sighting> getRecentSightings() {
        return jdbcTemplate.query(SQL_SELECT_RECENT_SIGHTINGS, new SightingMapper());
    }

}
