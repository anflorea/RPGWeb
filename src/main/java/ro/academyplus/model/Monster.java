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
    private int maxHealth;
    private int damage;
    private int level;
    private int dropedExperience;

    @Autowired HttpServletRequest request;
    @Override
    public String toString() {
        return ("Type: " + this.type.toString () + "\nHealth: " + this.health + "\\"  + this.maxHealth + "\nDamage " + this.damage );
    }

    public void createMonster (){
        setLevel ();
        randomtype();
        setMaxHealth();
        setHealth();
        setDamage();
        setDropedExperience();

    }

    public void randomtype(){
        Random random = new Random();
        int rnd = random.nextInt(3);
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth() {
        if (this.type.toString().equals("GOBLIN"))
            this.maxHealth = this.maxHealth + ((Integer)request.getAttribute("hero_level") * 15);
        if (this.type.toString().equals("DARKMAGE"))
            this.maxHealth = this.maxHealth + ((Integer)request.getAttribute("hero_level") * 6);
        if (this.type.toString().equals("DEVIL"))
            this.maxHealth = this.maxHealth + ((Integer)request.getAttribute("hero_level") * 12);
        if (this.type.toString().equals("NECROMANCER"))
            this.maxHealth = this.maxHealth + ((Integer)request.getAttribute("hero_level") * 8);
    }
    public void setDamage() {
        if (type.toString().equals("GOBLIN"))
            this.damage =  this.damage + ((Integer)request.getAttribute("hero_level") * 6);
        if (type.toString().equals("DARKMAGE"))
            this.damage =  this.damage + ((Integer)request.getAttribute("hero_level") * 15);
        if (type.toString().equals("DEVIL"))
            this.damage =  this.damage + ((Integer)request.getAttribute("hero_level") * 8);
        if (type.toString().equals("NECROMANCER"))
            this.damage =  this.damage + ((Integer)request.getAttribute("hero_level") * 12);
    }

    public int getDamage() {
        return damage;
    }

    public void setDropedExperience() {
        this.dropedExperience = this.getHealth() + this.getDamage();
    }

    public int getDropedExperience() {
        return this.dropedExperience;
    }

    public void setLevel () {
        this.level = (Integer)request.getAttribute("hero_level");
    }

    public int getLevel () {
        return this.level;
    }

}
