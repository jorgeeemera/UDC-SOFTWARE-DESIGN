package e1;

import java.util.List;

public abstract class Aldea {
    protected String name;
    protected int age;
    protected int wallResistance;
    protected List<Unidad> army;

    public Aldea(String name, int age, int wallResistance, List<Unidad> army) {
        this.name = name;
        this.age = age;
        this.wallResistance = wallResistance;
        this.army = army;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Unidad> getArmy() {
        return army;
    }

    public abstract double calculateAttackingPower();
    public abstract double calculateDefensePower();
}
