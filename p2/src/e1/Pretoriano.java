package e1;

public class Pretoriano implements Unidad {
    private String name = "Pretoriano";
    private int attackingPoints = 30;
    private int defensePoints = 65;
    private boolean hasArmor;

    public Pretoriano(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

    @Override
    public int getAttackingPoints() {
        return attackingPoints;
    }

    @Override
    public int getDefensePoints() {
        return hasArmor ? (int) (defensePoints * 1.10) : defensePoints;
    }

    @Override
    public boolean hasArmor() {
        return hasArmor;
    }

    @Override
    public String toString() {
        return name + (hasArmor ? " with armor" : "");
    }
}
