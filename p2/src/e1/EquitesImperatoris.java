package e1;
public class EquitesImperatoris implements Unidad {

    private String name = "Equites Imperatoris";
    private int attackingPoints = 120;
    private int defensePoints = 65;

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
        return "Equites Imperatoris " + name;
    }
}
