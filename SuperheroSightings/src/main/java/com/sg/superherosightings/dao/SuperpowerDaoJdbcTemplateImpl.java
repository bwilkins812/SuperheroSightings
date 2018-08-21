/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.mappers.SuperpowerMapper;
import com.sg.superherosightings.model.Superpower;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brettwilkins
 */
public class SuperpowerDaoJdbcTemplateImpl implements SuperpowerDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_SUPERPOWER
            = "insert into Superpowers (PowerName, PowerDescription) values (?, ?)";

    private static final String SQL_DELETE_SUPERPOWER
            = "delete from Superpowers where SuperpowerID = ?";

    private static final String SQL_DELETE_SUPERHEROES_SUPERPOWERS_BY_SUPERPOWER_ID
            = "delete from SuperheroesSuperpowers where SuperpowerID = ?";

    private static final String SQL_UPDATE_SUPERPOWER
            = "update Superpowers set PowerName = ?, PowerDescription = ? where SuperpowerID = ?";

    private static final String SQL_SELECT_SUPERPOWER
            = "select * from Superpowers where SuperpowerID = ?";

    private static final String SQL_SELECT_ALL_SUPERPOWERS
            = "select * from Superpowers";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperpower(Superpower superpower) {

        jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                superpower.getPowerName(),
                superpower.getPowerDescription());

        int superpowerID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        superpower.setSuperpowerID(superpowerID);

    }

    @Override
    public void deleteSuperpower(int superpowerID) {
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_SUPERPOWERS_BY_SUPERPOWER_ID, superpowerID);
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, superpowerID);

    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER,
                superpower.getPowerName(),
                superpower.getPowerDescription(),
                superpower.getSuperpowerID());

    }

    @Override
    public Superpower getSuperpowersByID(int superpowerID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER, new SuperpowerMapper(), superpowerID);

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWERS, new SuperpowerMapper());

    }

}
