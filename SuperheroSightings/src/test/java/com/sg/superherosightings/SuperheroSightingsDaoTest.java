/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperheroDao;
import com.sg.superherosightings.dao.SuperpowerDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.model.Superpower;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.time.LocalDate;

/**
 *
 * @author brettwilkins
 */
public class SuperheroSightingsDaoTest {

    private LocationDao locationDao;
    private OrganizationDao organizationDao;
    private SightingDao sightingDao;
    private SuperheroDao superheroDao;
    private SuperpowerDao superpowerDao;

    public SuperheroSightingsDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        locationDao = ctx.getBean("locationDao", LocationDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        superheroDao = ctx.getBean("superheroDao", SuperheroDao.class);
        superpowerDao = ctx.getBean("superpowerDao", SuperpowerDao.class);

        //delete all locations
        List<Location> locations = locationDao.getAllLocations();
        for (Location currentLocation : locations) {
            locationDao.deleteLocation(currentLocation.getLocationID());
        }
        //delete all organizations
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            organizationDao.deleteOrganization(currentOrganization.getOrgID());
        }
        //delete all sightings
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingDao.deleteSighting(currentSighting.getSightingID());
        }

        //delete all superheroes
        List<Superhero> superheroes = superheroDao.getAllSuperheroes();
        for (Superhero currentSuperhero : superheroes) {
            superheroDao.deleteSuperhero(currentSuperhero.getSuperheroID());
        }

        //delete all superpowers
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for (Superpower currentSuperpower : superpowers) {
            superpowerDao.deleteSuperpower(currentSuperpower.getSuperpowerID());
        }

    }

    @After
    public void tearDown() {
    }

    //Tests for Superheroes
    @Test
    public void addGetSuperhero() {

        Organization organization = new Organization();
        organization.setOrgName("Justice League");
        organization.setOrgDescription("A sort of United Nations of superheroes");
        organization.setOrgAddress("2112 Goldhawk Road");

        organizationDao.addOrganization(organization);

        Superpower superpower = new Superpower();
        superpower.setPowerName("X-ray Vision");
        superpower.setPowerDescription("Can see through any material up to 15 feet");

        superpowerDao.addSuperpower(superpower);

        Sighting sighting = new Sighting();
        sighting.setSightingName("Batman seen in London");
        sighting.setSightingDate(LocalDate.parse("2016-11-12",
                DateTimeFormatter.ISO_DATE));

        sightingDao.addSighting(sighting);

        Superhero superhero = new Superhero();
        superhero.setSuperheroName("Batman");
        superhero.setSuperheroDescription("Man in black rubber suit");
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero.setOrganizations(organizations);
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero.setSuperpowers(superpowers);
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        superhero.setSuperheroSightings(sightings);

        superheroDao.addSuperhero(superhero);

        Superhero fromSuperheroDao = superheroDao.getSuperheroByID(superhero.getSuperheroID());

        assertEquals(fromSuperheroDao, superhero);

    }

    @Test
    public void deleteSuperhero() {

        Organization organization = new Organization();
        organization.setOrgName("Justice League");
        organization.setOrgDescription("A sort of United Nations of superheroes");
        organization.setOrgAddress("2112 Goldhawk Road");

        organizationDao.addOrganization(organization);

        Superpower superpower = new Superpower();
        superpower.setPowerName("X-ray Vision");
        superpower.setPowerDescription("Can see through any material up to 15 feet");

        superpowerDao.addSuperpower(superpower);

        Sighting sighting = new Sighting();
        sighting.setSightingName("Batman seen in London");
        sighting.setSightingDate(LocalDate.parse("2016-11-12",
                DateTimeFormatter.ISO_DATE));

        sightingDao.addSighting(sighting);

        Superhero superhero = new Superhero();
        superhero.setSuperheroName("Batman");
        superhero.setSuperheroDescription("Man in black rubber suit");
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero.setOrganizations(organizations);
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero.setSuperpowers(superpowers);
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        superhero.setSuperheroSightings(sightings);

        superheroDao.addSuperhero(superhero);

        Superhero fromSuperheroDao = superheroDao.getSuperheroByID(superhero.getSuperheroID());

        assertEquals(fromSuperheroDao, superhero);
        superheroDao.deleteSuperhero(superhero.getSuperheroID());
        assertNull(superheroDao.getSuperheroByID(superhero.getSuperheroID()));

    }

    @Test
    public void updateSuperhero() {

        Organization organization = new Organization();
        organization.setOrgName("Justice League");
        organization.setOrgDescription("A sort of United Nations of superheroes");
        organization.setOrgAddress("2112 Goldhawk Road");

        organizationDao.addOrganization(organization);

        Superpower superpower = new Superpower();
        superpower.setPowerName("X-ray Vision");
        superpower.setPowerDescription("Can see through any material up to 15 feet");

        superpowerDao.addSuperpower(superpower);

        Sighting sighting = new Sighting();
        sighting.setSightingName("Batman seen in London");
        sighting.setSightingDate(LocalDate.parse("2016-11-12", DateTimeFormatter.ISO_DATE));

        sightingDao.addSighting(sighting);

        Superhero superhero = new Superhero();
        superhero.setSuperheroName("Batman");
        superhero.setSuperheroDescription("Man in black rubber suit");
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero.setOrganizations(organizations);
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero.setSuperpowers(superpowers);
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        superhero.setSuperheroSightings(sightings);

        superheroDao.addSuperhero(superhero);

        superheroDao.updateSuperhero(superhero);
        String newName = "Superman";
        superhero.setSuperheroName("Superman");
        superhero.setSuperheroDescription("Man in black rubber suit");
        organizations.add(organization);
        superhero.setOrganizations(organizations);
        superpowers.add(superpower);
        superhero.setSuperpowers(superpowers);
        sightings.add(sighting);
        superhero.setSuperheroSightings(sightings);
        superhero.setSuperheroID(1);

        superheroDao.addSuperhero(superhero);

        Superhero fromSuperheroDao = superheroDao.getSuperheroByID(superhero.getSuperheroID());
        String updatedName = fromSuperheroDao.getSuperheroName();

        assertEquals(updatedName, newName);

    }

    //Tests for Locations
    @Test
    public void addGetLocation() {

        Sighting sighting = new Sighting();
        sighting.setSightingName("Batman seen in London");
        sighting.setSightingDate(LocalDate.parse("2016-11-12",
                DateTimeFormatter.ISO_DATE));

        sightingDao.addSighting(sighting);

        Location location = new Location();
        location.setLocationName("Gotham");
        location.setLocationDescription("Top of Empire State Building");
        location.setAddress("1123 Gotham Street");
        location.setLatitude(new BigDecimal(12.34));
        location.setLongitude(new BigDecimal(45.26));
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        location.setSightings(sightings);

        locationDao.addLocation(location);

        Location fromLocationDao = locationDao.getLocationByID(location.getLocationID());
        int fromLD = fromLocationDao.getLocationID();
        int fromL = location.getLocationID();

        assertEquals(fromLD, fromL);

    }

    @Test
    public void deleteLocation() {
        Sighting sighting = new Sighting();
        sighting.setSightingName("Spider-Man in Paris");
        sighting.setSightingDate(LocalDate.parse("2017-12-25", DateTimeFormatter.ISO_DATE));

        sightingDao.addSighting(sighting);

        Location location = new Location();
        location.setLocationName("Eiffel Tower");
        location.setLocationDescription("Seen scaling the famous landmark");
        location.setAddress("Champs des Mars");
        location.setLatitude(new BigDecimal(34.56));
        location.setLongitude(new BigDecimal(45.67));
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        location.setSightings(sightings);

        locationDao.addLocation(location);

        Location fromLocationDao = locationDao.getLocationByID(location.getLocationID());
        int fromLD = fromLocationDao.getLocationID();
        int fromL = location.getLocationID();

        assertEquals(fromLD, fromL);

        locationDao.deleteLocation(location.getLocationID());
        assertNull(locationDao.getLocationByID(location.getLocationID()));

    }

    @Test
    public void updateLocation() {
        Sighting sighting = new Sighting();
        sighting.setSightingName("Spider-Man in Paris");
        sighting.setSightingDate(LocalDate.parse("2017-12-25", DateTimeFormatter.ISO_DATE));

        sightingDao.addSighting(sighting);

        Location location = new Location();
        location.setLocationName("Eiffel Tower");
        location.setLocationDescription("Seen scaling the famous landmark");
        location.setAddress("Champs des Mars");
        location.setLatitude(new BigDecimal(34.56));
        location.setLongitude(new BigDecimal(45.67));
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        location.setSightings(sightings);

        locationDao.addLocation(location);

        locationDao.updateLocation(location);
        String newLocationName = "Big Ben";
        location.setLocationName("Big Ben");
        location.setLocationDescription("Seen hanging on the minute hand of the clock.");
        location.setAddress("12 Whitehall");
        location.setLatitude(new BigDecimal(54.34));
        location.setLongitude(new BigDecimal(78.29));
        sightings.add(sighting);
        location.setSightings(sightings);
        location.setLocationID(1);

        locationDao.addLocation(location);

        Location fromLocationDao = locationDao.getLocationByID(location.getLocationID());
        String updatedLocationName = fromLocationDao.getLocationName();

        assertEquals(updatedLocationName, newLocationName);

    }

    //Tests for Organizations
    @Test
    public void addGetOrganization() {
        Organization organization = new Organization();
        organization.setOrgName("Justice League");
        organization.setOrgDescription("A sort of United Nations of superheroes");
        organization.setOrgAddress("2112 Goldhawk Road");

        organizationDao.addOrganization(organization);

        Organization fromOrganizationDao = organizationDao.getOrganizationByID(organization.getOrgID());
        assertEquals(fromOrganizationDao, organization);

    }

    @Test
    public void deleteOrganization() {

        Organization organization = new Organization();
        organization.setOrgName("Justice League");
        organization.setOrgDescription("A United Nations for superheroes");
        organization.setOrgAddress("1123 Gotham Street");

        organizationDao.addOrganization(organization);

        Organization fromOrganizationDao = organizationDao.getOrganizationByID(organization.getOrgID());
        assertEquals(fromOrganizationDao, organization);
        organizationDao.deleteOrganization(organization.getOrgID());
        assertNull(organizationDao.getOrganizationByID(organization.getOrgID()));

    }

    @Test
    public void updateOrganization() {
        Organization organization = new Organization();
        organization.setOrgName("Superfriends");
        organization.setOrgDescription("A group of likeminded superheroes.");
        organization.setOrgAddress("1111 Fleet Street");

        organizationDao.addOrganization(organization);

        organizationDao.updateOrganization(organization);
        String newAddress = "234 The Strand";
        organization.setOrgName("Superfriends");
        organization.setOrgDescription("A group of likeminded superheroes.");
        organization.setOrgAddress("234 The Strand");
        organization.setOrgID(1);

        organizationDao.addOrganization(organization);

        Organization fromOrgDao = organizationDao.getOrganizationByID(organization.getOrgID());
        String updatedAddress = fromOrgDao.getOrgAddress();

        assertEquals(updatedAddress, newAddress);

    }

    //Tests for Sightings
    @Test
    public void addGetSighting() {
        Sighting sighting = new Sighting();
        sighting.setSightingName("Batman seen in London");
        sighting.setSightingDate(LocalDate.parse("2016-11-12",
                DateTimeFormatter.ISO_DATE));

        sightingDao.addSighting(sighting);

        Sighting fromSightingDao = sightingDao.getSightingsByID(sighting.getSightingID());
        assertEquals(fromSightingDao, sighting);
    }

    @Test
    public void deleteSighting() {
        Sighting sighting = new Sighting();
        sighting.setSightingName("Batman seen in London");
        sighting.setSightingDate(LocalDate.parse("2016-11-12",
                DateTimeFormatter.ISO_DATE));

        sightingDao.addSighting(sighting);

        Sighting fromSightingDao = sightingDao.getSightingsByID(sighting.getSightingID());
        assertEquals(fromSightingDao, sighting);
        sightingDao.deleteSighting(sighting.getSightingID());
        assertNull(sightingDao.getSightingsByID(sighting.getSightingID()));

    }

    @Test
    public void updateSighting() {
        Sighting sighting = new Sighting();
        sighting.setSightingName("Superman seen in Berlin.");
        sighting.setSightingDate(LocalDate.parse("2017-03-04", DateTimeFormatter.ISO_DATE));

        sightingDao.addSighting(sighting);

        sightingDao.updateSighting(sighting);
        sighting.setSightingName("Superman seen in Berlin.");
        LocalDate newDate = LocalDate.parse("2016-03-04", DateTimeFormatter.ISO_DATE);
        sighting.setSightingDate(LocalDate.parse("2016-03-04", DateTimeFormatter.ISO_DATE));
        sighting.setSightingID(1);

        sightingDao.addSighting(sighting);

        Sighting fromSightingDao = sightingDao.getSightingsByID(sighting.getSightingID());
        LocalDate updatedDate = fromSightingDao.getSightingDate();

        assertEquals(newDate, updatedDate);

    }

    //Tests for Superpowers
    @Test
    public void addGetSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setPowerName("X-ray Vision");
        superpower.setPowerDescription("Can see through any material up to 15 feet");

        superpowerDao.addSuperpower(superpower);

        Superpower fromSuperpowerDao = superpowerDao.getSuperpowersByID(superpower.getSuperpowerID());
        assertEquals(fromSuperpowerDao, superpower);
    }

    @Test
    public void deleteSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setPowerName("Incredible Strength");
        superpower.setPowerDescription("Can lift up to 20K pounds");

        superpowerDao.addSuperpower(superpower);

        Superpower fromSuperpowerDao = superpowerDao.getSuperpowersByID(superpower.getSuperpowerID());
        assertEquals(fromSuperpowerDao, superpower);
        superpowerDao.deleteSuperpower(superpower.getSuperpowerID());
        assertNull(superpowerDao.getSuperpowersByID(superpower.getSuperpowerID()));
    }

    @Test
    public void updateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setPowerName("X-ray Vision");
        superpower.setPowerDescription("Can see through any material up to 15 feet");

        superpowerDao.addSuperpower(superpower);

        superpowerDao.updateSuperpower(superpower);
        String newDescription = "Can see through any material up to 100 feet";
        superpower.setPowerName("X-Ray Vision");
        superpower.setPowerDescription("Can see through any material up to 100 feet");
        superpower.setSuperpowerID(1);
        superpowerDao.addSuperpower(superpower);

        Superpower fromSuperpowerDao = superpowerDao.getSuperpowersByID(superpower.getSuperpowerID());
        String updatedDescription = fromSuperpowerDao.getPowerDescription();

        assertEquals(updatedDescription, newDescription);

    }

}
