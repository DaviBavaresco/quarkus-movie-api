package br.com.mp.quarkusmovie.resources;

import br.com.mp.quarkusmovie.model.dto.LoginDTO;
import br.com.mp.quarkusmovie.model.dto.LoginResponseDTO;
import br.com.mp.quarkusmovie.service.LoginService;
import br.com.mp.quarkusmovie.util.GenerateToken;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/login")
public class LoginResource {

    @Inject
    LoginService loginService;

    @Inject
    GenerateToken generateToken;

    @PermitAll
    @POST
    public LoginResponseDTO login(LoginDTO loginDTO){
    return loginService.login(loginDTO);
    }



}
