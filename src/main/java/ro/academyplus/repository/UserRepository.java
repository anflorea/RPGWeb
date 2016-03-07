package ro.academyplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.academyplus.model.User;

/**
 * Created by agheboianu on 04.03.2016.
 */
public interface UserRepository extends JpaRepository<User,Long>{
}
