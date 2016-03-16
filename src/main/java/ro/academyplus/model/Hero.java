package ro.academyplus.model;


import org.springframework.beans.factory.annotation.Autowired;
import ro.academyplus.dto.HeroDTO;
import ro.academyplus.repository.ArtifactRepository;

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
    private int defense = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private int level;
    private int experience;
    private int armor = -1;
    private int helmet = -1;
    private int weapon = -1;

    @Autowired
    ArtifactRepository artifactRepository;
    @Override
    public String toString() {
        String wep = new String("none");
        String arm = new String("none");
        if (this.weapon > -1)
            wep = artifactRepository.findOneById((long) this.weapon).getName();
        if (this.armor > -1)
            arm = artifactRepository.findOneById((long) this.armor).getName();

        return ("Name: " + this.name + "\nType: " + this.type.toString() + "\nLevel: " + this.level + " ("  + this.experience + "%)\nHealth: " + this.health + "\\"  + this.maxHealth + "\nDamage " + this.damage + "\nArmor: " + arm + "\nWeapon: " + wep );
    }

    public void createHero (HeroDTO heroDTO) {
        setName(heroDTO.getHeroName());
        setType(heroDTO.getType());
        setMaxHealth();
        setHealth();
        setDamage();
        this.level = 1;
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

    public void setHealth(){
        this.health = maxHealth;
    }
    public void setHealth(int amount) {
        this.health -= amount;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth() {
        if (this.type.toString().equals("ORC"))
            this.maxHealth = 120;
        if (this.type.toString().equals("MAGE"))
            this.maxHealth = 110;
        if (this.type.toString().equals("KNIGHT"))
            this.maxHealth = 100;
        if (this.type.toString().equals("PRIEST"))
            this.maxHealth = 130;
        maxHealth += defense;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage() {

        if (type.toString().equals("ORC"))
            this.damage = 20;
        if (type.toString().equals("MAGE"))
            this.damage = 10;
        if (type.toString().equals("KNIGHT"))
            this.damage = 20;
        if (type.toString().equals("PRIEST"))
            this.damage = 30;
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
                this.maxHealth += increasedLevel * 10;
                this.damage += increasedLevel * 15;

            } else if (type.toString().equals("MAGE")) {
                this.maxHealth += increasedLevel * 15;
                this.damage += increasedLevel * 5;

            } else if (type.toString().equals("PRIEST")) {
                this.maxHealth += increasedLevel * 10;
                this.damage += increasedLevel * 5;

            } else if (type.toString().equals("KNIGHT")) {
                this.maxHealth += increasedLevel * 5;
                this.damage += increasedLevel * 10;
            }
        }
        setHealth();
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

    }

    public int getArmor() {
        return this.armor;
    }

    public int getWeapon() {
        return this.weapon;
    }

    public int getHelmet() {
        return this.helmet;
    }

    public void setWeapon (int newWeapon) {
        this.weapon = newWeapon;
    }

    public void setArmor (int newArmor) {
        this.armor = newArmor;
    }

    public void setHelmet(int newHelmet) {
        this.health = newHelmet;
    }

}