package e1;

public class Paladin implements Unidad {

    private String name = "Paladin";
    private int attackingPoints = 55;
    private int defensePoints = 100;

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
        return "Paladin " + name;
    }

}
