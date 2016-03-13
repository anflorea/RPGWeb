package ro.academyplus.model;


import ro.academyplus.dto.HeroDTO;
import ro.academyplus.model.artifacts.Armor;
import ro.academyplus.model.artifacts.Weapon;

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

    @Override
    public String toString() {
        return ("Name: " + this.name + "\nType: " + this.type.toString() + "\nHealth: " + this.health + "\\"  + this.maxHealth + "\nDamage " + this.damage );
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