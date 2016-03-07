package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.UserDTO;
import ro.academyplus.model.User;
import ro.academyplus.repository.UserRepository;

/**
 * Created by agheboianu on 03.03.2016.
 */

@Service
public class AwesomeService {

    @Autowired
    UserRepository userRepository;

    public String formatName(String name) {
        return "Mr." + name;
    }

    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }
}
