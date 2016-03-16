package ro.academyplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.academyplus.model.artifacts.Artifacts;

import java.util.ArrayList;

/**
 * Created by azaha on 13.03.2016.
 */

public interface ArtifactRepository extends JpaRepository<Artifacts, Integer> {

    ArrayList<Artifacts> findAll();
    Artifacts findOneById(long id);

}
