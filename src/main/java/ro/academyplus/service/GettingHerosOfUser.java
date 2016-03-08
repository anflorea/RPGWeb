package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.UserDTO;
import ro.academyplus.model.Hero;
import ro.academyplus.model.User;
import ro.academyplus.repository.HeroRepository;
import ro.academyplus.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bb on 3/8/2016.
 */
@Service
public class GettingHerosOfUser {


    @Autowired
    UserRepository userRepository;
    @Autowired
    HeroRepository heroRepository;


    public ArrayList<Hero> listingHero(long userId) {

        ArrayList<Hero> heroes = heroRepository.findByUserId(userId);

        for (Hero i: heroes)
            System.out.println(i);
        return heroes;
    }
}
