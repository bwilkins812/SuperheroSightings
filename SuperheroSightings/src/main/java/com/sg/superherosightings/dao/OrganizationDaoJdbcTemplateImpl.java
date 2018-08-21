/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.mappers.OrganizationMapper;
import com.sg.superherosightings.mappers.SuperheroMapper;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Superhero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brettwilkins
 */
public class OrganizationDaoJdbcTemplateImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_ORGANIZATION
            = "insert into Organizations (OrgName, OrgDescription, OrgAddress) values (?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from Organizations where OrgID = ?";

    private static final String SQL_DELETE_SUPERHEROES_ORGANIZATIONS_BY_ORG_ID
            = "delete from SuperheroesOrganizations where OrgID = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update Organizations set OrgName = ?, OrgDescription = ?, OrgAddress = ? where OrgID = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from Organizations where OrgID = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from Organizations";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization org) {

        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                org.getOrgName(),
                org.getOrgDescription(),
                org.getOrgAddress());

        int orgID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        org.setOrgID(orgID);

    }

    @Override
    public void deleteOrganization(int orgID) {
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_ORGANIZATIONS_BY_ORG_ID, orgID);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, orgID);
    }

    @Override
    public void updateOrganization(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                org.getOrgName(),
                org.getOrgDescription(),
                org.getOrgAddress(),
                org.getOrgID());

    }

    @Override
    public Organization getOrganizationByID(int orgID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), orgID);

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());

    }

}
