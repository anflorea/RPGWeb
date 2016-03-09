package ro.academyplus.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Flo on 08-Mar-16.
 */

@Entity
@Table(name = "HEROES")
public class Hero implements Serializable{
    private String name;
    private HeroType type;
    private int health;
    private int maxHealth;
    private int damage;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;

    @Override
    public String toString() {
        return ("ID: " + this.id + "\nName: " + this.name + "\nType: " + this.type.toString() + "\nHealt: " + this.health + "\\"  + this.maxHealth + "\nDamage " + this.damage );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroType getType() {
        return type;
    }

    public void setType(HeroType type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
