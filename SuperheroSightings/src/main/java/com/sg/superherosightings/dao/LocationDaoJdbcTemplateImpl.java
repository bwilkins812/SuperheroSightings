/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.mappers.LocationMapper;
import com.sg.superherosightings.mappers.SightingMapper;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brettwilkins
 */
public class LocationDaoJdbcTemplateImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_LOCATION
            = "insert into Locations (LocationName, LocationDescription, Address, Latitude, Longitude) values (?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_LOCATIONS_SIGHTINGS
            = "insert into LocationsSightings (LocationID, SightingID) values (?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from Locations where LocationID = ?";

    private static final String SQL_DELETE_LOCATIONS_SIGHTINGS
            = "delete from LocationsSightings where LocationID = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update Locations set LocationName = ?, LocationDescription = ?, Address = ?, Latitude = ?, Longitude = ? where LocationID = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from Locations where LocationID = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from Locations";

    private static final String SQL_SELECT_LOCATIONS_BY_SIGHTING_ID
            = "select loc.LocationID, loc.LocationName, loc.LocationDescription, loc.Address, loc.Latitude, loc.Longitude "
            + "from Locations loc join LocationsSightings losi on SightingID where loc.LocationID = losi.LocationID "
            + "and losi.SightingID = ?";

    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID
            = "select s.SightingID, s.SightingName, s.SightingDate from Sightings s "
            + "join LocationsSightings losi on s.SightingID = losi.SightingID where losi.LocationID = ?";

    //Add a Location
    //==============
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {

        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude());

        location.setLocationID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        insertLocationsSightings(location);

    }

    //Adding a Location Helper Methods
    //================================
    private void insertLocationsSightings(Location location) {
        final int locationID = location.getLocationID();
        final List<Sighting> sightings = location.getSightings();

        for (Sighting currentSighting : sightings) {
            jdbcTemplate.update(SQL_INSERT_LOCATIONS_SIGHTINGS, locationID, currentSighting.getSightingID());

        }
    }

    //Delete a Location
    //=================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteLocation(int locationID) {
        jdbcTemplate.update(SQL_DELETE_LOCATIONS_SIGHTINGS, locationID);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationID);
    }

    //Update a Location
    //=================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationID());

        jdbcTemplate.update(SQL_DELETE_LOCATIONS_SIGHTINGS, location.getLocationID());
        insertLocationsSightings(location);

    }

    //Get a Location and ALL Locations
    @Override
    public Location getLocationByID(int id) {
        try {
            Location location = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), id);
            location.setSightings(findSightingsForLocation(location));

            return location;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    //Get a Location and ALL Locations Helper Methods
    @Override
    public List<Location> getLocationsBySightingID(int sightingID) {
        List<Location> locationList = jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_SIGHTING_ID, new LocationMapper(), sightingID);
        return associateSightingsWithLocations(locationList);
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locationList = jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
        return associateSightingsWithLocations(locationList);
    }

    private List<Sighting> findSightingsForLocation(Location location) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, new SightingMapper(), location.getLocationID());
    }

    private List<Location> associateSightingsWithLocations(List<Location> locationList) {
        for (Location currentLocation : locationList) {
            currentLocation.setSightings(findSightingsForLocation(currentLocation));

        }
        return locationList;
    }

}
