package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.Movie;
import br.com.mp.quarkusmovie.restclient.IMDBAPIRestClient;
import br.com.mp.quarkusmovie.restclient.model.MovieIMDB;
import br.com.mp.quarkusmovie.service.MovieService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/movies")
public class MovieResource {
    @Inject
    MovieService movieService;

    @GET
    @Path("/search/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieIMDB hello(@PathParam("query") String query) {
        return movieService.search(query);

    }

    @GET

    public List<Movie> list(){
        return movieService.list();
    }

    @GET
    @Path("/listBestRated")
    public List<Movie> listBestRated(){
        return movieService.listBestRated();
    }
}
