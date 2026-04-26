package e1;

import java.util.List;

public class AldeaTeutona extends Aldea {

    public AldeaTeutona(String name, int years, int wallLevel, List<Unidad> army) {
        super(name, years, wallLevel, army); // Asegurar que el ejército nunca es nulo
    }

    @Override
    public double calculateAttackingPower() {
        double poderAtaque = 0;
        for (Unidad unidad : army) {
            poderAtaque += unidad.getAttackingPoints();
        }
        return poderAtaque * 0.95; // -5% penalización de ataque para Teutones
    }

    @Override
    public double calculateDefensePower() {
        double poderDefensa = 0;
        for (Unidad unidad : army) {
            poderDefensa += unidad.getDefensePoints();
        }
        return poderDefensa + (wallResistance); // Bonus de defensa por muralla
    }
}
