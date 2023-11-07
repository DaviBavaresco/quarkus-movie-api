package br.com.mp.quarkusmovie.service;

import br.com.mp.quarkusmovie.model.User;
import br.com.mp.quarkusmovie.model.dto.UserDTO;
import br.com.mp.quarkusmovie.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;
    @Transactional
    public void create(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());


        userRepository.persist(user);
    }
}
