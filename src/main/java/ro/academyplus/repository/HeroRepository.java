package ro.academyplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.academyplus.model.Hero;
import ro.academyplus.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bb on 3/8/2016.
 */

public interface HeroRepository extends JpaRepository<Hero,Long> {
//    List<> findByBoo(String boo);

    ArrayList<Hero> findByUserId(long userId);
    Hero findOneById(long id);



//    ArrayList<Hero> findBy();
//    List<Hero> listOfHeroes = new ArrayList<Hero>();
//    listOfHeroes = HeroRepository findAll();
//     listOfHeroes = HeroRepository(String email);
}