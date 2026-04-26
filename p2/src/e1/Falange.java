package e1;
public class Falange implements Unidad {

    private String name = "Falange";
    private int attackingPoints = 15;
    private int defensePoints = 40;

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
        return "Falange " + name;
    }

}
