package br.com.mp.quarkusmovie.service;

import br.com.mp.quarkusmovie.model.Movie;
import br.com.mp.quarkusmovie.repository.MovieRepository;
import br.com.mp.quarkusmovie.restclient.IMDBAPIRestClient;
import br.com.mp.quarkusmovie.restclient.model.MovieIMDB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MovieService {

    @ConfigProperty(name ="x-rapidapi-key")
    String xRapidapiKey;

    @ConfigProperty(name ="x-rapidapi-host")
    String xRapidapiHost;

    @Inject
    MovieRepository movieRepository;


    @Inject
    @RestClient
    IMDBAPIRestClient imdbapiRestClient;

    @Transactional
    public MovieIMDB search(String query) {
        MovieIMDB movieIMDB = imdbapiRestClient.search(xRapidapiKey,xRapidapiHost,query);


        saveMovie(movieIMDB);
        return movieIMDB;
    }

    private void saveMovie(MovieIMDB movieIMDB) {
        List<Movie> movies = new ArrayList<>();


        movieIMDB.getDescriptionIMDBS().forEach(m ->{
            Movie movieDatabase = movieRepository.findyIMDBID(m.getIdIMDB());

            if(movieDatabase == null && m.getQualifier() != null){
                movies.add(new Movie(m));
            }

            movieRepository.persist(movies);
        });
    }

    public List<Movie> list() {
        return movieRepository.listAll();
    }

    public List<Movie> listBestRated() {
    return movieRepository.listBestRated();
    }
}
