/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author brettwilkins
 */
public class Organization {

    private int orgID;

    @NotEmpty(message = "You must supply a value for Organization Name.")
    @Length(max = 45, message = "Organization Name must be no more than 45 characters in length.")
    private String orgName;

    @NotEmpty(message = "You must supply a value for Organization Description.")
    @Length(max = 100, message = "Organization Description must be no more than 100 characters in length.")
    private String orgDescription;

    @NotEmpty(message = "You must supply a value for Address.")
    @Length(max = 45, message = "Address must be no more than 45 characters in length.")
    private String orgAddress;

    public int getOrgID() {
        return orgID;
    }

    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.orgID;
        hash = 61 * hash + Objects.hashCode(this.orgName);
        hash = 61 * hash + Objects.hashCode(this.orgDescription);
        hash = 61 * hash + Objects.hashCode(this.orgAddress);
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
        final Organization other = (Organization) obj;
        if (this.orgID != other.orgID) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.orgDescription, other.orgDescription)) {
            return false;
        }
        if (!Objects.equals(this.orgAddress, other.orgAddress)) {
            return false;
        }
        return true;
    }

}
