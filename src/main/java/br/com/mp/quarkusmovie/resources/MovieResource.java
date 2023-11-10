package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.Movie;
import br.com.mp.quarkusmovie.restclient.IMDBAPIRestClient;
import br.com.mp.quarkusmovie.restclient.model.MovieIMDB;
import br.com.mp.quarkusmovie.service.MovieService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/movies")
public class MovieResource {
    @Inject
    MovieService movieService;

    @Inject
    JsonWebToken jwt;

    @Operation(summary = "Método para buscar filmes")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON, //
                    schema = @Schema(implementation = MovieIMDB.class, type = SchemaType.ARRAY)))
    @Tag(name="movie")
    @GET
    @Path("/search/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieIMDB search(@PathParam("query") String query) {

        return movieService.search(query);
    }

    @GET
    @Path("/list")
    public List<Movie> list(){
        return movieService.list();
    }

    @GET
    @Path("/listBestRated")
    public List<Movie> listBestRated(){
        return movieService.listBestRated();
    }
}
