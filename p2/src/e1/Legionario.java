package e1;

public class Legionario implements Unidad {

    private String name = "Legionario";
    private int attackingPoints = 40;
    private int defensePoints = 35;

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
        return "Legionario " + name;
    }
}
