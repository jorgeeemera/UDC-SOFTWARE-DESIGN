package e1;

public interface Unidad {
    int getAttackingPoints();
    int getDefensePoints();
    String toString();

    default boolean hasArmor() {
        return false;
    }

    default boolean hasImprovedWeapon() {
        return false;
    }
}
