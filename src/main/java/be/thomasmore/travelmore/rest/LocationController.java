package be.thomasmore.travelmore.rest;

import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.service.LocationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/locations")
public class LocationController{

    @Inject
    private LocationService locationService;

    @GET
    //{id}
    @Path("/getlocation")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Location getLocationById(@QueryParam("id") int id) {
        return locationService.findLocationById(id);
    }

    @POST
    @Path("/addlocation")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addLocation(Location location) {
        if (null != locationService.findLocationById(location.getId())) {
            return Response
                    .status(Response.Status.NOT_MODIFIED)
                    .entity("location id should not be set.").build();
        }

        locationService.insert(location);
        return Response.status(Response.Status.CREATED).entity(location).build();
    }
}
