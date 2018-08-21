/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.model.Organization;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brettwilkins
 */
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationDao organizationDao;

    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public void addOrganization(Organization organization) {
        organizationDao.addOrganization(organization);
    }

    @Override
    public void deleteOrganization(int organizationID) {
        organizationDao.deleteOrganization(organizationID);
    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationDao.updateOrganization(organization);
    }

    @Override
    public Organization getOrganizationByID(int orgID) {
        return organizationDao.getOrganizationByID(orgID);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }

}
