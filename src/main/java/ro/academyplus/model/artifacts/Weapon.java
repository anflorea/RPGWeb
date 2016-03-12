package ro.academyplus.model.artifacts;

/**
 * Created by azaha on 12.03.2016.
 */

public class Weapon extends Artifacts {

    private int damage;
    private WeaponType weaponType;

    public Weapon (WeaponType weaponType) {
        setDamage(weaponType);
    }

    private void setDamage(WeaponType weaponType) {

        if (weaponType.toString().equals("AXE"))
            this.damage = 10;
        if (weaponType.toString().equals("SWORD"))
            this.damage = 20;
        if (weaponType.toString().equals("ARROW"))
            this.damage = 15;
        if (weaponType.toString().equals("KNIFE"))
            this.damage = 5;

    }

    public int getDamage() {
        return this.damage;
    }

}
