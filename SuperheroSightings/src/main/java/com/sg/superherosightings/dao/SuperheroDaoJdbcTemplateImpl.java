/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.mappers.OrganizationMapper;
import com.sg.superherosightings.mappers.SightingMapper;
import com.sg.superherosightings.mappers.SuperheroMapper;
import com.sg.superherosightings.mappers.SuperpowerMapper;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
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
public class SuperheroDaoJdbcTemplateImpl implements SuperheroDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_SUPERHERO
            = "insert into Superheroes (SuperheroName, SuperheroDescription) values (?, ?)";

    private static final String SQL_INSERT_SUPERHEROES_ORGANIZATIONS
            = "insert into SuperheroesOrganizations (SuperheroID, OrgID) values (?, ?)";

    private static final String SQL_INSERT_SUPERHEROES_SIGHTINGS
            = "insert into SuperheroesSightings (SuperheroID, SightingID) values (?, ?)";

    private static final String SQL_INSERT_SUPERHEROES_SUPERPOWERS
            = "insert into SuperheroesSuperpowers (SuperheroID, SuperpowerID) values (?, ?)";

    private static final String SQL_DELETE_SUPERHERO
            = "delete from Superheroes where SuperheroID = ?";

    private static final String SQL_DELETE_SUPERHEROES_ORGANIZATIONS
            = "delete from SuperheroesOrganizations where SuperheroID = ?";

    private static final String SQL_DELETE_SUPERHEROES_SIGHTINGS
            = "delete from SuperheroesSightings where SuperheroID = ?";

    private static final String SQL_DELETE_SUPERHEROES_SUPERPOWERS
            = "delete from SuperheroesSuperpowers where SuperheroID = ?";

    private static final String SQL_DELETE_SUPERHEROES_SUPERPOWERS_BY_SUPERPOWER_ID
            = "delete from SuperheroesSuperpowers where SuperpowerID = ?";

    private static final String SQL_DELETE_SUPERHEROES_ORGANIZATIONS_BY_ORG_ID
            = "delete from SuperheroesOrganizations where OrgID = ?";

    private static final String SQL_UPDATE_SUPERHERO
            = "update Superheroes set SuperheroName = ?, SuperheroDescription = ? where SuperheroID = ?";

    private static final String SQL_SELECT_SUPERHERO
            = "select * from Superheroes where SuperheroID = ?";

    private static final String SQL_SELECT_ALL_SUPERHEROES
            = "select * from Superheroes";

    private static final String SQL_SELECT_SUPERHEROES_BY_ORGANIZATION_ID
            = "select sh.SuperheroID, sh.SuperheroName, sh.SuperheroDescription from Superheroes sh "
            + "join SuperheroesOrganizations shorg on OrgID where sh.SuperheroID = shorg.SuperheroID "
            + "and shorg.OrgID = ?";

    private static final String SQL_SELECT_SUPERHEROES_BY_SUPERPOWERS_ID
            = "select sh.SuperheroID, sh.SuperheroName, sh.SuperheroDescription from Superheroes sh "
            + "join SuperheroesSuperpowers shsp on SuperpowerID where sh.SuperheroID = shsp.SuperheroID "
            + "and shsp.SuperpowerID = ?";

    private static final String SQL_SELECT_SUPERHEROES_BY_SIGHTING_ID
            = "select sh.SuperheroID, sh.SuperheroName, sh.SuperheroDescription from Superheroes sh "
            + "join SuperheroesSightings shsi on SightingID where sh.SuperheroID = shsi.SuperheroID "
            + "and shsi.SightingID = ?";

    private static final String SQL_SELECT_SIGHTINGS_BY_SUPERHERO_ID
            = "select s.SightingID, s.SightingName, s.SightingDate from Sightings s "
            + "join SuperheroesSightings shsi on s.SightingID = shsi.SightingID where shsi.SuperheroID = ?";

    private static final String SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID
            = "select org.OrgID, org.OrgName, org.OrgDescription, org.OrgAddress from Organizations org "
            + "join SuperheroesOrganizations shorg on org.OrgID = shorg.OrgID where shorg.SuperheroID = ?";

    private static final String SQL_SELECT_SUPERPOWERS_BY_SUPERHERO_ID
            = "select sp.SuperpowerID, sp.PowerName, sp.PowerDescription from Superpowers sp "
            + "join SuperheroesSuperpowers shsp on sp.SuperpowerID = shsp.SuperpowerID where shsp.SuperheroID = ?";

    //Add a Superhero
    //===============
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperhero(Superhero superhero) {

        jdbcTemplate.update(SQL_INSERT_SUPERHERO,
                superhero.getSuperheroName(),
                superhero.getSuperheroDescription());

        superhero.setSuperheroID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        insertSuperheroesOrganizations(superhero);
        insertSuperheroesSuperpowers(superhero);
        insertSuperheroesSightings(superhero);

    }

    //Adding a Superhero Helper Methods
    //========================
    private void insertSuperheroesOrganizations(Superhero superhero) {
        final int superheroID = superhero.getSuperheroID();
        final List<Organization> organizations = superhero.getOrganizations();

        for (Organization currentOrganization : organizations) {
            jdbcTemplate.update(SQL_INSERT_SUPERHEROES_ORGANIZATIONS, superheroID, currentOrganization.getOrgID());
        }
    }

    private void insertSuperheroesSuperpowers(Superhero superhero) {
        final int superheroID = superhero.getSuperheroID();
        final List<Superpower> superpowers = superhero.getSuperpowers();

        for (Superpower currentSuperpower : superpowers) {
            jdbcTemplate.update(SQL_INSERT_SUPERHEROES_SUPERPOWERS, superheroID, currentSuperpower.getSuperpowerID());
        }
    }

    @Override
    public void insertMoreSuperpowers(int superheroID, int superpowerID) {
        jdbcTemplate.update(SQL_INSERT_SUPERHEROES_SUPERPOWERS, superheroID, superpowerID);
    }

    @Override
    public void insertMoreOrganizations(int superheroID, int orgID) {
        jdbcTemplate.update(SQL_INSERT_SUPERHEROES_ORGANIZATIONS, superheroID, orgID);
    }

    @Override
    public void insertMoreSightings(int superheroID, int sightingID) {
        jdbcTemplate.update(SQL_INSERT_SUPERHEROES_SIGHTINGS, superheroID, sightingID);
    }

    private void insertSuperheroesSightings(Superhero superhero) {
        final int superheroID = superhero.getSuperheroID();
        final List<Sighting> sightings = superhero.getSuperheroSightings();

        for (Sighting currentSighting : sightings) {
            jdbcTemplate.update(SQL_INSERT_SUPERHEROES_SIGHTINGS, superheroID, currentSighting.getSightingID());
        }
    }

    //Delete a Superhero
    //==================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperhero(int superheroID) {
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_ORGANIZATIONS, superheroID);
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_SIGHTINGS, superheroID);
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_SUPERPOWERS, superheroID);
        jdbcTemplate.update(SQL_DELETE_SUPERHERO, superheroID);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperpowerFromSuperhero(int superpowerID) {

        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_SUPERPOWERS_BY_SUPERPOWER_ID, superpowerID);

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganizationFromSuperhero(int orgID) {

        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_ORGANIZATIONS_BY_ORG_ID, orgID);

    }

    //Update a Superhero
    //==================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuperhero(Superhero superhero) {
        jdbcTemplate.update(SQL_UPDATE_SUPERHERO,
                superhero.getSuperheroName(),
                superhero.getSuperheroDescription(),
                superhero.getSuperheroID());

        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_ORGANIZATIONS, superhero.getSuperheroID());
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_SIGHTINGS, superhero.getSuperheroID());
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_SUPERPOWERS, superhero.getSuperheroID());
        insertSuperheroesOrganizations(superhero);
        insertSuperheroesSightings(superhero);
        insertSuperheroesSuperpowers(superhero);
    }

    //Get a Superhero and get ALL Superheroes
    //=======================================
    @Override
    public Superhero getSuperheroByID(int superheroID) {
        try {
            Superhero superhero = jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO, new SuperheroMapper(), superheroID);
            superhero.setOrganizations(findOrganizationsForSuperhero(superhero));
            superhero.setSuperheroSightings(findSightingsForSuperhero(superhero));
            superhero.setSuperpowers(findSuperpowersForSuperhero(superhero));

            return superhero;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superhero> getSuperheroesByOrganizationID(int orgID) {
        List<Superhero> superheroList = jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_ORGANIZATION_ID, new SuperheroMapper(), orgID);
        return associateSightingsAndOrganizationsAndSuperpowersWithSuperhero(superheroList);
    }

    @Override
    public List<Superhero> getSuperheroesBySuperpowerID(int superpowerID) {
        List<Superhero> superheroList = jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_SUPERPOWERS_ID, new SuperheroMapper(), superpowerID);
        return associateSightingsAndOrganizationsAndSuperpowersWithSuperhero(superheroList);
    }

    @Override
    public List<Superhero> getSuperheroesSightingID(int sightingID) {
        List<Superhero> superheroList = jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_SIGHTING_ID, new SuperheroMapper(), sightingID);
        return associateSightingsAndOrganizationsAndSuperpowersWithSuperhero(superheroList);
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        List<Superhero> superheroList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROES, new SuperheroMapper());
        return associateSightingsAndOrganizationsAndSuperpowersWithSuperhero(superheroList);
    }

    //Get a Superhero and get ALL Superheroes Helper Methods
    //======================================================
    private List<Organization> findOrganizationsForSuperhero(Superhero superhero) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID, new OrganizationMapper(), superhero.getSuperheroID());
    }

    private List<Sighting> findSightingsForSuperhero(Superhero superhero) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_SUPERHERO_ID, new SightingMapper(), superhero.getSuperheroID());
    }

    private List<Superpower> findSuperpowersForSuperhero(Superhero superhero) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_SUPERHERO_ID, new SuperpowerMapper(), superhero.getSuperheroID());
    }

    private List<Superhero> associateSightingsAndOrganizationsAndSuperpowersWithSuperhero(List<Superhero> superheroList) {
        for (Superhero currentSuperhero : superheroList) {
            currentSuperhero.setOrganizations(findOrganizationsForSuperhero(currentSuperhero));
            currentSuperhero.setSuperpowers(findSuperpowersForSuperhero(currentSuperhero));
            currentSuperhero.setSuperheroSightings(findSightingsForSuperhero(currentSuperhero));
        }
        return superheroList;
    }

}
