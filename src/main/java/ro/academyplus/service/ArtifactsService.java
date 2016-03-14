package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.model.artifacts.ArtifactType;
import ro.academyplus.model.artifacts.Artifacts;
import ro.academyplus.repository.ArtifactRepository;

/**
 * Created by azaha on 14.03.2016.
 */
@Service
public class ArtifactsService {

    @Autowired
    ArtifactRepository artifactRepository;
    public void initArtifactsInDataBase() {
        if (artifactRepository.findAll().size() == 0)
            this.createThemArtifacts();
    }

    public void createThemArtifacts() {

        Artifacts artifacts0 = new Artifacts();
        artifacts0.createArtifact("War Hammer", ArtifactType.WEAPON, 9, 0);
        artifactRepository.save(artifacts0);
        Artifacts artifacts1 = new Artifacts();
        artifacts1.createArtifact("Dagger", ArtifactType.WEAPON, 5, 0);
        artifactRepository.save(artifacts1);
        Artifacts artifacts2 = new Artifacts();
        artifacts2.createArtifact("Short Sword", ArtifactType.WEAPON, 8, 0);
        artifactRepository.save(artifacts2);
        Artifacts artifacts3 = new Artifacts();
        artifacts3.createArtifact("Long Sword", ArtifactType.WEAPON, 12, 0);
        artifactRepository.save(artifacts3);
        Artifacts artifacts4 = new Artifacts();
        artifacts4.createArtifact("Bastard Sword", ArtifactType.WEAPON, 15, 0);
        artifactRepository.save(artifacts4);
        Artifacts artifacts5 = new Artifacts();
        artifacts5.createArtifact("Great Sword", ArtifactType.WEAPON, 20, 0);
        artifactRepository.save(artifacts5);
        Artifacts artifacts6 = new Artifacts();
        artifacts6.createArtifact("Trident", ArtifactType.WEAPON, 16, 0);
        artifactRepository.save(artifacts6);
        Artifacts artifacts7 = new Artifacts();
        artifacts7.createArtifact("Battle Axe", ArtifactType.WEAPON, 14, 0);
        artifactRepository.save(artifacts7);
        Artifacts artifacts8 = new Artifacts();
        artifacts8.createArtifact("Great Club", ArtifactType.WEAPON, 13, 0);
        artifactRepository.save(artifacts8);
        Artifacts artifacts9 = new Artifacts();
        artifacts9.createArtifact("Long Spear", ArtifactType.WEAPON, 17, 0);
        artifactRepository.save(artifacts9);


        Artifacts artifacts10 = new Artifacts();
        artifacts10.createArtifact("Battle Armor", ArtifactType.ARMOR, 0, 15);
        artifactRepository.save(artifacts10);
        Artifacts artifacts11 = new Artifacts();
        artifacts10.createArtifact("Wood Armor", ArtifactType.ARMOR, 0, 5);
        artifactRepository.save(artifacts11);
        Artifacts artifacts12 = new Artifacts();
        artifacts10.createArtifact("Leather Armor", ArtifactType.ARMOR, 0, 7);
        artifactRepository.save(artifacts12);
        Artifacts artifacts13 = new Artifacts();
        artifacts10.createArtifact("Metal Armor", ArtifactType.ARMOR, 0, 12);
        artifactRepository.save(artifacts13);
        Artifacts artifacts14 = new Artifacts();
        artifacts10.createArtifact("Metal Helmet", ArtifactType.ARMOR, 0, 7);
        artifactRepository.save(artifacts14);
        Artifacts artifacts15 = new Artifacts();
        artifacts10.createArtifact("Wood Helmet", ArtifactType.ARMOR, 0, 4);
        artifactRepository.save(artifacts15);
        Artifacts artifacts16 = new Artifacts();
        artifacts10.createArtifact("Metal Gloves", ArtifactType.ARMOR, 0, 6);
        artifactRepository.save(artifacts16);
        Artifacts artifacts17 = new Artifacts();
        artifacts10.createArtifact("Leather Gloves", ArtifactType.ARMOR, 0, 3);
        artifactRepository.save(artifacts17);
        Artifacts artifacts18 = new Artifacts();
        artifacts10.createArtifact("Large Metal Shield", ArtifactType.ARMOR, 0, 10);
        artifactRepository.save(artifacts18);
        Artifacts artifacts19 = new Artifacts();
        artifacts10.createArtifact("Full Body Armor", ArtifactType.ARMOR, 0, 20);
        artifactRepository.save(artifacts19);
    }
}
