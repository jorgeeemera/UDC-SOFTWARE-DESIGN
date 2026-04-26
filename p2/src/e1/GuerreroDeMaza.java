package e1;

public class GuerreroDeMaza implements Unidad {

    private String name = "Guerrero de maza";
    private int attackingPoints = 40;
    private int defensePoints = 20;
    private boolean improveAttacking;

    @Override
    public int getAttackingPoints() {
        if (improveAttacking) {
            return attackingPoints += (int) (attackingPoints * 0.25);
        }
        return attackingPoints;
    }

    @Override
    public int getDefensePoints() {
        return defensePoints;
    }

    @Override
    public String toString() {
        return "Guerrero de maza " + name;
    }

}
