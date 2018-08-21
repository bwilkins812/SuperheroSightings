/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.model.Organization;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brettwilkins
 */
public interface OrganizationService {

    public void addOrganization(Organization organization);

    public void deleteOrganization(int organizationID);

    public void updateOrganization(Organization organization);

    public Organization getOrganizationByID(int orgID);

    public List<Organization> getAllOrganizations();

}
