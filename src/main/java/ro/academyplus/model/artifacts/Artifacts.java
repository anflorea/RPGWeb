package ro.academyplus.model.artifacts;

import javax.persistence.*;

/**
 * Created by azaha on 12.03.2016.
 */

@Entity
@Table(name = "ARTIFACTS")
public class Artifacts {

    private String name;
    private ArtifactType artifactType;
    private int damage;
    private int defense;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ArtifactType getArtifactType() {
        return artifactType;
    }

    public void setArtifactType(ArtifactType artifactType) {
        this.artifactType = artifactType;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void createArtifact(String name, ArtifactType type, int damage, int defense) {

        Artifacts artifact = new Artifacts();
        artifact.setName(name);
        artifact.setArtifactType(type);
        artifact.setDamage(damage);
        artifact.setDefense(defense);

    }
}
