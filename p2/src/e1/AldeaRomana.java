package e1;

import java.util.List;

public class AldeaRomana extends Aldea {

    public AldeaRomana(String name, int age, int wallResistance, List<Unidad> army) {
        super(name, age, wallResistance, army);
    }

    @Override
    public double calculateAttackingPower() {
        return army.stream().mapToDouble(Unidad::getAttackingPoints).sum() * 1.1;
    }

    @Override
    public double calculateDefensePower() {
        return army.stream().mapToDouble(Unidad::getDefensePoints).sum() + (wallResistance * 2);
    }
}
