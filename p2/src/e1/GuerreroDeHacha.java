package e1;

public class GuerreroDeHacha implements Unidad {
    private String name = "Guerrero de hacha";
    private int attackingPoints = 60;
    private int defensePoints = 30;
    private boolean improvedWeapon;

    public GuerreroDeHacha(boolean improvedWeapon) {
        this.improvedWeapon = improvedWeapon;
    }

    @Override
    public int getAttackingPoints() {
        return improvedWeapon ? (int) (attackingPoints * 1.25) : attackingPoints;
    }

    @Override
    public int getDefensePoints() {
        return defensePoints;
    }

    @Override
    public boolean hasImprovedWeapon() {
        return improvedWeapon;
    }

    @Override
    public String toString() {
        return name + (improvedWeapon ? " with iron mace" : "");
    }
}
