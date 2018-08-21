/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.servicelayer.OrganizationService;
import com.sg.superherosightings.servicelayer.SuperheroService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author brettwilkins
 */
@Controller("/organization")
public class OrganizationController {

    OrganizationService organizationService;
    SuperheroService superheroService;

    @Inject
    public OrganizationController(OrganizationService organizationService, SuperheroService superheroService) {
        this.organizationService = organizationService;
        this.superheroService = superheroService;
    }

    @RequestMapping(value = "/displayOrganizationsPage", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {

        List<Organization> orgList = organizationService.getAllOrganizations();
        model.addAttribute("orgList", orgList);

        return "organizations";
    }

    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(HttpServletRequest request) {

        Organization org = new Organization();
        org.setOrgName(request.getParameter("orgName"));
        org.setOrgDescription(request.getParameter("orgDescription"));
        org.setOrgAddress(request.getParameter("orgAddress"));

        organizationService.addOrganization(org);

        return "redirect:displayOrganizationsPage";
    }

    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String orgIDParameter = request.getParameter("orgID");
        int orgID = Integer.parseInt(orgIDParameter);

        Organization org = organizationService.getOrganizationByID(orgID);
        List<Superhero> superheroes = superheroService.getSuperheroesByOrganizationID(orgID);

        model.addAttribute("organization", org);
        model.addAttribute("superheroes", superheroes);

        return "organizationDetails";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String orgIDParameter = request.getParameter("orgID");
        long longOrgID = Long.parseLong(orgIDParameter);
        int orgID = (int) longOrgID;
        organizationService.deleteOrganization(orgID);
        return "redirect:displayOrganizationsPage";
    }

    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String orgIDParameter = request.getParameter("orgID");
        int orgID = Integer.parseInt(orgIDParameter);
        Organization organization = organizationService.getOrganizationByID(orgID);
        model.addAttribute("organization", organization);
        return "editOrganizationsForm";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {

        if (result.hasErrors()) {
            return "editOrganizationsForm";
        }
        organizationService.updateOrganization(organization);

        return "redirect:displayOrganizationsPage";
    }

}
