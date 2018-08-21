/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.RESTcontroller;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.model.Organization;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author brettwilkins
 */
@CrossOrigin
@Controller
public class RESTOrganizationController {

    private OrganizationDao organizationDao;

    @Inject
    public RESTOrganizationController(OrganizationDao locationDao) {
        this.organizationDao = organizationDao;
    }

    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Organization getOrganization(@PathVariable("organizationID") int organizationID) {
        return organizationDao.getOrganizationByID(organizationID);
    }

    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addOrganization(@Valid @RequestBody Organization organization) {
        organizationDao.addOrganization(organization);
    }

    @RequestMapping(value = "/organization/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("organizationID") int organizationID) {
        organizationDao.deleteOrganization(organizationID);
    }

    @RequestMapping(value = "/organization/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrganization(@PathVariable("organizationID") int organizationID,
            @Valid @RequestBody Organization organization) throws UpdateIntegrityException {

        if (organizationID != organization.getOrgID()) {
            throw new UpdateIntegrityException("Organization ID on URL must match Organization ID in submitted data.");
        }
        organizationDao.updateOrganization(organization);
    }

    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    @ResponseBody
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }

}
