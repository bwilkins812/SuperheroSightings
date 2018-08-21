/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.ProfilePhoto;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.model.Superpower;
import com.sg.superherosightings.servicelayer.OrganizationService;
import com.sg.superherosightings.servicelayer.PhotoService;
import com.sg.superherosightings.servicelayer.SightingService;
import com.sg.superherosightings.servicelayer.SuperheroService;
import com.sg.superherosightings.servicelayer.SuperpowerService;
import java.util.ArrayList;
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
@Controller("/superhero")
public class SuperheroController {

    SuperheroService superheroService;
    SuperpowerService superpowerService;
    SightingService sightingService;
    OrganizationService organizationService;
    PhotoService photoService;

    @Inject
    public SuperheroController(SuperheroService superheroService,
            SuperpowerService superpowerService,
            OrganizationService organizationService,
            SightingService sightingService,
            PhotoService photoService) {
        this.superheroService = superheroService;
        this.sightingService = sightingService;
        this.organizationService = organizationService;
        this.superpowerService = superpowerService;
        this.photoService = photoService;
    }

    @RequestMapping(value = "/displaySuperheroesPage", method = RequestMethod.GET)
    public String displaySuperheroesPage(Model model) {

        List<Superhero> superheroList = superheroService.getAllSuperheroes();
        List<Sighting> sightingList = sightingService.getAllSightings();
        List<Organization> organizationList = organizationService.getAllOrganizations();
        List<Superpower> superpowerList = superpowerService.getAllSuperpowers();

        model.addAttribute("superheroList", superheroList);
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("superpowerList", superpowerList);

        return "superheroes";

    }

    @RequestMapping(value = "/createSuperhero", method = RequestMethod.POST)
    public String createSuperhero(HttpServletRequest request) {

        Superhero superhero = new Superhero();
        superhero.setSuperheroName(request.getParameter("superheroName"));
        superhero.setSuperheroDescription(request.getParameter("superheroDescription"));

        String stringSightingID = request.getParameter("sighting");
        int sightingID = Integer.parseInt(stringSightingID);
        Sighting sighting = sightingService.getSightingsByID(sightingID);
        List<Sighting> sightings = new ArrayList();
        sightings.add(sighting);
        superhero.setSuperheroSightings(sightings);

        String stringOrgID = request.getParameter("organization");
        int orgID = Integer.parseInt(stringOrgID);
        Organization organization = organizationService.getOrganizationByID(orgID);
        List<Organization> organizations = new ArrayList();
        organizations.add(organization);
        superhero.setOrganizations(organizations);

        String stringSuperpowerID = request.getParameter("superpower");
        int superpowerID = Integer.parseInt(stringSuperpowerID);
        Superpower superpower = superpowerService.getSuperpowersByID(superpowerID);
        List<Superpower> superpowers = new ArrayList();
        superpowers.add(superpower);
        superhero.setSuperpowers(superpowers);

        superheroService.addSuperhero(superhero);

        return "redirect:displaySuperheroesPage";
    }

    @RequestMapping(value = "/addMultipleSuperpowers", method = RequestMethod.POST)
    public String addMultipleSuperpowers(HttpServletRequest request) {

        String stringSuperpowerID = request.getParameter("superpower");
        int superpowerID = Integer.parseInt(stringSuperpowerID);
        String stringSuperheroID = request.getParameter("superhero");
        int superheroID = Integer.parseInt(stringSuperheroID);

        superheroService.insertMoreSuperpowers(superheroID, superpowerID);

        return "redirect:displaySuperheroesPage";

    }

    @RequestMapping(value = "/addMultipleOrgs", method = RequestMethod.POST)
    public String addMultipleOrganizations(HttpServletRequest request) {

        String stringOrgID = request.getParameter("organization");
        int orgID = Integer.parseInt(stringOrgID);
        String stringSuperheroID = request.getParameter("superhero");
        int superheroID = Integer.parseInt(stringSuperheroID);

        superheroService.insertMoreOrganizations(superheroID, orgID);

        return "redirect:displaySuperheroesPage";

    }

    @RequestMapping(value = "/addMultipleSightings", method = RequestMethod.POST)
    public String addMultipleSightings(HttpServletRequest request) {

        String stringSightingID = request.getParameter("sighting");
        int sightingID = Integer.parseInt(stringSightingID);
        String stringSuperheroID = request.getParameter("superhero");
        int superheroID = Integer.parseInt(stringSuperheroID);

        superheroService.insertMoreSightings(superheroID, sightingID);

        return "redirect:displaySuperheroesPage";
    }

    @RequestMapping(value = "/displaySuperheroDetails", method = RequestMethod.GET)
    public String displaySuperheroDetails(HttpServletRequest request, Model model) {
        String superheroIDParameter = request.getParameter("superheroID");
        int superheroID = Integer.parseInt(superheroIDParameter);
        //int heroID = superheroID;

        Superhero superhero = superheroService.getSuperheroByID(superheroID);
        List<Superpower> superpowerList = superhero.getSuperpowers();
        List<Organization> organizationList = superhero.getOrganizations();
        List<Sighting> sightingList = superhero.getSuperheroSightings();
        //ProfilePhoto profilePhoto = photoService.getProfilePhotoBySuperheroID(superheroID);
        ProfilePhoto profilePhoto = photoService.getProfilePhotoForSuperhero(superheroID);

        model.addAttribute("superhero", superhero);
        model.addAttribute("superpowerList", superpowerList);
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("profilePhoto", profilePhoto);

        return "superheroDetails";
    }

    @RequestMapping(value = "/deleteSuperhero", method = RequestMethod.GET)
    public String deleteSuperhero(HttpServletRequest request) {
        String superheroIDParameter = request.getParameter("superheroID");
        long longSuperheroID = Long.parseLong(superheroIDParameter);
        int superheroID = (int) longSuperheroID;
        superheroService.deleteSuperhero(superheroID);
        return "redirect:displaySuperheroesPage";
    }

    @RequestMapping(value = "/displayEditSuperheroForm", method = RequestMethod.GET)
    public String displayEditSuperheroForm(HttpServletRequest request, Model model) {
        String superheroIDParameter = request.getParameter("superheroID");
        long longSuperheroID = Long.parseLong(superheroIDParameter);
        int superheroID = (int) longSuperheroID;
        Superhero superhero = superheroService.getSuperheroByID(superheroID);
        model.addAttribute("superhero", superhero);
        List<Sighting> sightingList = sightingService.getAllSightings();
        model.addAttribute("sightingList", sightingList);
        List<Organization> organizationList = organizationService.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        List<Superpower> superpowerList = superpowerService.getAllSuperpowers();
        model.addAttribute("superpowerList", superpowerList);
        return "editSuperheroesForm";
    }

    @RequestMapping(value = "/editSuperhero", method = RequestMethod.POST)
    public String editSuperhero(@Valid @ModelAttribute("superhero") Superhero superhero, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "editSuperheroesForm";
        }
        String stringSightingID = request.getParameter("sighting");
        int sightingID = Integer.parseInt(stringSightingID);
        Sighting sighting = sightingService.getSightingsByID(sightingID);
        List<Sighting> sightings = new ArrayList();
        sightings.add(sighting);
        superhero.setSuperheroSightings(sightings);

        String stringOrgID = request.getParameter("organization");
        int orgID = Integer.parseInt(stringOrgID);
        Organization organization = organizationService.getOrganizationByID(orgID);
        List<Organization> organizations = new ArrayList();
        organizations.add(organization);
        superhero.setOrganizations(organizations);

        String stringSuperpowerID = request.getParameter("superpower");
        int superpowerID = Integer.parseInt(stringSuperpowerID);
        Superpower superpower = superpowerService.getSuperpowersByID(superpowerID);
        List<Superpower> superpowers = new ArrayList();
        superpowers.add(superpower);
        superhero.setSuperpowers(superpowers);

        superheroService.updateSuperhero(superhero);

        return "redirect:displaySuperheroesPage";
    }

}
