package ro.academyplus.dto;

import ro.academyplus.model.HeroType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Flo on 08-Mar-16.
 */
public class HeroDTO {
    @NotNull
    @Size(min = 3, max = 30, message = "Name size must be between 3 and 30 characters.")
    private String heroName;
    @NotNull(message = "You must choose a value.")
    private HeroType type;
    private long userId;
    private long id;

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

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public HeroType getType() {
        return type;
    }

    public void setType(HeroType type) {
        this.type = type;
    }
}
