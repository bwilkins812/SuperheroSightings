/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.mappers;

import com.sg.superherosightings.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author brettwilkins
 */
public class OrganizationMapper implements RowMapper<Organization> {

    @Override
    public Organization mapRow(ResultSet rs, int i) throws SQLException {
        Organization org = new Organization();
        org.setOrgName(rs.getString("OrgName"));
        org.setOrgDescription(rs.getString("OrgDescription"));
        org.setOrgAddress(rs.getString("OrgAddress"));
        org.setOrgID(rs.getInt("OrgID"));

        return org;

    }
}
