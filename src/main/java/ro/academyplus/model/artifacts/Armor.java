package ro.academyplus.model.artifacts;

/**
 * Created by azaha on 12.03.2016.
 */
public class Armor extends Artifacts {

    private int defense;
    private ArmorType armorType;

    public Armor (ArmorType armorType) {
        setDefense(armorType);
    }

    private void setDefense(ArmorType armorType) {
        if (armorType.toString().equals("SHIELD"))
            this.defense = 20;
        if (armorType.toString().equals("HELMET"))
            this.defense = 10;
    }

    public int getDefense () {
        return this.defense;
    }

}
