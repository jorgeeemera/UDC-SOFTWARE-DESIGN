package e1;

public class RayoDeTeutates implements Unidad {
    private String name = "Rayo de Teutates";
    private int attackingPoints = 100;
    private int defensePoints = 25;
    private boolean hasHeavyArmor;

    public RayoDeTeutates(boolean hasHeavyArmor) {
        this.hasHeavyArmor = hasHeavyArmor;
    }

    @Override
    public int getAttackingPoints() {
        return hasHeavyArmor ? (int) (attackingPoints * 0.75) : attackingPoints;
    }

    @Override
    public int getDefensePoints() {
        return hasHeavyArmor ? (int) (defensePoints * 1.25) : defensePoints;
    }

    @Override
    public boolean hasArmor() {
        return hasHeavyArmor;
    }

    @Override
    public String toString() {
        return name + (hasHeavyArmor ? " with heavy armor" : "");
    }
}
