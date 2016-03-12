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
    private int defense;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private int level;
    private int experience;

    @Override
    public String toString() {
        return ("Name: " + this.name + "\nType: " + this.type.toString() + "\nHealth: " + this.health + "\\"  + this.maxHealth + "\nDamage " + this.damage );
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

    public void setExperience(int experienceAmount) {
        this.experience += experienceAmount;

        if (this.experience > 100) {

            int increasedLevel = this.experience / 100;
            this.level += increasedLevel;
            this.experience = this.experience % (100 * increasedLevel);

            if (type.toString().equals("ORC")) {
                this.health += increasedLevel * 10;
                this.defense += increasedLevel * 5;
                this.damage += increasedLevel * 15;

            } else if (type.toString().equals("MAGE")) {
                this.health += increasedLevel * 15;
                this.defense += increasedLevel * 10;
                this.damage += increasedLevel * 5;

            } else if (type.toString().equals("PRIEST")) {
                this.health += increasedLevel * 10;
                this.defense += increasedLevel * 15;
                this.damage += increasedLevel * 5;

            } else if (type.toString().equals("KNIGHT")) {
                this.health += increasedLevel * 5;
                this.defense += increasedLevel * 15;
                this.damage += increasedLevel * 10;
            }
        }
    }

    public int getExperience() {
        return this.experience;
    }

    public int getLevel () {
        return this.level;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense () {
        // don't forget to set defense
    }

}
