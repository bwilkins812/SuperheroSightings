/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.model.Superpower;
import com.sg.superherosightings.servicelayer.SuperheroService;
import com.sg.superherosightings.servicelayer.SuperpowerService;
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
@Controller("/superpower")
public class SuperpowerController {

    SuperpowerService superpowerService;
    SuperheroService superheroService;

    @Inject
    public SuperpowerController(SuperpowerService superpowerService, SuperheroService superheroService) {
        this.superpowerService = superpowerService;
        this.superheroService = superheroService;
    }

    @RequestMapping(value = "/displaySuperpowersPage", method = RequestMethod.GET)
    public String displaySuperpowersPage(Model model) {

        List<Superpower> superpowerList = superpowerService.getAllSuperpowers();
        model.addAttribute("superpowerList", superpowerList);

        return "superpowers";
    }

    @RequestMapping(value = "/createSuperpower", method = RequestMethod.POST)
    public String createSuperpower(HttpServletRequest request) {

        Superpower superpower = new Superpower();
        superpower.setPowerName(request.getParameter("powerName"));
        superpower.setPowerDescription(request.getParameter("powerDescription"));

        superpowerService.addSuperpower(superpower);

        return "redirect:displaySuperpowersPage";
    }

    @RequestMapping(value = "/displaySuperpowerDetails", method = RequestMethod.GET)
    public String displaySuperpowerDetails(HttpServletRequest request, Model model) {
        String superpowerIDParameter = request.getParameter("superpowerID");
        int superpowerID = Integer.parseInt(superpowerIDParameter);

        Superpower superpower = superpowerService.getSuperpowersByID(superpowerID);
        List<Superhero> superheroList = superheroService.getSuperheroesBySuperpowerID(superpowerID);

        model.addAttribute("superpower", superpower);
        model.addAttribute("superheroList", superheroList);

        return "superpowerDetails";
    }

    @RequestMapping(value = "/deleteSuperpower", method = RequestMethod.GET)
    public String deleteSuperpower(HttpServletRequest request) {
        String superpowerIDParameter = request.getParameter("superpowerID");
        long longSuperpowerID = Long.parseLong(superpowerIDParameter);
        int superpowerID = (int) longSuperpowerID;
        superpowerService.deleteSuperpower(superpowerID);
        //superheroService.deleteSuperpowerFromSuperhero(superpowerID);
        return "redirect:displaySuperpowersPage";
    }

    @RequestMapping(value = "/displayEditSuperpowerForm", method = RequestMethod.GET)
    public String displayEditSuperpowerForm(HttpServletRequest request, Model model) {
        String superpowerIDParameter = request.getParameter("superpowerID");
        long longSuperpowerID = Long.parseLong(superpowerIDParameter);
        int superpowerID = (int) longSuperpowerID;
        Superpower superpower = superpowerService.getSuperpowersByID(superpowerID);
        model.addAttribute("superpower", superpower);
        return "editSuperpowerForm";
    }

    @RequestMapping(value = "/editSuperpower", method = RequestMethod.POST)
    public String editSuperpower(@Valid @ModelAttribute("superpower") Superpower superpower, BindingResult result) {

        if (result.hasErrors()) {
            return "editSuperpowerForm";
        }
        superpowerService.updateSuperpower(superpower);

        return "redirect:displaySuperpowersPage";
    }

}
