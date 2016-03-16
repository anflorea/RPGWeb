package ro.academyplus.model;

import org.springframework.beans.factory.annotation.Autowired;
import ro.academyplus.dto.HeroDTO;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by Bb on 3/15/2016.
 */
public class Monster {
    private MonsterType type;
    private int health;
    private int maxHealth = 59;
    private int damage = 10;
    private int level;
    private int dropedExperience;

    @Autowired
    HttpServletRequest request;
    @Override
    public String toString() {
        return ("This is a " + this.type.toString() + " monsta', it has " + this.health + " health, and it can hurt you with " + this.damage + " damage. ");
    }

    public void createMonster (int heroLevel) {
        setLevel (heroLevel);
        randomtype();
        setMaxHealth(heroLevel);
        setHealth();
        setDamage(heroLevel);
        setDropedExperience();
    }

    public void randomtype(){
        Random random = new Random();
        int rnd = random.nextInt(4);
        if (rnd == 0)
            this.type = MonsterType.DARKMAGE;
        if (rnd == 1)
            this.type = MonsterType.GOBLIN;
        if (rnd == 2)
            this.type = MonsterType.DEVIL;
        if (rnd == 3)
            this.type = MonsterType.NECROMANCER;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(){
        this.health = maxHealth;
    }

    public void setHealth (int amount) {
        this.health -= amount;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int heroLevel) {
        if (this.type.toString().equals("GOBLIN"))
            this.maxHealth = this.maxHealth + (heroLevel * 15);
        if (this.type.toString().equals("DARKMAGE"))
            this.maxHealth = this.maxHealth + (heroLevel * 6);
        if (this.type.toString().equals("DEVIL"))
            this.maxHealth = this.maxHealth + (heroLevel * 12);
        if (this.type.toString().equals("NECROMANCER"))
            this.maxHealth = this.maxHealth + (heroLevel * 8);
    }
    public void setDamage(int heroLevel) {
        if (type.toString().equals("GOBLIN"))
            this.damage =  this.damage + (heroLevel * 6);
        if (type.toString().equals("DARKMAGE"))
            this.damage =  this.damage + (heroLevel * 15);
        if (type.toString().equals("DEVIL"))
            this.damage =  this.damage + (heroLevel * 8);
        if (type.toString().equals("NECROMANCER"))
            this.damage =  this.damage + (heroLevel * 12);
    }

    public int getDamage() {
        return damage;
    }

    public void setDropedExperience() {
        this.dropedExperience = this.getMaxHealth() + this.getDamage();
    }

    public int getDropedExperience() {
        return this.dropedExperience;
    }

    public void setLevel (int heroLevel) {
        this.level = heroLevel;
    }

    public int getLevel () {
        return this.level;
    }

}
