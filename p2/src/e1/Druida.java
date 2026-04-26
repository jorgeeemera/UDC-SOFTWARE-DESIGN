package e1;

public class Druida implements Unidad {

    private String name = "Druida";
    private int attackingPoints = 45;
    private int defensePoints = 115;

    @Override
    public int getAttackingPoints() {
        return attackingPoints;
    }

    @Override
    public int getDefensePoints() {
        return defensePoints;
    }

    @Override
    public String toString() {
        return "Druida " + name;
    }
}
