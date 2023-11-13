package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.User;
import br.com.mp.quarkusmovie.model.dto.LoginDTO;
import br.com.mp.quarkusmovie.model.dto.LoginResponseDTO;
import br.com.mp.quarkusmovie.repository.UserRepository;
import br.com.mp.quarkusmovie.util.GenerateToken;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
@Path("/token")
public class TokenService {

    @Inject
    GenerateToken generateToken;

    @Inject
    UserRepository userRepository;

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;


    @GET
    @Path("/tokenn")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String getJWTt(User user){
        Optional<User> userr = userRepository.findByEmail(user.getEmail());
        if(userr.isEmpty()){
            throw new NotAuthorizedException("Erro ao efetuar a autenticação");

        }
        String token = Jwt.issuer(issuer)
                .upn(user.getEmail())
                .expiresAt(Instant.now().plus(Duration.ofHours(1)))
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.full_name, user.getName())
                .claim(Claims.email, user.getEmail())
                .sign();
        System.out.println(token);
        return token;
    }

}
