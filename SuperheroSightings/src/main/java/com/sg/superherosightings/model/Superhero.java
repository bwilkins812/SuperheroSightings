/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author brettwilkins
 */
public class Superhero {

    private int superheroID;

    @NotEmpty(message = "You must supply a value for Superhero Name.")
    @Length(max = 45, message = "Superhero Name must be no more than 45 characters in length.")
    private String superheroName;

    @NotEmpty(message = "You must supply a value for Superhero Description.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String superheroDescription;

    private List<Organization> organizations;
    private List<Superpower> superpowers;
    private List<Sighting> superheroSightings;

    public int getSuperheroID() {
        return superheroID;
    }

    public void setSuperheroID(int superheroID) {
        this.superheroID = superheroID;
    }

    public String getSuperheroName() {
        return superheroName;
    }

    public void setSuperheroName(String superheroName) {
        this.superheroName = superheroName;
    }

    public String getSuperheroDescription() {
        return superheroDescription;
    }

    public void setSuperheroDescription(String superheroDescription) {
        this.superheroDescription = superheroDescription;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Superpower> getSuperpowers() {
        return superpowers;
    }

    public void setSuperpowers(List<Superpower> superpowers) {
        this.superpowers = superpowers;
    }

    public List<Sighting> getSuperheroSightings() {
        return superheroSightings;
    }

    public void setSuperheroSightings(List<Sighting> superheroSightings) {
        this.superheroSightings = superheroSightings;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.superheroID;
        hash = 47 * hash + Objects.hashCode(this.superheroName);
        hash = 47 * hash + Objects.hashCode(this.superheroDescription);
        hash = 47 * hash + Objects.hashCode(this.organizations);
        hash = 47 * hash + Objects.hashCode(this.superpowers);
        hash = 47 * hash + Objects.hashCode(this.superheroSightings);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Superhero other = (Superhero) obj;
        if (this.superheroID != other.superheroID) {
            return false;
        }
        if (!Objects.equals(this.superheroName, other.superheroName)) {
            return false;
        }
        if (!Objects.equals(this.superheroDescription, other.superheroDescription)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        if (!Objects.equals(this.superpowers, other.superpowers)) {
            return false;
        }
        if (!Objects.equals(this.superheroSightings, other.superheroSightings)) {
            return false;
        }
        return true;
    }
}
